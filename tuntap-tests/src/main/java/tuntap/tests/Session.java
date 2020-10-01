package tuntap.tests;

import tuntap.Buffer;

public class Session {

    final sockaddr_in addr;
    final Buffer buffer;

    public Session(sockaddr_in addr, Buffer buffer) {
        this.addr = addr;
        this.buffer = buffer;
    }
}