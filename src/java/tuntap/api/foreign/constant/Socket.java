package tuntap.api.foreign.constant;

public interface Socket {

    int AF_INET = 2;
    int AF_INET6 = 10;

    int PF_UNSPEC = 0;
    int PF_LOCAL = 1;
    int PF_INET = 2;
    int PF_INET6 = 10;

    int PF_PACKET = 17;
    int SOCK_STREAM = 1;
    int SOCK_DGRAM = 2;
    int SOCK_RAW = 3;

    // mac os
    int PF_SYSTEM = 32;

    int AF_SYSTEM = 32;
}
