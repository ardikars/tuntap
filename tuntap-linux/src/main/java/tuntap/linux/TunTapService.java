package tuntap.linux;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.Service;
import tuntap.common.foreign.Unsafe;

public class TunTapService implements Service {

    @Override
    public String name() {
        return "tuntap-linux";
    }

    @Override
    public Interface create(Interface.Options option) {
        return new TunTapInterface(option);
    }

    @Override
    public Buffer allocate(long size) {
        Pointer ptr = Memory.allocate(Unsafe.RUNTIME, (int) size);
        return new TunTapBuffer(ptr, size);
    }
}
