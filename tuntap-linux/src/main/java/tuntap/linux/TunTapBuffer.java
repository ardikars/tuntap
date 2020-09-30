package tuntap.linux;

import jnr.ffi.Pointer;
import tuntap.Buffer;

public class TunTapBuffer implements Buffer {

    final Pointer buffer;
    final long capacity;
    private long readerIndex;
    private long writerIndex;

    public TunTapBuffer(Pointer buffer, long size) {
        this.buffer = buffer;
        this.capacity = size;
    }

    @Override
    public long capacity() {
        return capacity;
    }

    @Override
    public long readerIndex() {
        return readerIndex;
    }

    @Override
    public TunTapBuffer readerIndex(long readerIndex) {
        this.readerIndex = readerIndex;
        return this;
    }

    @Override
    public long readableBytes() {
        return writerIndex - readerIndex;
    }

    @Override
    public long writerIndex() {
        return writerIndex;
    }

    @Override
    public TunTapBuffer writerIndex(long writerIndex) {
        this.writerIndex = writerIndex;
        return this;
    }

    @Override
    public long writeableBytes() {
        return capacity - writerIndex;
    }

    @Override
    public long address() {
        return buffer.address();
    }

    @Override
    public void close() {
        //
    }

    @Override
    public <T> T buffer(Class<T> cls) {
        if (cls.isAssignableFrom(Pointer.class)) {
            return (T) buffer;
        }
        return null;
    }
}
