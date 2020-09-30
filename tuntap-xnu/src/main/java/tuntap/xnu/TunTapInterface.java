package tuntap.xnu;

import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.InterfaceOptions;
import tuntap.common.foreign.Unsafe;
import tuntap.common.foreign.constant.Socket;
import tuntap.xnu.foreign.constant.SysDomain;
import tuntap.xnu.foreign.constant.UTun;
import tuntap.xnu.foreign.ctl_info;
import tuntap.xnu.foreign.sockaddr_ctl;

public class TunTapInterface implements Interface {

    private final Id id;
    private final String name;

    public TunTapInterface(Options opt) {
        InterfaceOptions options = (InterfaceOptions) opt;
        int fd;
        int rc;
        if ((fd = Unsafe.NATIVE.socket(Socket.PF_SYSTEM, Socket.SOCK_DGRAM, SysDomain.SYSPROTO_CONTROL)) < 0) {
            throw new RuntimeException();
        }
        long CTLIOCGINFO = 3227799043L;
        ctl_info info = new ctl_info(Unsafe.RUNTIME);
        Unsafe.NATIVE.memset(Struct.getMemory(info), 0, Struct.size(info));
        info.ctl_name.set(UTun.UTUN_CONTROL_NAME);
        if ((rc = Unsafe.NATIVE.ioctl(fd, CTLIOCGINFO, Struct.getMemory(info))) < 0) {
            Unsafe.NATIVE.close(rc);
            throw new RuntimeException("Error(" + rc + "): ioctl()");
        }
        sockaddr_ctl addr = new sockaddr_ctl(Unsafe.RUNTIME);
        addr.sc_id.set(info.ctl_id.longValue());
        addr.sc_len.set(Struct.size(addr));
        addr.sc_family.set(Socket.AF_SYSTEM);
        addr.ss_sysaddr.set(SysDomain.AF_SYS_CONTROL);
        addr.sc_unit.set(options.id() + 1);
        if ((rc = Unsafe.NATIVE.connect(fd, addr, Struct.size(addr))) < 0) {
            Unsafe.NATIVE.close(fd);
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
