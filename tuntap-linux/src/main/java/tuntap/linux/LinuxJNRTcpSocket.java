package tuntap.linux;

import tuntap.Buffer;
import tuntap.Socket;

import java.net.InetSocketAddress;

class LinuxJNRTcpSocket extends LinuxJNRAbstractSocket implements Socket.Tcp {

    public LinuxJNRTcpSocket(InetSocketAddress socketAddress, Socket.Options options) {
        super(socketAddress, options);
    }

    @Override
    public long send(Buffer buffer) {
        LinuxJNRBuffer buf = (LinuxJNRBuffer) buffer;
        return LinuxJNRNative.LIB_C.send(fd, buf.buffer.getPointer(buf.readerIndex(), buf.readableBytes()), buf.readableBytes(), 0);
    }

    @Override
    public long receive(Buffer buffer) {
        LinuxJNRBuffer buf = (LinuxJNRBuffer) buffer;
        return LinuxJNRNative.LIB_C.recv(fd, buf.buffer.getPointer(buf.writerIndex(), buf.writeableBytes()), buf.writeableBytes(), 0);
    }
}
