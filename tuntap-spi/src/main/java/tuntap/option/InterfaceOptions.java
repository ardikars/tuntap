package tuntap.option;

import tuntap.Device;

import java.net.InetAddress;

public class InterfaceOptions implements Device.Options {

    private String name;
    private Device.Mode mode;

    public static TunInterface tun() {
        return new TunInterface();
    }

    public static TapInterface tap() {
        return new TapInterface();
    }

    @Override
    public String name() {
        return name;
    }

    public InterfaceOptions name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Device.Mode mode() {
        return mode;
    }

    public InterfaceOptions mode(Device.Mode mode) {
        this.mode = mode;
        return this;
    }

    public static final class TunInterface implements Device.Options.Tun {

        private InetAddress address;
        private InetAddress netmask;

        private TunInterface() {
        }

        @Override
        public String name() {
            return "Tun";
        }

        @Override
        public InetAddress address() {
            return address;
        }

        public Tun address(InetAddress address) {
            this.address = address;
            return this;
        }

        @Override
        public InetAddress netmask() {
            return netmask;
        }

        public Tun netmask(InetAddress netmask) {
            this.netmask = netmask;
            return this;
        }
    }

    public static final class TapInterface implements Device.Options.Tap {

        private byte[] address;

        private TapInterface() {
        }

        @Override
        public String name() {
            return "Tap";
        }

        @Override
        public byte[] address() {
            return address;
        }

        public Tap address(byte[] address) {
            this.address = address;
            return this;
        }
    }

}
