use std::ffi::CStr;
use std::fs::{File, OpenOptions};
use std::io::{Error, Read, Result, Write};
use std::os::raw::{c_char, c_int};
use std::os::unix::io::{AsRawFd, IntoRawFd, RawFd};
use std::ptr::null;

extern "C" {
    fn tuntap_set_name(fd: c_int, name: *mut u8, flags: c_int) -> c_int;
    fn tuntap_set_up(name: *mut u8) -> c_int;
    fn tuntap_set_down(name: *mut u8) -> c_int;
}

#[derive(Copy, Clone, Debug, Eq, PartialEq, Hash, Ord, PartialOrd)]
pub enum Mode {
    TUN = 1,
    TAP = 2,
}

pub trait Option {
    fn name(&self) -> String;
    fn mode(&self) -> Mode;
}

pub trait Interface {
    fn name(&self) -> String;
    fn up(&self) -> Result<TuntapInterface>;
    fn down(&self);
}

pub struct TunTapOption {
    pub name: String,
    pub mode: Mode,
}

impl Option for TunTapOption {
    fn name(&self) -> String {
        self.name.clone()
    }
    fn mode(&self) -> Mode {
        self.mode
    }
}

#[derive(Debug)]
pub struct TuntapInterface {
    fd: File,
    name: String,
}

pub struct Service {}

impl Service {
    pub fn create(opt: &impl Option) -> Result<TuntapInterface> {
        let fd = OpenOptions::new()
            .read(true)
            .write(true)
            .open("/dev/net/tun")?;

        let mut name_buffer = Vec::new();
        name_buffer.extend_from_slice(opt.name().as_bytes());
        name_buffer.extend_from_slice(&[0; 33]);
        let name_ptr: *mut u8 = name_buffer.as_mut_ptr();

        let flags;
        if opt.mode() == Mode::TUN {
            flags = 1 | 0x1000;
        } else {
            flags = 2 | 0x1000;
        }

        let result = unsafe { tuntap_set_name(fd.as_raw_fd(), name_ptr, flags as c_int) };

        if result < 0 {
            return Err(Error::last_os_error());
        }

        let name = unsafe {
            CStr::from_ptr(name_ptr as *const c_char)
                .to_string_lossy()
                .into_owned()
        };
        Ok(TuntapInterface { fd, name })
    }
}

impl Interface for TuntapInterface {
    fn name(&self) -> String {
        self.name.clone()
    }

    fn up(&self) -> Result<TuntapInterface> {
        let mut name_buffer = Vec::new();
        name_buffer.extend_from_slice(self.name.as_bytes());
        name_buffer.extend_from_slice(&[0; 33]);
        let name_ptr: *mut u8 = name_buffer.as_mut_ptr();
        unsafe { tuntap_set_up(name_ptr) };
    }

    fn down(&self) {
        let mut name_buffer = Vec::new();
        name_buffer.extend_from_slice(self.name.as_bytes());
        name_buffer.extend_from_slice(&[0; 33]);
        let name_ptr: *mut u8 = name_buffer.as_mut_ptr();
        unsafe { tuntap_set_down(name_ptr) };
    }
}


impl AsRawFd for TuntapInterface {
    fn as_raw_fd(&self) -> RawFd {
        self.fd.as_raw_fd()
    }
}

impl IntoRawFd for TuntapInterface {
    fn into_raw_fd(self) -> RawFd {
        self.fd.into_raw_fd()
    }
}
