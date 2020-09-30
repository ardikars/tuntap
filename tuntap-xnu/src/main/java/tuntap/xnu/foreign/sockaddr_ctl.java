package tuntap.xnu.foreign;

import jnr.ffi.Runtime;

public class sockaddr_ctl extends tuntap.common.foreign.sockaddr {

    private static final int RESERVED = 5;

    public final Unsigned8 sc_len = new Unsigned8();
    public final Unsigned8 sc_family = new Unsigned8();
    public final Unsigned16 ss_sysaddr = new Unsigned16();
    public final Unsigned32 sc_id = new Unsigned32();
    public final Unsigned32 sc_unit = new Unsigned32();
    public final Unsigned32[] sc_reserved = array(new Unsigned32[RESERVED]);

    public sockaddr_ctl(Runtime runtime) {
        super(runtime);
    }
}
