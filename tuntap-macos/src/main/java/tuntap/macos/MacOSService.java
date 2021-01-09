package tuntap.macos;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.*;
import tuntap.option.InterfaceOptions;

import java.net.InetSocketAddress;

public class MacOSService implements Service {

    @Override
    public String name() {
        return "tuntap-macos";
    }

    @Override
    public Device device(Device.Options options) {
        InterfaceOptions opts = (InterfaceOptions) options;
        if (opts.mode() instanceof Device.Options.Tap) {
            throw new UnsupportedOperationException();
        }
        return new MacOSJNRTunDevice(opts);
    }

    @Override
    public Socket.Tcp stream(InetSocketAddress bindAddress, Socket.Tcp.Options options) {
        return null;
    }

    @Override
    public Socket.Udp datagram(InetSocketAddress bindAddress, Socket.Udp.Options options) {
        return null;
    }

    @Override
    public Buffer allocate(long size) {
        Pointer ptr = Memory.allocateDirect(MacOSJNRNative.RUNTIME, size);
        return new MacOSJNRBuffer(ptr, size);
    }

    @Override
    public <T extends Selector.Selectable> Selector<T> selector(Class<T> type) {
        if (type.isAssignableFrom(Device.class)) {
            return (Selector<T>) new MacOSJNRSelector();
        }
        return null;
    }
}
