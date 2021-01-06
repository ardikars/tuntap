package tuntap.linux;

import jnr.ffi.Pointer;
import tuntap.Buffer;

class LinuxJNRBuffer implements Buffer {

    final Pointer buffer;
    final long capacity;
    private long readerIndex;
    private long writerIndex;
    private long markedReaderIndex;
    private long markedWriterIndex;

    public LinuxJNRBuffer(Pointer buffer, long size) {
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
    public LinuxJNRBuffer readerIndex(long readerIndex) {
        if (readerIndex < 0 || readerIndex > writerIndex) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "readerIndex: %d (expected: 0 <= readerIndex <= writerIndex(%d))",
                            readerIndex, writerIndex));
        }
        this.readerIndex = readerIndex;
        return this;
    }

    @Override
    public LinuxJNRBuffer markReaderIndex() {
        markedReaderIndex = readerIndex;
        return this;
    }

    @Override
    public Buffer resetReaderIndex() {
        readerIndex(markedReaderIndex);
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
    public LinuxJNRBuffer writerIndex(long writerIndex) {
        if (writerIndex < readerIndex || writerIndex > capacity) {
            throw new IndexOutOfBoundsException(
                    String.format(
                            "writerIndex: %d (expected: readerIndex(%d) <= writerIndex <= capacity(%d))",
                            writerIndex, readerIndex, capacity));
        }
        this.writerIndex = writerIndex;
        return this;
    }

    @Override
    public long writeableBytes() {
        return capacity - writerIndex;
    }

    @Override
    public LinuxJNRBuffer markWriterIndex() {
        markedWriterIndex = writerIndex;
        return this;
    }

    @Override
    public Buffer resetWriterIndex() {
        writerIndex(markedWriterIndex);
        return this;
    }

    @Override
    public boolean release() {
        LinuxJNRNative.LIB_C.free(buffer);
        return true;
    }

    @Override
    public <T> T cast(Class<T> cls) {
        if (cls.isAssignableFrom(Pointer.class)) {
            return (T) buffer;
        }
        throw new IllegalArgumentException(cls.getName() + " is not supported.");
    }
}
