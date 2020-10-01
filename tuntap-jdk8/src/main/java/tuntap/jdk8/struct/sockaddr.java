package tuntap.jdk8.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;


public abstract class sockaddr extends Struct {

    public sockaddr(Runtime runtime) {
        super(runtime);
    }
}
