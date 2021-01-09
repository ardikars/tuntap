package tuntap.macos;

import tuntap.Device;
import tuntap.Selector;

class MacOSJNRSelector implements Selector<Device> {

    @Override
    public Iterable<Device> select(long timeout, TimeoutPrecision precision) {
        return null;
    }

    @Override
    public Selector<Device> register(Device source, InterestOperation operation) {
        return null;
    }
}
