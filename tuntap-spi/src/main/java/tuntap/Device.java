package tuntap;

import java.net.InetAddress;

public interface Device extends Selector.Selectable {

    Id<?> id();

    String name();

    String mode();

    long read(Buffer buffer);

    long write(Buffer buffer);

    void close();

    interface Options {

        String name();

        Mode mode();

        interface Tun extends Mode {

            InetAddress address();

            InetAddress netmask();

        }

        interface Tap extends Mode {

            byte[] address();
        }
    }

    interface Id<T> {
        T value();
    }

    interface Mode {

        String name();

    }
}
