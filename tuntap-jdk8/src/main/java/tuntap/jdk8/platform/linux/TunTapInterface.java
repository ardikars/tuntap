package tuntap.jdk8.platform.linux;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.InterfaceMode;
import tuntap.InterfaceOptions;
import tuntap.jdk8.Unsafe;
import tuntap.jdk8.buffer.TunTapBuffer;
import tuntap.jdk8.constant.Ifreq;
import tuntap.jdk8.constant.Ioctl;
import tuntap.jdk8.constant.Socket;
import tuntap.jdk8.constant.TunTap;
import tuntap.jdk8.platform.linux.struct.ifreq;

public class TunTapInterface implements Interface {

    public static final int O_RDWR = 2;

    private final Id id;
    private final String name;

    public TunTapInterface(Options opt) {
        InterfaceOptions options = (InterfaceOptions) opt;
        int fd;
        int rc;
        if ((fd = Unsafe.LIB_C.open("/dev/net/tun", O_RDWR)) < 0) {
            throw new RuntimeException();
        }
        int TUNSETIFF = 1074025674;
        ifreq ifr = new ifreq(Unsafe.RUNTIME);
        Unsafe.LIB_C.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));

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
        if ((rc = Unsafe.LIB_C.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", reqName)) < 0) {
            Unsafe.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = Unsafe.LIB_C.ioctl(fd, TUNSETIFF, Struct.getMemory(ifr))) < 0) {
            Unsafe.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        this.name = ifr.ifrn_name.get();

        int sock;
        if ((sock = Unsafe.LIB_C.socket(Socket.AF_INET, Socket.SOCK_DGRAM, Socket.PF_UNSPEC)) < 0) {
            Unsafe.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): socket()");
        }
        Unsafe.LIB_C.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
        if ((rc = Unsafe.LIB_C.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", this.name)) < 0) {
            Unsafe.LIB_C.close(fd);
            Unsafe.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = Unsafe.LIB_C.ioctl(sock, Ioctl.SIOCGIFFLAGS, Struct.getMemory(ifr))) != 0) {
            Unsafe.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        ifr.ifru_flags.set(ifr.ifru_flags.get() | Ifreq.IFF_UP);
        if ((rc = Unsafe.LIB_C.ioctl(sock, Ioctl.SIOCSIFFLAGS, Struct.getMemory(ifr))) != 0) {
            Unsafe.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        Unsafe.LIB_C.close(sock);
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
        long written = Unsafe.LIB_C.read(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.readerIndex()), buffer.writeableBytes());
        buffer.writerIndex(buffer.writerIndex() + written);
    }

    @Override
    public void write(Buffer buffer) {
        TunTapBuffer ptrBuf = (TunTapBuffer) buffer;
        long nbytes = Unsafe.LIB_C.write(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.writerIndex()), buffer.readableBytes());
        buffer.readerIndex(buffer.readerIndex() + nbytes);
    }

    @Override
    public void close() {
        Unsafe.LIB_C.close(id.fd);
    }

    public static class Id implements Interface.Id {

        private final int id;
        private final int fd;

        public Id(int id, int fd) {
            this.id = id;
            this.fd = fd;
        }

        @Override
        public Object handle() {
            return fd;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "id=" + id +
                    ", fd=" + fd +
                    '}';
        }
    }
}
