package tuntap.spi.socket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import pcap.common.net.Inet4Address;

@RunWith(JUnitPlatform.class)
public class InetSocketAddressTest {

  @Test
  public void newInetSocketAddressTest() {
    int port = 8080;
    Inet4Address address = Inet4Address.LOCALHOST;
    InetSocketAddress socketAddress = new InetSocketAddress(port, address);
    Assertions.assertEquals(2, socketAddress.family());
    Assertions.assertEquals(port, socketAddress.port());
    Assertions.assertEquals(address, socketAddress.address());
  }

  @Test
  public void equalAndHashCodeTest() {
    int port = 8080;
    Inet4Address address = Inet4Address.LOCALHOST;
    InetSocketAddress socketAddress = new InetSocketAddress(port, address);
    InetSocketAddress otherSocketAddress = new InetSocketAddress(port, address);
    InetSocketAddress newOtherSocketAddress = new InetSocketAddress(port + port, address);
    Assertions.assertFalse(socketAddress.equals(null));
    Assertions.assertFalse(socketAddress.equals(newOtherSocketAddress));
    Assertions.assertTrue(socketAddress.equals(otherSocketAddress));
    Assertions.assertEquals(socketAddress.hashCode(), otherSocketAddress.hashCode());
  }

  @Test
  public void toStringTest() {
    int port = 8080;
    Inet4Address address = Inet4Address.LOCALHOST;
    Assertions.assertNotNull(new InetSocketAddress(port, address).toString());
  }
}
