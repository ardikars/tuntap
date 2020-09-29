package tuntap.api.foreign;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public class sockaddr extends Struct {

    public final static int ADDR_LENGTH = 14;

    public final Unsigned16 sa_family = new Unsigned16();
    public final Signed8[] sa_data = array(new Signed8[ADDR_LENGTH]);

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
