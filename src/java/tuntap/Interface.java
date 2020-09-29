package tuntap;

public interface Interface {

    String name();

    Interface up();

    Interface down();

    void read(Buffer buffer);

    void write(Buffer buffer);

    void close();

    interface Option {
    }
}
