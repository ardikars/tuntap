package tuntap.macos;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Device;

class MacOSJNRTunDevice implements Device {

    static final int SYSPROTO_CONTROL = 2;
    static final int AF_SYS_CONTROL = 2;
    static final int SOCK_DGRAM = 2;
    static final int PF_SYSTEM = 32;
    static final int AF_SYSTEM = 32;
    static final String UTUN_CONTROL_NAME = "com.apple.net.utun_control";

    private final FileDescriptor id;
    private final String name;
    private final String mode;

    public MacOSJNRTunDevice(Device.Options opts) {
        int fd;
        if ((fd = MacOSJNRNative.LIB_C.socket(PF_SYSTEM, SOCK_DGRAM, SYSPROTO_CONTROL)) < 0) {
            throw new RuntimeException();
        }
        int rc;
        long CTLIOCGINFO = 3227799043L;
        MacOSJNRNative.ctl_info info = new MacOSJNRNative.ctl_info(MacOSJNRNative.RUNTIME);
        MacOSJNRNative.LIB_C.memset(Struct.getMemory(info), 0, Struct.size(info));
        info.ctl_name.set(UTUN_CONTROL_NAME);
        if ((rc = MacOSJNRNative.LIB_C.ioctl(fd, CTLIOCGINFO, Struct.getMemory(info))) < 0) {
            MacOSJNRNative.LIB_C.close(rc);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        MacOSJNRNative.sockaddr_ctl addr = new MacOSJNRNative.sockaddr_ctl(MacOSJNRNative.RUNTIME);
        addr.sc_id.set(info.ctl_id.longValue());
        addr.sc_len.set(Struct.size(addr));
        addr.sc_family.set(AF_SYSTEM);
        addr.ss_sysaddr.set(AF_SYS_CONTROL);
        addr.sc_unit.set(1); // id (utun1);
        if ((rc = MacOSJNRNative.LIB_C.connect(fd, addr, Struct.size(addr))) < 0) {
            MacOSJNRNative.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): connect()");
        }
        this.name = "utun" + (addr.sc_unit.get() - 1);
        this.id = new FileDescriptor(fd);
        this.mode = opts.mode().name();
    }

    @Override
    public FileDescriptor id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String mode() {
        return mode;
    }

    @Override
    public long read(Buffer buffer) {
        MacOSJNRBuffer ptrBuf = (MacOSJNRBuffer) buffer;
        long written = MacOSJNRNative.LIB_C.read(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.readerIndex()), buffer.writeableBytes());
        buffer.writerIndex(buffer.writerIndex() + written);
        return written;
    }

    @Override
    public long write(Buffer buffer) {
        MacOSJNRBuffer ptrBuf = (MacOSJNRBuffer) buffer;
        long nbytes = MacOSJNRNative.LIB_C.write(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.writerIndex()), buffer.readableBytes());
        buffer.readerIndex(buffer.readerIndex() + nbytes);
        return nbytes;
    }

    @Override
    public void close() {
        MacOSJNRNative.LIB_C.close(id.fd);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MacOSJNRTunInterface{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private static final class FileDescriptor implements Device.Id<Integer> {

        private final int fd;

        public FileDescriptor(int fd) {
            this.fd = fd;
        }

        @Override
        public Integer value() {
            return fd;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FileDescriptor{");
            sb.append("fd=").append(fd);
            sb.append('}');
            return sb.toString();
        }
    }
}
