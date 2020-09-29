package tuntap.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import tuntap.Interface;
import tuntap.Service;
import tuntap.api.option.TunTapOption;

@RunWith(JUnitPlatform.class)
public class TunTapInterfaceTest {

    private Interface tun;
    private Interface tap;

    @BeforeEach
    public void setUp() {
        Service service = Service.Loader.load("tuntap");
        tun = service.create(TunTapOption.builder().mode(TunTapOption.Mode.TUN).name("linux-tun").build());
        tap = service.create(TunTapOption.builder().mode(TunTapOption.Mode.TAP).name("linux-tap").build());
    }

    @Test
    public void name() {
        Assertions.assertEquals("linux-tun", tun.name());
        Assertions.assertEquals("linux-tap", tap.name());
    }

    @AfterEach
    public void close() {
        tun.close();
    }
}
