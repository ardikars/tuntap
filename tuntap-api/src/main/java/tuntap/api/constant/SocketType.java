/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.constant;

import pcap.common.util.Platforms;

public class SocketType {

  /* Sequenced, reliable, connection-based byte streams. */
  public static int SOCK_STREAM() {
    if (Platforms.isLinux()) {
      return 1;
    }
    return 0;
  }

  /* Connectionless, unreliable datagrams of fixed maximum length. */
  public static int SOCK_DGRAM() {
    if (Platforms.isLinux()) {
      return 2;
    }
    return 0;
  }

  /* Raw protocol interface. */
  public static int SOCK_RAW() {
    if (Platforms.isLinux()) {
      return 3;
    }
    return 0;
  }

  /* Reliably-delivered messages. */
  public static int SOCK_RDM() {
    if (Platforms.isLinux()) {
      return 4;
    }
    return 0;
  }

  /* Sequenced, reliable, connection-based, datagrams of fixed maximum length. */
  public static int SOCK_SEQPACKET() {
    if (Platforms.isLinux()) {
      return 5;
    }
    return 0;
  }

  /* Datagram Congestion Control Protocol. */
  public static int SOCK_DCCP() {
    if (Platforms.isLinux()) {
      return 6;
    }
    return 0;
  }

  /* Linux specific way of getting packets at the dev level.  For writing rarp and other similar things on the user level. */
  public static int SOCK_PACKET() {
    if (Platforms.isLinux()) {
      return 10;
    }
    return 0;
  }

  /* Atomically set close-on-exec flag for the new descriptor(s). */
  public static int SOCK_CLOEXEC() {
    if (Platforms.isLinux()) {
      return 02000000;
    }
    return 0;
  }

  /* Atomically mark descriptor(s) as non-blocking.  */
  public static int SOCK_NONBLOCK() {
    if (Platforms.isLinux()) {
      return 00004000;
    }
    return 0;
  }
}
