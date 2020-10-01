package tuntap.jdk8;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.types.size_t;
import jnr.ffi.types.socklen_t;
import jnr.ffi.types.ssize_t;
import tuntap.jdk8.struct.sockaddr;

public class Unsafe {

    public static final Posix POSIX;
    public static final Runtime RUNTIME;

    static {
        POSIX = LibraryLoader.create(Posix.class).load("c");
        RUNTIME = Runtime.getRuntime(POSIX);
    }

    public interface Posix {

        int socket(int domain, int type, int protocol);

        int bind(int sockfd, @In sockaddr addr, @socklen_t long addrlen);

        int connect(int socket, @In @Transient sockaddr address, @socklen_t long address_len);

        int open(String pathname, int flags);

        int close(int fd);

        int ioctl(int fd, long request, Object... args);

        Pointer memset(Pointer s, int c, @size_t long n);

        void free(Pointer ptr);

        @ssize_t
        long read(int fd, @Out Pointer buf, @size_t long count);

        @ssize_t
        long recvfrom(int sockfd, @Out Pointer buf, @size_t long len, int flags, @Out sockaddr src_addr, IntByReference addrlen);

        @ssize_t
        long write(int fd, @In Pointer buf, @size_t long count);

        @ssize_t
        long sendto(int sockfd, @In Pointer buf, @size_t long size, int flags, @In @Transient sockaddr dst_addr, @socklen_t int addrlen);

        int snprintf(Pointer str, @size_t long size, String format, Object... args);

        Pointer memcpy(@Out Pointer dest, @In Pointer src, @size_t long n);
    }
}
