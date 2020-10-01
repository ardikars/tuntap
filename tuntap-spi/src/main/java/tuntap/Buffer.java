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

    long address();

    <T> T cast(Class<T> buffer);

    void close();

    interface Sliced {
    }
}
