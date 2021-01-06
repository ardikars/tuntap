package tuntap.linux;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.*;
import tuntap.option.InterfaceOptions;

public class LinuxService implements Service {

    @Override
    public String name() {
        return "tuntap-linux";
    }

    @Override
    public Interface create(Interface.Options options) {
        InterfaceOptions opts = (InterfaceOptions) options;
        if (opts.mode() instanceof Interface.Options.Tap) {
            return new LinuxJNRTapInterface(opts);
        } else {
            return new LinuxJNRTunInterface(opts);
        }
    }

    @Override
    public Buffer allocate(long size) {
        Pointer ptr = Memory.allocateDirect(LinuxJNRNative.RUNTIME, size);
        return new LinuxJNRBuffer(ptr, size);
    }
}
