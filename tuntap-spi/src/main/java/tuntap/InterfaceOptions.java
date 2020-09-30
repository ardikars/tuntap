package tuntap;

public class InterfaceOptions implements Interface.Options {

    private int id;
    private String name;
    private InterfaceMode mode;

    public int id() {
        return id;
    }

    public InterfaceOptions id(int id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public InterfaceOptions name(String name) {
        this.name = name;
        return this;
    }

    public InterfaceMode mode() {
        return mode;
    }

    public InterfaceOptions mode(InterfaceMode mode) {
        this.mode = mode;
        return this;
    }
}
