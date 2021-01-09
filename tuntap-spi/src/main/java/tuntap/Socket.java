package tuntap;

import java.net.InetSocketAddress;

public interface Socket extends Selector.Selectable {

    void close();

    interface Tcp extends Socket {

        long send(Buffer buffer);

        long receive(Buffer buffer);

        interface Options extends Socket.Options {

            int listen();

        }
    }

    interface Udp extends Socket {

        long send(Buffer buffer, InetSocketAddress address);

        Pair<Long, InetSocketAddress> receive(Buffer buffer);

        interface Options extends Socket.Options {

            int listen();

        }
    }

    interface Options {

    }
}
