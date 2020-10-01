package tuntap.jdk8.platform.linux.struct;

import jnr.ffi.Runtime;

public class sockaddr extends tuntap.jdk8.struct.sockaddr {

    public final Unsigned16 sa_family = new Unsigned16();
    public final Signed8[] sa_data = array(new Signed8[14]);

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
