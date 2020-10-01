package tuntap.jdk8.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;


public abstract class ctl_info extends Struct {

    public ctl_info(Runtime runtime) {
        super(runtime);
    }
}
