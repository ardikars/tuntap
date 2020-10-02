package tuntap.tests;

import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSelectorProvider;
import jnr.enxio.channels.NativeSocketChannel;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.Interface;
import tuntap.InterfaceMode;
import tuntap.InterfaceOptions;
import tuntap.Service;
import tuntap.jdk8.Unsafe;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class TunServer {

    public static void main(String[] args) throws IOException {
        Service service = Service.Loader.load("tuntap-linux");
        Interface tun = service.create(new InterfaceOptions().id(0).name("tuna").mode(InterfaceMode.tun()));

        Selector tunSelector = NativeSelectorProvider.getInstance().openSelector();
        NativeSocketChannel tunChannel = new NativeSocketChannel((Integer) tun.id().handle());
        tunChannel.configureBlocking(false);
        tunChannel.register(tunSelector, SelectionKey.OP_READ, new TunHandler(tunSelector, tunChannel));
        while (tunSelector.select() > 0) {
            for (SelectionKey k : tunSelector.selectedKeys()) {
                if ((k.readyOps() & (SelectionKey.OP_READ)) != 0) {
                    ((IO) k.attachment()).read();
                }
                if ((k.readyOps() & (SelectionKey.OP_WRITE)) != 0) {
                    ((IO) k.attachment()).write();
                }
            }
        }
    }

    private static class TunHandler extends IO {

        private final Pointer buffer;
        private final long bufferSize;
        private long length;

        public TunHandler(Selector selector, SelectableChannel ch) {
            super(selector, ch);
            this.bufferSize = 1504;
            this.buffer = Memory.allocateDirect(Unsafe.RUNTIME, bufferSize);
        }

        @Override
        public void read() {
            long nbytes = Unsafe.LIB_C.read(((NativeSelectableChannel) channel).getFD(), buffer, bufferSize);
            if (nbytes > 0) {
                length = length + nbytes;
                channel.keyFor(selector).interestOps(SelectionKey.OP_WRITE);
            }
        }


        @Override
        public void write() {
            long written = 0;
            while (written < length) {
                long nbytes = Unsafe.LIB_C.write(((NativeSelectableChannel) channel).getFD(), buffer, length);
                if (nbytes < 0) {
                    length = 0;
                } else if (nbytes > 0) {
                    written = written + nbytes;
                } else {
                    break;
                }
            }
            length = 0;
            channel.keyFor(selector).interestOps(SelectionKey.OP_READ);
        }
    }
}
