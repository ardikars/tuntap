/**
 * This code is licenced under the BSD-3-Clause.
 */
package tuntap.api;

import jnr.ffi.Platform;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.api.buffer.DirectBuffer;
import tuntap.api.foreign.Unsafe;
import tuntap.api.foreign.constant.Ifreq;
import tuntap.api.foreign.constant.Ioctl;
import tuntap.api.foreign.constant.Socket;
import tuntap.api.foreign.constant.Tun;
import tuntap.api.foreign.ifreq;
import tuntap.api.option.TunTapOption;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TunTapInterface implements Interface, AutoCloseable {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static final int O_RDWR = 2;

    private final int fd;

    private final String name;

    public TunTapInterface(TunTapOption option) {
        if (Platform.getNativePlatform().isUnix()) {
            int rc;
            this.fd = Unsafe.NATIVE.open("/dev/net/tun", O_RDWR);
            if (fd < 0) {
                throw new RuntimeException();
            }
            int TUNSETIFF = 1074025674;
            ifreq ifr = new ifreq(Unsafe.RUNTIME);
            Unsafe.NATIVE.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
            if ((rc = Unsafe.NATIVE.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", option.name())) < 0) {
                Unsafe.NATIVE.close(fd);
                throw new RuntimeException("Error(" + rc + "): snprintf()");
            }
            if (option.mode() == TunTapOption.Mode.TAP) {
                ifr.ifru_flags.set(Tun.IFF_TAP | Tun.IFF_NO_PI);
            } else {
                ifr.ifru_flags.set(Tun.IFF_TUN | Tun.IFF_NO_PI);
            }
            if ((rc = Unsafe.NATIVE.ioctl(fd, TUNSETIFF, Struct.getMemory(ifr))) < 0) {
                Unsafe.NATIVE.close(fd);
                throw new RuntimeException("Error(" + rc + "): ioctl()");
            }
            this.name = ifr.ifrn_name.get();
        } else {
            fd = 0;
            this.name = null;
        }
    }

    @Override
    public String name() {
        readLock.lock();
        try {
            return name;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Interface up() {
        int rc;
        int sock;
        writeLock.lock();
        try {
            sock = Unsafe.NATIVE.socket(Socket.AF_INET, Socket.SOCK_DGRAM, Socket.PF_UNSPEC);
            ifreq ifr = new ifreq(Unsafe.RUNTIME);
            Unsafe.NATIVE.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
            if ((rc = Unsafe.NATIVE.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", name())) < 0) {
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
        } finally {
            writeLock.unlock();
        }
        return this;
    }

    @Override
    public Interface down() {
        int rc;
        int sock;
        writeLock.lock();
        try {
            sock = Unsafe.NATIVE.socket(Socket.AF_INET, Socket.SOCK_DGRAM, Socket.PF_UNSPEC);
            ifreq ifr = new ifreq(Unsafe.RUNTIME);
            Unsafe.NATIVE.memset(Struct.getMemory(ifr), 0, Struct.size(ifr));
            if ((rc = Unsafe.NATIVE.snprintf(ifr.ifrn_name.getMemory(), ifreq.IFNAMSIZ, "%s", name())) < 0) {
                Unsafe.NATIVE.close(sock);
                throw new RuntimeException("Error(" + rc + "): snprintf()");
            }
            if ((rc = Unsafe.NATIVE.ioctl(sock, Ioctl.SIOCGIFFLAGS, Struct.getMemory(ifr))) != 0) {
                Unsafe.NATIVE.close(sock);
                throw new RuntimeException("Error(" + rc + "): ioctl()");
            }
            ifr.ifru_flags.set(ifr.ifru_flags.get() & ~Ifreq.IFF_UP);
            if ((rc = Unsafe.NATIVE.ioctl(sock, Ioctl.SIOCSIFFLAGS, Struct.getMemory(ifr))) != 0) {
                Unsafe.NATIVE.close(sock);
                throw new RuntimeException("Error(" + rc + "): ioctl()");
            }
            Unsafe.NATIVE.close(sock);
        } finally {
            writeLock.unlock();
        }
        return this;
    }

    @Override
    public void read(Buffer buffer) {
        writeLock.unlock();
        DirectBuffer directBuffer = (DirectBuffer) buffer;
        try {
            Unsafe.NATIVE.read(fd, directBuffer.buffer(Pointer.class), buffer.capacity() - buffer.writerIndex());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void write(Buffer buffer) {
        writeLock.unlock();
        DirectBuffer directBuffer = (DirectBuffer) buffer;
        try {
            Unsafe.NATIVE.write(fd, directBuffer.buffer(Pointer.class), buffer.writerIndex() - buffer.readerIndex());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void close() {
        writeLock.lock();
        try {
            Unsafe.NATIVE.close(fd);
        } finally {
            writeLock.unlock();
        }
    }
}
