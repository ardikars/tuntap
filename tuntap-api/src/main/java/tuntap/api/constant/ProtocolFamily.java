/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.constant;

import pcap.common.util.Platforms;

public class ProtocolFamily {

  /* Unspecified. */
  public static int PF_UNSPEC() {
    if (Platforms.isLinux()) {
      return 0;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Local to host (pipes and file-domain). */
  public static int PF_LOCAL() {
    if (Platforms.isLinux()) {
      return 1;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* IP protocol family. */
  public static int PF_INET() {
    if (Platforms.isLinux() || Platforms.isDarwin()) {
      return 2;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Amateur Radio AX.25.  */
  public static int PF_AX25() {
    if (Platforms.isLinux()) {
      return 3;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Novell Internet Protocol.  */
  public static int PF_IPX() {
    if (Platforms.isLinux()) {
      return 4;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Appletalk DDP.  */
  public static int PF_APPLETALK() {
    if (Platforms.isLinux()) {
      return 5;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Amateur radio NetROM.  */
  public static int PF_NETROM() {
    if (Platforms.isLinux()) {
      return 6;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Multiprotocol bridge.  */
  public static int PF_BRIDGE() {
    if (Platforms.isLinux()) {
      return 7;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* ATM PVCs.  */
  public static int PF_ATMPVC() {
    if (Platforms.isLinux()) {
      return 8;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Reserved for X.25 project.  */
  public static int PF_X25() {
    if (Platforms.isLinux()) {
      return 9;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* IP version 6.  */
  public static int PF_INET6() {
    if (Platforms.isLinux()) {
      return 10;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Amateur Radio X.25 PLP.  */
  public static int PF_ROSE() {
    if (Platforms.isLinux()) {
      return 11;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Reserved for DECnet project.  */
  public static int PF_DECnet() {
    if (Platforms.isLinux()) {
      return 12;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Reserved for 802.2LLC project.  */
  public static int PF_NETBEUI() {
    if (Platforms.isLinux()) {
      return 13;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Security callback pseudo AF.  */
  public static int PF_SECURITY() {
    if (Platforms.isLinux()) {
      return 14;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* PF_KEY key management API.  */
  public static int PF_KEY() {
    if (Platforms.isLinux()) {
      return 15;
    }
    throw new RuntimeException("Not supported yet.");
  }

  public static int PF_NETLINK() {
    if (Platforms.isLinux()) {
      return 16;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Packet family.  */
  public static int PF_PACKET() {
    if (Platforms.isLinux()) {
      return 17;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Ash.  */
  public static int PF_ASH() {
    if (Platforms.isLinux()) {
      return 18;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Acorn Econet.  */
  public static int PF_ECONET() {
    if (Platforms.isLinux()) {
      return 19;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* ATM SVCs.  */
  public static int PF_ATMSVC() {
    if (Platforms.isLinux()) {
      return 20;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* RDS sockets.  */
  public static int PF_RDS() {
    if (Platforms.isLinux()) {
      return 21;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Linux SNA Project */
  public static int PF_SNA() {
    if (Platforms.isLinux()) {
      return 22;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* IRDA sockets.  */
  public static int PF_IRDA() {
    if (Platforms.isLinux()) {
      return 23;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* PPPoX sockets.  */
  public static int PF_PPPOX() {
    if (Platforms.isLinux()) {
      return 24;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Wanpipe API sockets.  */
  public static int PF_WANPIPE() {
    if (Platforms.isLinux()) {
      return 25;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Linux LLC.  */
  public static int PF_LLC() {
    if (Platforms.isLinux()) {
      return 26;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Native InfiniBand address.  */
  public static int PF_IB() {
    if (Platforms.isLinux()) {
      return 27;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* MPLS.  */
  public static int PF_MPLS() {
    if (Platforms.isLinux()) {
      return 28;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Controller Area Network.  */
  public static int PF_CAN() {
    if (Platforms.isLinux()) {
      return 29;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* TIPC sockets.  */
  public static int PF_TIPC() {
    if (Platforms.isLinux()) {
      return 30;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Bluetooth sockets.  */
  public static int PF_BLUETOOTH() {
    if (Platforms.isLinux()) {
      return 31;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* IUCV sockets.  */
  public static int PF_IUCV() {
    if (Platforms.isLinux()) {
      return 32;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* RxRPC sockets.  */
  public static int PF_RXRPC() {
    if (Platforms.isLinux()) {
      return 33;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* mISDN sockets.  */
  public static int PF_ISDN() {
    if (Platforms.isLinux()) {
      return 34;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Phonet sockets.  */
  public static int PF_PHONET() {
    if (Platforms.isLinux()) {
      return 35;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* IEEE 802.15.4 sockets.  */
  public static int PF_IEEE802154() {
    if (Platforms.isLinux()) {
      return 36;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* CAIF sockets.  */
  public static int PF_CAIF() {
    if (Platforms.isLinux()) {
      return 37;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Algorithm sockets.  */
  public static int PF_ALG() {
    if (Platforms.isLinux()) {
      return 38;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* NFC sockets.  */
  public static int PF_NFC() {
    if (Platforms.isLinux()) {
      return 39;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* vSockets.  */
  public static int PF_VSOCK() {
    if (Platforms.isLinux()) {
      return 40;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Kernel Connection Multiplexor.  */
  public static int PF_KCM() {
    if (Platforms.isLinux()) {
      return 41;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Qualcomm IPC Router.  */
  public static int PF_QIPCRTR() {
    if (Platforms.isLinux()) {
      return 42;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* SMC sockets.  */
  public static int PF_SMC() {
    if (Platforms.isLinux()) {
      return 43;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* XDP sockets.  */
  public static int PF_XDP() {
    if (Platforms.isLinux()) {
      return 44;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* For now..  */
  public static int PF_MAX() {
    if (Platforms.isLinux()) {
      return 45;
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* POSIX name for PF_LOCAL.  */
  public static int PF_UNIX() {
    if (Platforms.isLinux()) {
      return PF_LOCAL();
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Another non-standard name for PF_LOCAL.  */
  public static int PF_FILE() {
    if (Platforms.isLinux()) {
      return PF_LOCAL();
    }
    throw new RuntimeException("Not supported yet.");
  }

  /* Alias to emulate 4.4BSD.  */
  public static int PF_ROUTE() {
    if (Platforms.isLinux()) {
      return PF_NETLINK();
    }
    throw new RuntimeException("Not supported yet.");
  }
}
