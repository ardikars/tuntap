package tuntap.jdk8.platform.linux;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.Buffer;
import tuntap.Interface;
import tuntap.Service;
import tuntap.jdk8.Unsafe;
import tuntap.jdk8.buffer.TunTapBuffer;

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
        Pointer ptr = Memory.allocateDirect(Unsafe.RUNTIME, size);
        return new TunTapBuffer(ptr, size);
    }
}
