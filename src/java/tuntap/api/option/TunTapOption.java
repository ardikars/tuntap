package tuntap.api.option;

import tuntap.Interface;

import java.security.SecureRandom;

public class TunTapOption implements Interface.Option {

    private final Mode mode;
    private final String name;

    private TunTapOption(Mode mode, String name) {
        this.mode = mode;
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Mode mode() {
        return mode;
    }

    public String name() {
        return name;
    }

    public enum Mode {
        TUN, TAP
    }

    public static class Builder {

        private Mode mode;
        private String name;

        public Builder mode(Mode mode) {
            this.mode = mode;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public TunTapOption build() {
            if (mode == null) {
                this.mode = Mode.TUN;
            }
            if (name == null) {
                this.name = mode.name().toLowerCase() + new SecureRandom().nextInt(0xFF);
            }
            return new TunTapOption(mode, name);
        }
    }
}
