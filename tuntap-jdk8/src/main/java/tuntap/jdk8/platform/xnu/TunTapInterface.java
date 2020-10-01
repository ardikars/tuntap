package tuntap.jdk8.platform.xnu;

import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.InterfaceOptions;
import tuntap.jdk8.Unsafe;
import tuntap.jdk8.buffer.TunTapBuffer;
import tuntap.jdk8.constant.Socket;
import tuntap.jdk8.constant.SysDomain;
import tuntap.jdk8.constant.UTun;
import tuntap.jdk8.platform.xnu.struct.ctl_info;
import tuntap.jdk8.platform.xnu.struct.sockaddr_ctl;

public class TunTapInterface implements Interface {

    private final Id id;
    private final String name;

    public TunTapInterface(Options opt) {
        InterfaceOptions options = (InterfaceOptions) opt;
        int fd;
        int rc;
        if ((fd = Unsafe.POSIX.socket(Socket.PF_SYSTEM, Socket.SOCK_DGRAM, SysDomain.SYSPROTO_CONTROL)) < 0) {
            throw new RuntimeException();
        }
        long CTLIOCGINFO = 3227799043L;
        ctl_info info = new ctl_info(Unsafe.RUNTIME);
        Unsafe.POSIX.memset(Struct.getMemory(info), 0, Struct.size(info));
        info.ctl_name.set(UTun.UTUN_CONTROL_NAME);
        if ((rc = Unsafe.POSIX.ioctl(fd, CTLIOCGINFO, Struct.getMemory(info))) < 0) {
            Unsafe.POSIX.close(rc);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        sockaddr_ctl addr = new sockaddr_ctl(Unsafe.RUNTIME);
        addr.sc_id.set(info.ctl_id.longValue());
        addr.sc_len.set(Struct.size(addr));
        addr.sc_family.set(Socket.AF_SYSTEM);
        addr.ss_sysaddr.set(SysDomain.AF_SYS_CONTROL);
        addr.sc_unit.set(options.id() + 1);
        if ((rc = Unsafe.POSIX.connect(fd, addr, Struct.size(addr))) < 0) {
            Unsafe.POSIX.close(fd);
            throw new RuntimeException("Error(" + rc + "): connect()");
        }
        this.name = "utun" + (addr.sc_unit.get() - 1);
        this.id = new Id((int) addr.sc_unit.get(), fd);
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
        long written = Unsafe.POSIX.read(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.readerIndex()), buffer.writeableBytes());
        buffer.writerIndex(buffer.writerIndex() + written);
    }

    @Override
    public void write(Buffer buffer) {
        TunTapBuffer ptrBuf = (TunTapBuffer) buffer;
        long nbytes = Unsafe.POSIX.write(id.fd, ptrBuf.cast(Pointer.class).slice(buffer.writerIndex()), buffer.readableBytes());
        buffer.readerIndex(buffer.readerIndex() + nbytes);
    }

    @Override
    public void close() {
        Unsafe.POSIX.close(id.fd);
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
