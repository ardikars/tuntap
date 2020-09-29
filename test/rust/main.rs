extern crate tuntap;
use std::{thread, time};
use tuntap::{TuntapInterface, TunTapOption, Mode, Interface, Service};

fn main() {

    let opt = TunTapOption {
        name: "rstun".to_string(),
        mode: Mode::TUN
    };

    let interface = Service::create(&opt).expect("Whala");
    eprintln!("{:x?}", interface.name());
    interface.up();
    thread::sleep(time::Duration::from_millis(10000));
}