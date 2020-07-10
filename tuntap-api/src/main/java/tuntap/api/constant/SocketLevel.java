/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.constant;

import pcap.common.util.Platforms;

public class SocketLevel {

  public static int SOL_RAW() {
    if (Platforms.isLinux()) {
      return 255;
    }
    return 0;
  }

  public static int SOL_DECNET() {
    if (Platforms.isLinux()) {
      return 261;
    }
    return 0;
  }

  public static int SOL_X25() {
    if (Platforms.isLinux()) {
      return 262;
    }
    return 0;
  }

  public static int SOL_PACKET() {
    if (Platforms.isLinux()) {
      return 263;
    }
    return 0;
  }

  /* ATM layer (cell level).  */
  public static int SOL_ATM() {
    if (Platforms.isLinux()) {
      return 264;
    }
    return 0;
  }

  /* ATM Adaption Layer (packet level).  */
  public static int SOL_AAL() {
    if (Platforms.isLinux()) {
      return 265;
    }
    return 0;
  }

  public static int SOL_IRDA() {
    if (Platforms.isLinux()) {
      return 266;
    }
    return 0;
  }

  public static int SOL_NETBEUI() {
    if (Platforms.isLinux()) {
      return 267;
    }
    return 0;
  }

  public static int SOL_LLC() {
    if (Platforms.isLinux()) {
      return 268;
    }
    return 0;
  }

  public static int SOL_DCCP() {
    if (Platforms.isLinux()) {
      return 269;
    }
    return 0;
  }

  public static int SOL_NETLINK() {
    if (Platforms.isLinux()) {
      return 270;
    }
    return 0;
  }

  public static int SOL_TIPC() {
    if (Platforms.isLinux()) {
      return 271;
    }
    return 0;
  }

  public static int SOL_RXRPC() {
    if (Platforms.isLinux()) {
      return 272;
    }
    return 0;
  }

  public static int SOL_PPPOL2TP() {
    if (Platforms.isLinux()) {
      return 273;
    }
    return 0;
  }

  public static int SOL_BLUETOOTH() {
    if (Platforms.isLinux()) {
      return 274;
    }
    return 0;
  }

  public static int SOL_PNPIPE() {
    if (Platforms.isLinux()) {
      return 275;
    }
    return 0;
  }

  public static int SOL_RDS() {
    if (Platforms.isLinux()) {
      return 276;
    }
    return 0;
  }

  public static int SOL_IUCV() {
    if (Platforms.isLinux()) {
      return 277;
    }
    return 0;
  }

  public static int SOL_CAIF() {
    if (Platforms.isLinux()) {
      return 278;
    }
    return 0;
  }

  public static int SOL_ALG() {
    if (Platforms.isLinux()) {
      return 279;
    }
    return 0;
  }

  public static int SOL_NFC() {
    if (Platforms.isLinux()) {
      return 280;
    }
    return 0;
  }

  public static int SOL_KCM() {
    if (Platforms.isLinux()) {
      return 281;
    }
    return 0;
  }

  public static int SOL_TLS() {
    if (Platforms.isLinux()) {
      return 282;
    }
    return 0;
  }

  public static int SOL_XDP() {
    if (Platforms.isLinux()) {
      return 283;
    }
    return 0;
  }
}
