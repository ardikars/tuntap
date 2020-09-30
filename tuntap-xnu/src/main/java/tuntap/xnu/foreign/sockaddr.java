package tuntap.xnu.foreign;

import jnr.ffi.Runtime;

public class sockaddr extends tuntap.common.foreign.sockaddr {

    public final Unsigned8 sa_len = new Unsigned8();
    public final Unsigned8 sa_family = new Unsigned8();
    public final Signed8[] sa_data = array(new Signed8[ADDR_LENGTH]);

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
