package tuntap.linux;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.*;
import tuntap.option.InterfaceOptions;

import java.net.InetSocketAddress;

public class LinuxService implements Service {

    @Override
    public String name() {
        return "tuntap-linux";
    }

    @Override
    public Device device(Device.Options options) {
        InterfaceOptions opts = (InterfaceOptions) options;
        if (opts.mode() instanceof Device.Options.Tap) {
            return new LinuxJNRTapDevice(opts);
        } else {
            return new LinuxJNRTunDevice(opts);
        }
    }

    @Override
    public Socket.Tcp stream(InetSocketAddress bindAddress, Socket.Tcp.Options options) {
        return new LinuxJNRTcpSocket(bindAddress, options);
    }

    @Override
    public Socket.Udp datagram(InetSocketAddress bindAddress, Socket.Udp.Options options) {
        return new LinuxJNRUdpSocket(bindAddress, options);
    }

    @Override
    public Buffer allocate(long size) {
        Pointer ptr = Memory.allocateDirect(LinuxJNRNative.RUNTIME, size);
        return new LinuxJNRBuffer(ptr, size);
    }

    @Override
    public <T extends Selector.Selectable> Selector<T> selector(Class<T> type) {
        if (type.isAssignableFrom(Device.class)) {
            return (Selector<T>) new LinuxJNRSelector();
        }
        return null;
    }
}
