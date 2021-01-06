package tuntap.option;

import tuntap.Interface;

import java.net.InetAddress;

public class InterfaceOptions implements Interface.Options {

    private String name;
    private Interface.Mode mode;

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
    public Interface.Mode mode() {
        return mode;
    }

    public InterfaceOptions mode(Interface.Mode mode) {
        this.mode = mode;
        return this;
    }

    public static final class TunInterface implements Interface.Options.Tun {

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

    public static final class TapInterface implements Interface.Options.Tap {

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
