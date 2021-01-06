package tuntap.macos;

import jnr.ffi.Runtime;
import jnr.ffi.*;
import jnr.ffi.annotations.IgnoreError;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;
import jnr.ffi.annotations.Transient;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.types.size_t;
import jnr.ffi.types.socklen_t;
import jnr.ffi.types.ssize_t;

class MacOSJNRNative {

    public static final LibC LIB_C;
    public static final Runtime RUNTIME;

    static {
        Platform platform = Platform.getNativePlatform();
        LibraryLoader<LibC> loader = LibraryLoader.create(LibC.class);
        loader.library(platform.getStandardCLibraryName());
        LIB_C = loader.load();
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

    public static abstract class sockaddr extends Struct {

        public sockaddr(Runtime runtime) {
            super(runtime);
        }
    }

    /*
    struct ctl_info {
        u_int32_t   ctl_id;
        char        ctl_name[MAX_KCTL_NAME];
    };
     */
    public static class ctl_info extends Struct {

        private static final int MAX_KCTL_NAME = 96;

        public final Struct.Unsigned32 ctl_id = new Struct.Unsigned32();
        public final Struct.UTF8String ctl_name = new Struct.UTF8String(MAX_KCTL_NAME);

        public ctl_info(Runtime runtime) {
            super(runtime);
        }
    }

    public static class sockaddr_ctl extends sockaddr {

        private static final int RESERVED = 5;

        public final Struct.Unsigned8 sc_len = new Struct.Unsigned8();
        public final Struct.Unsigned8 sc_family = new Struct.Unsigned8();
        public final Struct.Unsigned16 ss_sysaddr = new Struct.Unsigned16();
        public final Struct.Unsigned32 sc_id = new Struct.Unsigned32();
        public final Struct.Unsigned32 sc_unit = new Struct.Unsigned32();
        public final Struct.Unsigned32[] sc_reserved = array(new Struct.Unsigned32[RESERVED]);

        public sockaddr_ctl(Runtime runtime) {
            super(runtime);
        }
    }

}

