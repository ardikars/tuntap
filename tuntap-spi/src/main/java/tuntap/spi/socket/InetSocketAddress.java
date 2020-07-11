/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.socket;

import pcap.common.net.Inet4Address;
import pcap.common.util.Strings;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InetSocketAddress that = (InetSocketAddress) o;
    return family == that.family && port == that.port && inetAddress.equals(that.inetAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(family, port, inetAddress);
  }

  @Override
  public String toString() {
    return Strings.toStringBuilder(this)
        .add("family", family())
        .add("port", port())
        .add("address", address())
        .toString();
  }
}
