/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi;

import pcap.common.net.InetAddress;
import tuntap.spi.internal.TunTap;

public interface Tun extends TunTap {

  Tun inetAddress(InetAddress inetAddress, InetAddress mask);
}
