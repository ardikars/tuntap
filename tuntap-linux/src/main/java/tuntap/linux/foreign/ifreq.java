package tuntap.linux.foreign;

import jnr.ffi.Runtime;

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
public class ifreq extends tuntap.common.foreign.ifreq {

    public final UTF8String ifrn_name = new UTF8String(IFNAMSIZ);
    public final tuntap.common.foreign.sockaddr ifru_addr;
    public final tuntap.common.foreign.sockaddr ifru_dstaddr;
    public final tuntap.common.foreign.sockaddr ifru_broadaddr;
    public final tuntap.common.foreign.sockaddr ifru_netmask;
    public final sockaddr ifru_hwaddr;
    public final Signed16 ifru_flags = new Signed16();
    public final Signed32 ifru_ivalue = new Signed32();
    public final Signed32 ifru_mtu = new Signed32();
    public final ifmap ifru_map;
    public final Signed8[] ifru_slave = array(new Signed8[IFNAMSIZ]);
    public final Signed8[] ifru_newname = array(new Signed8[IFNAMSIZ]);
    public final Pointer ifru_data = new Pointer();

    public ifreq(Runtime runtime) {
        super(runtime);
        this.ifru_addr = inner(new sockaddr(runtime));
        this.ifru_dstaddr = inner(new sockaddr(runtime));
        this.ifru_broadaddr = inner(new sockaddr(runtime));
        this.ifru_netmask = inner(new sockaddr(runtime));
        this.ifru_hwaddr = inner(new sockaddr(runtime));
        this.ifru_map = inner(new ifmap(runtime));
    }

    @Override
    public int size() {
        return size(this);
    }
}
