/** This code is licenced under the BSD-3-Clause. */
package tuntap.api;

import pcap.common.util.Platforms;
import tuntap.api.internal.LinuxTap;
import tuntap.api.internal.LinuxTun;
import tuntap.api.internal.socket.DarwinSocket;
import tuntap.api.internal.socket.LinuxSocket;
import tuntap.spi.Tap;
import tuntap.spi.Tun;
import tuntap.spi.socket.Socket;

public class TunTap {

  public static Tun tun(int name) {
    if (Platforms.isLinux()) {
      return new LinuxTun(name);
    }
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public static Tap tap(int name) {
    if (Platforms.isLinux()) {
      return new LinuxTap(name);
    }
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public static Socket socket(int family, int type, int protocol) {
    if (Platforms.isLinux()) {
      return new LinuxSocket(family, type, protocol);
    } else if (Platforms.isDarwin()) {
      return new DarwinSocket(family, type, protocol);
    }
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
