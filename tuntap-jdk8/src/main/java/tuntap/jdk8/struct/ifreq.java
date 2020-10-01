package tuntap.jdk8.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;


public abstract class ifreq extends Struct {

    public ifreq(Runtime runtime) {
        super(runtime);
    }
}
