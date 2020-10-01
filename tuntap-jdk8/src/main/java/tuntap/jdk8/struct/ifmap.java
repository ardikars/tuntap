package tuntap.jdk8.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;


public abstract class ifmap extends Struct {

    public ifmap(Runtime runtime) {
        super(runtime);
    }
}
