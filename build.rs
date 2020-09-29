extern crate cc;

use cc::Build;

fn main() {
    Build::new()
        .file("src/c/tuntap.c")
        .warnings(true)
        .compile("tuntap");
}