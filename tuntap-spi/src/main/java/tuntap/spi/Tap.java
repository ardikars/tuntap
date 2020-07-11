/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi;

import pcap.common.net.MacAddress;
import tuntap.spi.internal.TunTap;

public interface Tap extends TunTap {

  MacAddress hardwareAddress();

  Tap hardwareAddress(MacAddress macAddress);
}
