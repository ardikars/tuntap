package tuntap.linux;

import jnr.ffi.Memory;
import jnr.ffi.Pointer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tuntap.*;
import tuntap.common.foreign.Unsafe;

@RunWith(JUnitPlatform.class)
public class TuntapInterfaceTest {

    Interface tun;

//    @BeforeEach
    public void open() {
        Service service = Service.Loader.load("tuntap-linux");
        tun = service.create(new InterfaceOptions().mode(InterfaceMode.tap()).id(0).name("helo"));
    }

//    @Test
    public void readWrite() {
        Pointer ptr = Memory.allocate(Unsafe.RUNTIME, 1504);
        Buffer buffer = new TunTapBuffer(ptr, 1504);
        long markWriterIndex = buffer.writerIndex();
        for (int i = 0; i < 10; i++) {
            tun.read(buffer);
            System.out.println(buffer.writerIndex());
            buffer.writerIndex(markWriterIndex);
        }
        System.out.println();
    }

//    @AfterEach
    void close() {
        tun.close();
    }
}
