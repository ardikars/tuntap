package tuntap.linux;

import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.InterfaceMode;
import tuntap.InterfaceOptions;
import tuntap.common.foreign.Unsafe;
import tuntap.common.foreign.constant.Ifreq;
import tuntap.common.foreign.constant.Ioctl;
import tuntap.common.foreign.constant.Socket;
import tuntap.linux.foreign.constant.TunTap;
import tuntap.linux.foreign.ifreq;

public class TunTapInterface implements Interface {

    public static final int O_RDWR = 2;

    private final Id id;
    private final String name;

    public TunTapInterface(Options opt) {
        InterfaceOptions options = (InterfaceOptions) opt;
        int fd;
        int rc;
        if ((fd = Unsafe.NATIVE.open("/dev/net/tun", O_RDWR)) < 0) {
            throw new RuntimeException();
        }
        int TUNSETIFF = 1074025674;
        ifreq ifr = new ifreq(Unsafe.RUNTIME);
        Unsafe.NATIVE.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));

        String reqName = options.name();
        if (options.mode().equals(InterfaceMode.tap())) {
            ifr.ifru_flags.set(TunTap.IFF_TAP | TunTap.IFF_NO_PI | TunTap.IFF_MULTI_QUEUE);
            if (reqName == null) {
                reqName = "tap" + options.id();
            } else {
                reqName = reqName + options.id();
            }
        } else {
            ifr.ifru_flags.set(TunTap.IFF_TUN | TunTap.IFF_NO_PI | TunTap.IFF_MULTI_QUEUE);
            if (reqName == null) {
                reqName = "tap" + options.id();
            } else {
                reqName = reqName + options.id();
            }
        }
        if ((rc = Unsafe.NATIVE.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", reqName)) < 0) {
            Unsafe.NATIVE.close(fd);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = Unsafe.NATIVE.ioctl(fd, TUNSETIFF, Struct.getMemory(ifr))) < 0) {
            Unsafe.NATIVE.close(fd);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        this.name = ifr.ifrn_name.get();

        int sock;
        if ((sock = Unsafe.NATIVE.socket(Socket.AF_INET, Socket.SOCK_DGRAM, Socket.PF_UNSPEC)) < 0) {
            Unsafe.NATIVE.close(fd);
            throw new RuntimeException("Error(" + rc + "): socket()");
        }
        Unsafe.NATIVE.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
        if ((rc = Unsafe.NATIVE.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", this.name)) < 0) {
            Unsafe.NATIVE.close(fd);
            Unsafe.NATIVE.close(sock);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = Unsafe.NATIVE.ioctl(sock, Ioctl.SIOCGIFFLAGS, Struct.getMemory(ifr))) != 0) {
            Unsafe.NATIVE.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        ifr.ifru_flags.set(ifr.ifru_flags.get() | Ifreq.IFF_UP);
        if ((rc = Unsafe.NATIVE.ioctl(sock, Ioctl.SIOCSIFFLAGS, Struct.getMemory(ifr))) != 0) {
            Unsafe.NATIVE.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        Unsafe.NATIVE.close(sock);
        this.id = new Id(options.id(), fd);
    }

    @Override
    public Id id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void read(Buffer buffer) {
        TunTapBuffer ptrBuf = (TunTapBuffer) buffer;
        long written = Unsafe.NATIVE.read(id.fd, ptrBuf.buffer.slice(buffer.writerIndex()), buffer.writeableBytes());
        buffer.writerIndex(buffer.writerIndex() + written);
    }

    @Override
    public void write(Buffer buffer) {
        TunTapBuffer ptrBuf = (TunTapBuffer) buffer;
        long nbytes = Unsafe.NATIVE.write(id.fd, ptrBuf.buffer.slice(buffer.readerIndex()), buffer.readableBytes());
        buffer.readerIndex(buffer.readerIndex() + nbytes);
    }

    @Override
    public void close() {
        Unsafe.NATIVE.close(id.fd);
    }

    public static class Id implements Interface.Id {

        private final int id;
        private final int fd;

        public Id(int id, int fd) {
            this.id = id;
            this.fd = fd;
        }
    }
}
