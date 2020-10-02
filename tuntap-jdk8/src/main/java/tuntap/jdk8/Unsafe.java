package tuntap.jdk8;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.provider.LoadedLibrary;
import jnr.ffi.types.size_t;
import jnr.ffi.types.socklen_t;
import jnr.ffi.types.ssize_t;
import tuntap.jdk8.struct.sockaddr;

public class Unsafe {

    public static final LibC LIB_C;
    public static final Runtime RUNTIME;

    static {
        Platform platform = Platform.getNativePlatform();
        LibraryLoader<LibC> loader = LibraryLoader.create(LibC.class);
        loader.library(platform.getStandardCLibraryName());

        if (platform.getOS() == Platform.OS.SOLARIS) {
            loader.library("socket");
        }

        if (platform.getOS() == Platform.OS.WINDOWS) {
            WinLibCAdapter.LibMSVCRT mslib = LibraryLoader.create(WinLibCAdapter.LibMSVCRT.class).load(platform.getStandardCLibraryName());
            LIB_C = new WinLibCAdapter(mslib);
        } else {
            LIB_C = loader.load();
        }
        RUNTIME = Runtime.getRuntime(LIB_C);
    }

    public interface LibC {

        int socket(int domain, int type, int protocol);

        int bind(int sockfd, @In sockaddr addr, @socklen_t long addrlen);

        int connect(int sockfd, @In @Transient sockaddr address, @socklen_t long address_len);

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

        @IgnoreError
        String strerror(int error);
    }

    private static final class WinLibCAdapter implements LibC, LoadedLibrary {

        private final LibMSVCRT win;

        public WinLibCAdapter(LibMSVCRT winlibc) {
            this.win = winlibc;

        }

        @Override
        public Runtime getRuntime() {
            return Runtime.getRuntime(win);
        }

        @Override
        public int socket(int domain, int type, int protocol) {
            return win.socket(domain, type, protocol);
        }

        @Override
        public int bind(int sockfd, sockaddr addr, long addrlen) {
            return win.bind(sockfd, addr, addrlen);
        }

        @Override
        public int connect(int sockfd, sockaddr address, long address_len) {
            return win.connect(sockfd, address, address_len);
        }

        @Override
        public int open(String pathname, int flags) {
            return win._open(pathname, flags);
        }

        @Override
        public int close(int fd) {
            return win._close(fd);
        }

        @Override
        public int ioctl(int fd, long request, Object... args) {
            return win.ioctl(fd, request, args);
        }

        @Override
        public Pointer memset(Pointer s, int c, long n) {
            return win.memset(s, c, n);
        }

        @Override
        public void free(Pointer ptr) {
            win.free(ptr);
        }

        @Override
        public long read(int fd, Pointer buf, long count) {
            return win._read(fd, buf, count);
        }

        @Override
        public long recvfrom(int sockfd, Pointer buf, long len, int flags, sockaddr src_addr, IntByReference addrlen) {
            return win.recvfrom(sockfd, buf, len, flags, src_addr, addrlen);
        }

        @Override
        public long write(int fd, Pointer buf, long count) {
            return win._write(fd, buf, count);
        }

        @Override
        public long sendto(int sockfd, Pointer buf, long size, int flags, sockaddr dst_addr, int addrlen) {
            return win.sendto(sockfd, buf, size, flags, dst_addr, addrlen);
        }

        @Override
        public int snprintf(Pointer str, long size, String format, Object... args) {
            return win.snprintf(str, size, format, args);
        }

        @Override
        public Pointer memcpy(Pointer dest, Pointer src, long n) {
            return win.memcpy(dest, src, n);
        }

        @Override
        public String strerror(int error) {
            return win.strerror(error);
        }

        public interface LibMSVCRT {

            int socket(int domain, int type, int protocol);

            int bind(int sockfd, @In sockaddr addr, @socklen_t long addrlen);

            int connect(int sockfd, @In @Transient sockaddr address, @socklen_t long address_len);

            int _open(String pathname, int flags);

            int _close(int fd);

            int ioctl(int fd, long request, Object... args);

            Pointer memset(Pointer s, int c, @size_t long n);

            void free(Pointer ptr);

            @ssize_t
            long _read(int fd, @Out Pointer buf, @size_t long count);

            @ssize_t
            long recvfrom(int sockfd, @Out Pointer buf, @size_t long len, int flags, @Out sockaddr src_addr, IntByReference addrlen);

            @ssize_t
            long _write(int fd, @In Pointer buf, @size_t long count);

            @ssize_t
            long sendto(int sockfd, @In Pointer buf, @size_t long size, int flags, @In @Transient sockaddr dst_addr, @socklen_t int addrlen);

            int snprintf(Pointer str, @size_t long size, String format, Object... args);

            Pointer memcpy(@Out Pointer dest, @In Pointer src, @size_t long n);

            @IgnoreError
            String strerror(int error);
        }
    }
}
