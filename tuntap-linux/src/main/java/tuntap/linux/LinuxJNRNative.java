package tuntap.linux;

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

class LinuxJNRNative {

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

    public static class sockaddr_impl extends sockaddr {

        public final Struct.Unsigned16 sa_family = new Struct.Unsigned16();
        public final Struct.Signed8[] sa_data = array(new Struct.Signed8[14]);

        public sockaddr_impl(Runtime runtime) {
            super(runtime);
        }
    }

    /*
    struct ifmap {
        unsigned long int mem_start;
        unsigned long int mem_end;
        unsigned short int base_addr;
        unsigned char irq;
        unsigned char dma;
        unsigned char port;
    }
    */
    public static class ifmap extends Struct {

        public final NativeLong mem_start = new NativeLong(0);
        public final NativeLong mem_end = new NativeLong(0);
        public final Struct.Unsigned16 base_addr = new Struct.Unsigned16();
        public final Struct.Unsigned8 irq = new Struct.Unsigned8();
        public final Struct.Unsigned8 dma = new Struct.Unsigned8();
        public final Struct.Unsigned8 port = new Struct.Unsigned8();

        public ifmap(Runtime runtime) {
            super(runtime);
        }
    }

    /*
    struct ifreq {
    # define IFHWADDRLEN    6
    # define IFNAMSIZ       IF_NAMESIZE
        union {
            char ifrn_name[IFNAMSIZ];
        } ifr_ifrn;
        union {
            struct sockaddr ifru_addr;
            struct sockaddr ifru_dstaddr;
            struct sockaddr ifru_broadaddr;
            struct sockaddr ifru_netmask;
            struct sockaddr ifru_hwaddr;
            short int ifru_flags;
            int ifru_ivalue;
            int ifru_mtu;
            struct ifmap ifru_map;
            char ifru_slave[IFNAMSIZ];
            char ifru_newname[IFNAMSIZ];
            __caddr_t ifru_data;
        } ifr_ifru;
    };
    */
    public static class ifreq extends Struct {

        public static final int IF_NAMESIZE = 16;
        public static final int IFNAMSIZ = IF_NAMESIZE;

        public final Struct.UTF8String ifrn_name = new Struct.UTF8String(IFNAMSIZ);
        public final sockaddr ifru_addr;
        public final sockaddr ifru_dstaddr;
        public final sockaddr ifru_broadaddr;
        public final sockaddr ifru_netmask;
        public final sockaddr ifru_hwaddr;
        public final Struct.Signed16 ifru_flags = new Struct.Signed16();
        public final Struct.Signed32 ifru_ivalue = new Struct.Signed32();
        public final Struct.Signed32 ifru_mtu = new Struct.Signed32();
        public final ifmap ifru_map;
        public final Struct.Signed8[] ifru_slave = array(new Struct.Signed8[IFNAMSIZ]);
        public final Struct.Signed8[] ifru_newname = array(new Struct.Signed8[IFNAMSIZ]);
        public final Struct.Pointer ifru_data = new Struct.Pointer();

        public ifreq(Runtime runtime) {
            super(runtime);
            this.ifru_addr = inner(new sockaddr_impl(runtime));
            this.ifru_dstaddr = inner(new sockaddr_impl(runtime));
            this.ifru_broadaddr = inner(new sockaddr_impl(runtime));
            this.ifru_netmask = inner(new sockaddr_impl(runtime));
            this.ifru_hwaddr = inner(new sockaddr_impl(runtime));
            this.ifru_map = inner(new ifmap(runtime));
        }
    }


}

