package tuntap.common.foreign;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class sockaddr extends Struct {

    protected final static int ADDR_LENGTH = 14;

    protected sockaddr(Runtime runtime) {
        super(runtime);
    }

}
