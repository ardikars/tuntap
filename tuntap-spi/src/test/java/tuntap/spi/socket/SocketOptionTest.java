package tuntap.spi.socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class SocketOptionTest {

  @Test
  public void newSocketOptionTest() {
    int level = 1;
    int optname = 2;
    SocketOption option = new SocketOption(level, optname);
    Assertions.assertEquals(level, option.level());
    Assertions.assertEquals(optname, option.name());
  }
}
