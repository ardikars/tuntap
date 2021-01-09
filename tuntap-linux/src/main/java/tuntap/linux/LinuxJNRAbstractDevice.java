package tuntap.linux;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Device;

abstract class LinuxJNRAbstractDevice implements Device {

    static final int AF_INET = 2;
    static final int AF_INET6 = 10;
    static final int SOCK_DGRAM = 2;
    static final int SOCK_STREAM = 1;
    static final int PF_UNSPEC = 0;

    static final int O_RDWR = 2;
    static final int TUNSETIFF = 1074025674;

    static final int IFF_TUN = 0x0001;
    static final int IFF_TAP = 0x0002;
    static final int IFF_NO_PI = 0x1000;
    static final int IFF_MULTI_QUEUE = 0x0100;

    static final int SIOCGIFFLAGS = 0x8913; /* get flags                    */
    static final int SIOCSIFFLAGS = 0x8914; /* set flags                    */

    static final int IFF_UP = 1;

    protected final FileDescriptor id;
    protected final String name;
    protected final String mode;

    public LinuxJNRAbstractDevice(Device.Options opts) {
        int fd;
        if ((fd = LinuxJNRNative.LIB_C.open("/dev/net/tun", O_RDWR)) < 0) {
            throw new RuntimeException();
        }
        int rc;
        LinuxJNRNative.ifreq ifr = new LinuxJNRNative.ifreq(LinuxJNRNative.RUNTIME);
        LinuxJNRNative.LIB_C.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));

        String reqName = requiredName(ifr, opts.name());

        if ((rc = LinuxJNRNative.LIB_C.snprintf(ifr.ifrn_name.getMemory(), LinuxJNRNative.ifreq.IFNAMSIZ, "%s", reqName)) < 0) {
            LinuxJNRNative.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = LinuxJNRNative.LIB_C.ioctl(fd, TUNSETIFF, Struct.getMemory(ifr))) < 0) {
            LinuxJNRNative.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        this.name = ifr.ifrn_name.get();

        int sock;
        if ((sock = LinuxJNRNative.LIB_C.socket(AF_INET, SOCK_DGRAM, PF_UNSPEC)) < 0) {
            LinuxJNRNative.LIB_C.close(fd);
            throw new RuntimeException("Error(" + rc + "): socket()");
        }
        LinuxJNRNative.LIB_C.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
        if ((rc = LinuxJNRNative.LIB_C.snprintf(ifr.ifrn_name.getMemory(), LinuxJNRNative.ifreq.IFNAMSIZ, "%s", this.name)) < 0) {
            LinuxJNRNative.LIB_C.close(fd);
            LinuxJNRNative.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): snprintf()");
        }
        if ((rc = LinuxJNRNative.LIB_C.ioctl(sock, SIOCGIFFLAGS, Struct.getMemory(ifr))) != 0) {
            LinuxJNRNative.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        ifr.ifru_flags.set(ifr.ifru_flags.get() | IFF_UP);
        if ((rc = LinuxJNRNative.LIB_C.ioctl(sock, SIOCSIFFLAGS, Struct.getMemory(ifr))) != 0) {
            LinuxJNRNative.LIB_C.close(sock);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        LinuxJNRNative.LIB_C.close(sock);
        this.id = new FileDescriptor(fd);
        this.mode = opts.mode().name();
    }

    protected abstract String requiredName(LinuxJNRNative.ifreq ifreq, String name);

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
        LinuxJNRBuffer ptrBuf = (LinuxJNRBuffer) buffer;
        long written = LinuxJNRNative.LIB_C.read(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.readerIndex()), buffer.writeableBytes());
        buffer.writerIndex(buffer.writerIndex() + written);
        return written;
    }

    @Override
    public long write(Buffer buffer) {
        LinuxJNRBuffer ptrBuf = (LinuxJNRBuffer) buffer;
        long nbytes = LinuxJNRNative.LIB_C.write(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.writerIndex()), buffer.readableBytes());
        buffer.readerIndex(buffer.readerIndex() + nbytes);
        return nbytes;
    }

    @Override
    public void close() {
        LinuxJNRNative.LIB_C.close(id.fd);
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
