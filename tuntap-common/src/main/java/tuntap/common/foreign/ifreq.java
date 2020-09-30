package tuntap.common.foreign;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class ifreq extends Struct {

    public static final int IF_NAMESIZE = 16;
    public static final int IFNAMSIZ = IF_NAMESIZE;

    protected ifreq(Runtime runtime) {
        super(runtime);
    }

    public abstract int size();
}
