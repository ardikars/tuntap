package tuntap.option;

import tuntap.Socket;

public class SocketOptions implements Socket.Options {

    public static class Tcp implements Socket.Tcp.Options {

        private int backlog;

        @Override
        public int listen() {
            return 0;
        }

        public Tcp listen(int backlog) {
            this.backlog = backlog;
            return this;
        }
    }

    public static class Udp implements Socket.Udp.Options {

        private int backlog;

        @Override
        public int listen() {
            return backlog;
        }

        public Udp listen(int backlog) {
            this.backlog = backlog;
            return this;
        }
    }
}
