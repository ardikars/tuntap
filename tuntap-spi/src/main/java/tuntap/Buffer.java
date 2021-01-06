package tuntap;

public interface Buffer {

    long capacity();

    long readerIndex();

    Buffer readerIndex(long readerIndex);

    long readableBytes();

    Buffer markReaderIndex();

    Buffer resetReaderIndex();

    long writerIndex();

    Buffer writerIndex(long writerIndex);

    long writeableBytes();

    Buffer markWriterIndex();

    Buffer resetWriterIndex();

    <T> T cast(Class<T> buffer);

    boolean release();

    interface Sliced {
    }
}
