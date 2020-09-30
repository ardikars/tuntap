package tuntap;

import java.util.Objects;

public class InterfaceMode implements Interface.Mode {

    private static final InterfaceMode TUN = new InterfaceMode(Mode.TUN);
    private static final InterfaceMode TAP = new InterfaceMode(Mode.TAP);

    private final Mode mode;

    private InterfaceMode(Mode mode) {
        this.mode = mode;
    }

    public static InterfaceMode tun() {
        return TUN;
    }

    public static InterfaceMode tap() {
        return TAP;
    }

    public Mode mode() {
        return mode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterfaceMode that = (InterfaceMode) o;
        return mode == that.mode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mode);
    }

    public enum Mode {
        TUN, TAP
    }
}
