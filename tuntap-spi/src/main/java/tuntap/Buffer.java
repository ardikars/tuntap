package tuntap;

public interface Buffer {

    long capacity();

    long readerIndex();

    Buffer readerIndex(long readerIndex);

    long readableBytes();

    long writerIndex();

    Buffer writerIndex(long writerIndex);

    long writeableBytes();

    long address();

    <T> T buffer(Class<T> buffer);

    void close();

    interface Sliced {
    }
}
