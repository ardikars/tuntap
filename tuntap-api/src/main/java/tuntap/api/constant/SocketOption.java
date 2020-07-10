/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.constant;

import pcap.common.util.MultipleObject;
import pcap.common.util.Platforms;

import java.util.Arrays;
import java.util.HashSet;

public class SocketOption extends MultipleObject<Integer> {

  private final int level;
  private final int name;

  private SocketOption(int level, int optname) {
    super(new HashSet<>(Arrays.asList(level, optname)));
    this.level = level;
    this.name = optname;
  }

  public static SocketOption IP_HDRINCL() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 3);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_OPTIONS() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 4);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_RECVDSTADDR() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_RECVIF() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_TOS() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 1);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_TTL() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 2);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_MULTICAST_IF() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 32);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_MULTICAST_TTL() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 33);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_MULTICAST_LOOP() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 34);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_ADD_MEMBERSHIP() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 35);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_DROP_MEMBERSHIP() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 36);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_UNBLOCK_SOURCE() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 37);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_BLOCK_SOURCE() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 38);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_ADD_SOURCE_MEMBERSHIP() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 39);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IP_DROP_SOURCE_MEMBERSHIP() {
    if (Platforms.isLinux()) {
      return new SocketOption(0, 40);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption ICMP6_FILTER() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_CHECKSUM() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 7);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_DONTFRAG() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 61);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_NEXTHOP() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 9);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_PATHMTU() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 61);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVDSTOPTS() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 58);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVHOPLIMIT() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 51);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVHOPOPTS() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 53);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVPATHMTU() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 60);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVPKTINFO() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 49);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVRTHDR() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 56);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_RECVTCLASS() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 66);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_UNICAT_HOPS() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_USE_MIN_MTU() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_V6ONLY() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 26);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_MULTICAST_IF() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 17);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_MULTICAST_HOPS() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 18);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_MULTICAST_LOOP() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 19);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_JOIN_GROUP() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 20);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption IPV6_LEAVE_GROUP() {
    if (Platforms.isLinux()) {
      return new SocketOption(41, 21);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_BROADCAST() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 6);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_DEBUG() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 1);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_DONTROUTE() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 5);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_ERROR() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 4);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_KEEPALIVE() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 9);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_LINGER() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 13);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_OOBINLINE() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 10);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_RCVBUF() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 8);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_SNDBUF() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 7);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_RCVLOWAT() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 18);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_SNDLOWAT() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 19);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_RCVTIMEO() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 20);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_SNDTIMEO() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 21);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_REUSEADDR() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 2);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_REUSEPORT() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 15);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_TYPE() {
    if (Platforms.isLinux()) {
      return new SocketOption(1, 3);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public static SocketOption SO_USELOOPBACK() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_JOIN_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_LEAVE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_BLOCK_SOURCE() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_UNBLOCK_SOURCE() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_JOIN_SOURCE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IP_MCAST_LEAVE_SOURCE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_MCAST_JOIN_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_IP_MCAST_LEAVE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_IP_MCAST_BLOCK_SOURCE() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_IP_MCAST_UNBLOCK_SOURCE() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_IP_MCAST_JOIN_SOURCE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption IPV6_IP_MCAST_LEAVE_SOURCE_GROUP() {
    throw new UnsupportedOperationException("");
  }

  public static SocketOption TCP_NODELAY() {
    if (Platforms.isLinux()) {
      return new SocketOption(6, 1);
    } else if (Platforms.isDarwin()) {
      return new SocketOption(6, 1);
    }
    throw new UnsupportedOperationException("Doesn't supported yet.");
  }

  public int level() {
    return level;
  }

  public int name() {
    return name;
  }
}
