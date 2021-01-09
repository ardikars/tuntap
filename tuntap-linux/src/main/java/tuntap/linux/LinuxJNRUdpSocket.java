package tuntap.linux;

import jnr.ffi.byref.IntByReference;
import tuntap.Buffer;
import tuntap.Pair;
import tuntap.Socket;

import java.net.InetSocketAddress;

class LinuxJNRUdpSocket extends LinuxJNRAbstractSocket implements Socket.Udp {

    private final LinuxJNRNative.sockaddr_in6_impl in6 = new LinuxJNRNative.sockaddr_in6_impl(LinuxJNRNative.RUNTIME);
    private final IntByReference in6len = new IntByReference(28);

    public LinuxJNRUdpSocket(InetSocketAddress socketAddress, Socket.Options options) {
        super(socketAddress, options);
    }

    @Override
    public long send(Buffer buffer, InetSocketAddress address) {
        LinuxJNRBuffer buf = (LinuxJNRBuffer) buffer;
        // setter
        LinuxJNRNative.LIB_C.sendto(fd, buf.buffer.getPointer(buf.readerIndex(), buf.readableBytes()), buf.readableBytes(), 0, in6, 28);
        return 0;
    }

    @Override
    public Pair<Long, InetSocketAddress> receive(Buffer buffer) {
        LinuxJNRBuffer buf = (LinuxJNRBuffer) buffer;
        long written = LinuxJNRNative.LIB_C.recvfrom(fd, buf.buffer.getPointer(buf.writerIndex(), buf.writeableBytes()), buf.writeableBytes(), 0, in6, in6len);
        return new DatagramPair<Long, InetSocketAddress>(written, null);
    }
}
