/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.socket;

import pcap.common.net.Inet6Address;
import pcap.common.util.Platforms;

public class Inet6SocketAddress implements SocketAddress {

  private final int family; // unsigned short
  private final int port; // unsigned short
  private final long flowInfo; // unsigned int
  private final Inet6Address inetAddress;
  private final long scopeId; // unsigned int

  public Inet6SocketAddress(int port, Inet6Address inetAddress, long scopeId) {
    this(port, 0, inetAddress, scopeId);
  }

  public Inet6SocketAddress(int port, long flowInfo, Inet6Address inetAddress, long scopeId) {
    this(defaultFamily(), port, flowInfo, inetAddress, scopeId);
  }

  public Inet6SocketAddress(
      int family, int port, long flowInfo, Inet6Address inetAddress, long scopeId) {
    this.family = family;
    this.port = port;
    this.flowInfo = flowInfo;
    this.inetAddress = inetAddress;
    this.scopeId = scopeId;
  }

  @Override
  public int family() {
    return family;
  }

  public int port() {
    return port;
  }

  public long flowInfo() {
    return flowInfo;
  }

  public Inet6Address address() {
    return inetAddress;
  }

  public long scopeId() {
    return scopeId;
  }

  private static int defaultFamily() {
      if (Platforms.isLinux()) {
          return 10;
      } else if (Platforms.isDarwin()) {
          return 30;
      } else if (Platforms.isWindows()) {
          return 23;
      }
      return 0;
  }
}
