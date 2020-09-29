package tuntap;

public interface Buffer {

    long capacity();

    long readerIndex();

    long readerIndex(long readerIndex);

    long writerIndex();

    long writerIndex(long writerIndex);

    void close();

    <T> T buffer(Class<T> buffer);

    interface Sliced {
    }
}
