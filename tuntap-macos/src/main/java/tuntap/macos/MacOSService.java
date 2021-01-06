package tuntap.macos;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.option.InterfaceOptions;
import tuntap.Service;

public class MacOSService implements Service {

    @Override
    public String name() {
        return "tuntap-macos";
    }

    @Override
    public Interface create(Interface.Options options) {
        InterfaceOptions opts = (InterfaceOptions) options;
        if (opts.mode() instanceof Interface.Options.Tap) {
            throw new UnsupportedOperationException();
        }
        return new MacOSJNRTunInterface(opts);
    }

    @Override
    public Buffer allocate(long size) {
        Pointer ptr = Memory.allocateDirect(MacOSJNRNative.RUNTIME, size);
        return new MacOSJNRBuffer(ptr, size);
    }
}
