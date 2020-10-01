package tuntap.jdk8.platform.xnu.struct;

import jnr.ffi.Runtime;

public class sockaddr extends tuntap.jdk8.struct.sockaddr {

    public final Unsigned8 sa_len = new Unsigned8();
    public final Unsigned8 sa_family = new Unsigned8();
    public final Signed8[] sa_data = array(new Signed8[14]);

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
