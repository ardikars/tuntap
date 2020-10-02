package tuntap;

public interface Interface {

    Id id();

    String name();

    void read(Buffer buffer);

    void write(Buffer buffer);

    void close();

    interface Options {
    }

    interface Id {
        Object handle();
    }

    interface Mode {

    }
}
