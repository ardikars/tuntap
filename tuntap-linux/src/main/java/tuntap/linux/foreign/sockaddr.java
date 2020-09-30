package tuntap.linux.foreign;

import jnr.ffi.Runtime;

public class sockaddr extends tuntap.common.foreign.sockaddr {

    public final Unsigned16 sa_family = new Unsigned16();
    public final Signed8[] sa_data = array(new Signed8[ADDR_LENGTH]);

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
