package tuntap.jdk8.struct;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;


public abstract class sockaddr_ctl extends Struct {

    public sockaddr_ctl(Runtime runtime) {
        super(runtime);
    }
}
