package tuntap.linux;

import tuntap.Socket;

import java.net.Inet6Address;
import java.net.InetSocketAddress;

abstract class LinuxJNRAbstractSocket implements Socket {

    protected final int fd;

    protected LinuxJNRAbstractSocket(InetSocketAddress socketAddress, Socket.Options options) {
        int domain = socketAddress.getAddress() instanceof Inet6Address ? LinuxJNRAbstractDevice.AF_INET6 : LinuxJNRAbstractDevice.AF_INET;
        int type;
        int backlog;
        if (options instanceof Socket.Udp.Options) {
            Udp.Options opts = (Udp.Options) options;
            backlog = opts.listen();
            type = LinuxJNRAbstractDevice.SOCK_DGRAM;
        } else {
            Tcp.Options opts = (Tcp.Options) options;
            backlog = opts.listen();
            type = LinuxJNRAbstractDevice.SOCK_STREAM;
        }
        this.fd = LinuxJNRNative.LIB_C.socket(domain, type, 0);
        if (fd == -1) {
            throw new RuntimeException(); // On error, -1 is returned, and errno is set appropriately.
        }
        if (backlog > 0) {
            if (LinuxJNRNative.LIB_C.bind(fd, null, 0) == -1) {
                throw new RuntimeException(); // On error, -1 is returned, and errno is set appropriately.
            }
            if (LinuxJNRNative.LIB_C.listen(fd, backlog) == -1) {
                throw new RuntimeException(); // On error, -1 is returned, and errno is set appropriately.
            }
        } else {
            if (LinuxJNRNative.LIB_C.connect(fd, null, 0) == -1) {
                throw new RuntimeException(); // On error, -1 is returned, and errno is set appropriately.
            }
        }
    }

    @Override
    public void close() {
        LinuxJNRNative.LIB_C.close(fd);
    }
}
