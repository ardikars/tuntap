package tuntap.api.buffer;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import tuntap.Buffer;
import tuntap.api.foreign.Unsafe;

public class DirectBuffer implements Buffer, AutoCloseable {

    private final Pointer pointer;
    private final long capacity;
    private volatile long readerIndex;
    private volatile long writerIndex;

    private DirectBuffer(Pointer pointer) {
        this.pointer = pointer;
        this.capacity = this.pointer.size();
    }

    public DirectBuffer(long capacity) {
        this(Memory.allocateDirect(Unsafe.RUNTIME, capacity));
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
    public long readerIndex(long readerIndex) {
        this.readerIndex = readerIndex;
        return readerIndex;
    }

    @Override
    public long writerIndex() {
        return writerIndex;
    }

    @Override
    public long writerIndex(long writerIndex) {
        this.writerIndex = writerIndex;
        return writerIndex;
    }

    public Sliced slice() {
        return slice(readerIndex(), writerIndex() - readerIndex());
    }

    public Sliced slice(long offset, long size) {
        return new Sliced(pointer.slice(offset, size));
    }

    @Override
    public void close() {
        Unsafe.NATIVE.free(pointer);
    }

    @Override
    public <T> T buffer(Class<T> buffer) {
        if (buffer.isAssignableFrom(Pointer.class)) {
            return (T) pointer;
        }
        throw new RuntimeException();
    }

    public static class Sliced extends DirectBuffer {

        public Sliced(Pointer pointer) {
            super(pointer);
        }
    }
}
