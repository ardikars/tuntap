package tuntap.api;

import tuntap.Interface;
import tuntap.Service;
import tuntap.api.option.TunTapOption;

public class TunTapService implements Service {

    @Override
    public String name() {
        return "tuntap";
    }

    @Override
    public Interface create(Interface.Option option) {
        TunTapOption opt = (TunTapOption) option;
        return new TunTapInterface(opt);
    }
}
