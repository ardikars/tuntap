package tuntap.tests;

import jnr.enxio.channels.NativeSelectableChannel;
import jnr.enxio.channels.NativeSocketChannel;
import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.byref.IntByReference;
import tuntap.Buffer;
import tuntap.jdk8.buffer.TunTapBuffer;
import tuntap.jdk8.Unsafe;
import tuntap.jdk8.constant.Socket;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashMap;
import java.util.Map;

public class Client extends IO {

    private final Pointer buffer;
    private final long bufferSize;
    private final sockaddr_in in_addr = new sockaddr_in(Unsafe.RUNTIME);
    private final Map<sockaddr_in, Session> connections;

    public Client(Selector selector, NativeSocketChannel ch) {
        super(selector, ch);
        this.bufferSize = 10;
        this.buffer = Memory.allocateDirect(Unsafe.RUNTIME, bufferSize);
        this.connections = new HashMap<>();
        in_addr.sin_family.set(Socket.AF_INET);
    }

    @Override
    public void read() {
        IntByReference socklen = new IntByReference(Struct.size(in_addr));
        long nbytes = Unsafe.POSIX.recvfrom(((NativeSelectableChannel) channel).getFD(), buffer, bufferSize, 0, in_addr, socklen);
        if (nbytes > 0) {
            Session session = connections.get(in_addr);
            if (session == null) {
                Pointer dst = Memory.allocateDirect(Unsafe.RUNTIME, bufferSize);
                Unsafe.POSIX.memcpy(dst, buffer, nbytes);
                Buffer buffer = new TunTapBuffer(dst, bufferSize);
                buffer.markReaderIndex();
                buffer.markReaderIndex();
                sockaddr_in new_addr_in = new sockaddr_in(Unsafe.RUNTIME);
                new_addr_in.sin_family.set(in_addr.sin_family.get());
                new_addr_in.sin_port.set(in_addr.sin_port.get());
                new_addr_in.sin_addr.set(in_addr.sin_addr.get());
                session = new Session(new_addr_in, buffer);
                connections.put(in_addr, session);
            } else {
                Pointer dst = session.buffer.cast(Pointer.class);
                Unsafe.POSIX.memcpy(dst, buffer, nbytes);
            }
            session.buffer.writerIndex(session.buffer.writerIndex() + nbytes);
            channel.keyFor(selector).interestOps(SelectionKey.OP_WRITE);
        }
    }


    @Override
    public void write() {
        connections.entrySet().forEach(entry -> {
            Buffer value = entry.getValue().buffer;
            while (value.readableBytes() > 0) {
                long nbytes = Unsafe.POSIX.sendto(((NativeSelectableChannel) channel).getFD(), value.cast(Pointer.class), value.readableBytes(), 0, entry.getValue().addr, Struct.size(entry.getKey()));
                if (nbytes < 0) {
                    value.resetReaderIndex();
                    value.resetWriterIndex();
                } else if (nbytes > 0) {
                    value.readerIndex(value.readerIndex() + nbytes);
                } else {
                    break;
                }
            }
            value.resetReaderIndex();
            value.resetWriterIndex();
        });
        channel.keyFor(selector).interestOps(SelectionKey.OP_READ);
    }
}
