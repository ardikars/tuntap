package tuntap.tests;

import jnr.enxio.channels.NativeSelectorProvider;
import jnr.enxio.channels.NativeSocketChannel;
import jnr.ffi.Struct;
import tuntap.Service;
import tuntap.jdk8.Unsafe;
import tuntap.jdk8.struct.sockaddr;
import tuntap.jdk8.constant.Socket;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class LinuxTunServer {

    private static final Service SERVICE = Service.Loader.load("tuntap-linux");

    public static void main(String[] args) throws IOException {
        Selector selector = NativeSelectorProvider.getInstance().openSelector();
        NativeSocketChannel ch = serverSocket(9999);
        ch.configureBlocking(false);
        ch.register(selector, SelectionKey.OP_READ, new Client(selector, ch));
        while (selector.select() > 0) {
            for (SelectionKey k : selector.selectedKeys()) {
                if ((k.readyOps() & (SelectionKey.OP_READ)) != 0) {
                    ((IO) k.attachment()).read();
                }
                if ((k.readyOps() & (SelectionKey.OP_WRITE)) != 0) {
                    ((IO) k.attachment()).write();
                }
            }
        }
        System.out.println();
    }

    private static short htons(short val) {
        return Short.reverseBytes(val);
    }

    private static NativeSocketChannel serverSocket(int port) {
        int fd = Unsafe.POSIX.socket(Socket.AF_INET, Socket.SOCK_DGRAM, Socket.PF_UNSPEC);
        sockaddr addr;
        sockaddr_in addr_in = new sockaddr_in(Unsafe.RUNTIME);
        addr_in.sin_family.set(Socket.AF_INET);
        addr_in.sin_port.set(htons((short) port));
        addr = addr_in;
        if (Unsafe.POSIX.bind(fd, addr, Struct.size(addr)) < 0) {
            throw new RuntimeException();
        }
        System.out.println("UDP server started on port " + port);
        return new NativeSocketChannel(fd);
    }

}
