package tuntap.linux;

import jnr.ffi.Pointer;
import tuntap.Device;
import tuntap.Selector;

class LinuxJNRSelector implements Selector<Device> {

    private Pointer pointer;

    @Override
    public Iterable<Device> select(long timeout, TimeoutPrecision precision) {
        return null;
    }

    @Override
    public Selector<Device> register(Device source, InterestOperation operation) {
        return null;
    }
}
