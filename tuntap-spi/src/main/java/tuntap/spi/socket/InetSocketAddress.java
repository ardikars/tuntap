/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.socket;

import pcap.common.net.Inet4Address;

public class InetSocketAddress implements SocketAddress {

  private final int family; // unsigned short
  private final int port; // unsigned short
  private final Inet4Address inetAddress;

  public InetSocketAddress(int port, Inet4Address inetAddress) {
    this(2, port, inetAddress);
  }

  public InetSocketAddress(int family, int port, Inet4Address inetAddress) {
    this.family = family;
    this.port = port;
    this.inetAddress = inetAddress;
  }

  @Override
  public int family() {
    return family;
  }

  public int port() {
    return port;
  }

  public Inet4Address address() {
    return inetAddress;
  }
}
