package tuntap.tests;

import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;

public abstract class IO {

    protected final SelectableChannel channel;
    protected final Selector selector;

    public IO(Selector selector, SelectableChannel ch) {
        this.selector = selector;
        this.channel = ch;
    }

    public abstract void read();

    public abstract void write();
}