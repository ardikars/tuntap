package tuntap.spi.socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import pcap.common.net.Inet6Address;

@RunWith(JUnitPlatform.class)
public class Inet6SocketAddressTest {

  @Test
  public void newInet6SocketAddressTest() {
    int port = 8080;
    Inet6Address address = Inet6Address.LOCALHOST;
    int scopeId = 10;
    Inet6SocketAddress socketAddress = new Inet6SocketAddress(port, address, scopeId);
    Assertions.assertEquals(Inet6SocketAddress.defaultFamily(), socketAddress.family());
    Assertions.assertEquals(port, socketAddress.port());
    Assertions.assertEquals(0, socketAddress.flowInfo());
    Assertions.assertEquals(address, socketAddress.address());
    Assertions.assertEquals(scopeId, socketAddress.scopeId());
  }

  @Test
  public void equalAndHashCodeTest() {
    int port = 8080;
    Inet6Address address = Inet6Address.LOCALHOST;
    int scopeId = 10;
    Inet6SocketAddress socketAddress = new Inet6SocketAddress(port, address, scopeId);
    Inet6SocketAddress otherSocketAddress = new Inet6SocketAddress(port, address, scopeId);
    Inet6SocketAddress newOtherSocketAddress =
        new Inet6SocketAddress(port + port, address, scopeId);
    Assertions.assertFalse(socketAddress.equals(null));
    Assertions.assertFalse(socketAddress.equals(newOtherSocketAddress));
    Assertions.assertTrue(socketAddress.equals(otherSocketAddress));
    Assertions.assertEquals(socketAddress.hashCode(), otherSocketAddress.hashCode());
  }

  @Test
  public void toStringTest() {
    int port = 8080;
    Inet6Address address = Inet6Address.LOCALHOST;
    int scopeId = 10;
    Assertions.assertNotNull(new Inet6SocketAddress(port, address, scopeId).toString());
  }
}
