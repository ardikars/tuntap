/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal;

import pcap.common.net.MacAddress;
import tuntap.spi.Tap;
import tuntap.api.internal.foreign.linux.NativeFnctl;
import tuntap.api.internal.foreign.linux.NativeIoctl;
import tuntap.api.internal.foreign.linux.NativeString;

import java.foreign.Scope;
import java.foreign.memory.Array;

public class LinuxTap extends AbstractLinuxTunTap implements Tap {

  public LinuxTap(int number) {
    try (Scope scope = Scope.globalScope().fork()) {
      String ifName;

      NativeString.memset(ifr, '\0', (int) ifr.type().bytesSize());
      ifr.get().ifr_ifru$get().ifru_flags$set((short) IFF_TAP);
      ifName = "tap" + number;

      int flags = ifr.get().ifr_ifru$get().ifru_flags$get();
      flags |= IFF_NO_PI;
      ifr.get().ifr_ifru$get().ifru_flags$set((short) flags);
      if (number < 0) {
        throw new IllegalArgumentException();
      }
      int fd = NativeFnctl.open(scope.allocateCString("/dev/net/tun"), O_RDWR);
      if (fd < 0) {
        throw new IllegalStateException();
      }

      /* Set the interface name, if any */
      if (number != TUNTAP_ID_ANY) {
        if (fd > TUNTAP_ID_MAX) {
          throw new IllegalArgumentException();
        }
        byte[] bytes = ifName.getBytes();
        for (int i = 0; i < bytes.length; i++) {
          ifr.get().ifr_ifrn$get().ifrn_name$get().set(i, bytes[i]);
        }
      }

      /* Configure the interface */
      if (NativeIoctl.ioctl(fd, TUNSETIFF, ifr) == -1) {
        throw new IllegalStateException();
      }

      this.fd = fd;

      /* Get the interface default values */
      if (NativeIoctl.ioctl(socket, SIOCGIFFLAGS, ifr) == -1) {
        throw new RuntimeException();
      }
    }
  }

  @Override
  public MacAddress hardwareAddress() {
    Array<Byte> byteArray = ifr.get().ifr_ifru$get().ifru_hwaddr$get().sa_data$get();
    if (byteArray.bytesSize() >= MacAddress.MAC_ADDRESS_LENGTH) {
      byte[] data = new byte[MacAddress.MAC_ADDRESS_LENGTH];
      for (int i = 0; i < data.length; i++) {
        data[i] = byteArray.get(i);
      }
      return MacAddress.valueOf(data);
    }
    return MacAddress.ZERO;
  }

  @Override
  public Tap hardwareAddress(MacAddress macAddress) {
    byte[] nameBytes = name().getBytes();
    for (int i = 0; i < nameBytes.length; i++) {
      ifr.get().ifr_ifrn$get().ifrn_name$get().set(i, nameBytes[i]);
    }
    ifr.get().ifr_ifru$get().ifru_hwaddr$get().sa_family$set((short) ARPHRD_ETHER);

    byte[] macBytes = macAddress.address();
    for (int i = 0; i < macBytes.length; i++) {
      ifr.get().ifr_ifru$get().ifru_hwaddr$get().sa_data$get().set(i, macBytes[i]);
    }
    if (NativeIoctl.ioctl(socket, SIOCSIFHWADDR, ifr) < 0) {
      throw new RuntimeException();
    }
    return this;
  }
}
