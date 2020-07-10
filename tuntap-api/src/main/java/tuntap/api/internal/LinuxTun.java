/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal;

import pcap.common.net.Inet4Address;
import pcap.common.net.InetAddress;
import tuntap.spi.Tun;
import tuntap.api.constant.AddressFamily;
import tuntap.api.internal.foreign.linux.NativeFnctl;
import tuntap.api.internal.foreign.linux.NativeInet;
import tuntap.api.internal.foreign.linux.NativeIoctl;
import tuntap.api.internal.foreign.linux.NativeString;
import tuntap.api.internal.foreign.linux.struct.sockaddr;
import tuntap.api.internal.foreign.linux.struct.sockaddr_in;

import java.foreign.NativeTypes;
import java.foreign.Scope;
import java.foreign.memory.Array;
import java.foreign.memory.LayoutType;
import java.foreign.memory.Pointer;

public class LinuxTun extends AbstractLinuxTunTap implements Tun {

  public LinuxTun(int number) {
    try (Scope scope = Scope.globalScope().fork()) {
      String ifname;

      ifr.get().ifr_ifru$get().ifru_flags$set((short) IFF_TUN);
      ifname = "tun" + number;

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
        byte[] bytes = ifname.getBytes();
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
  public Tun inetAddress(InetAddress inetAddress, InetAddress mask) {
    try (Scope scope = Scope.globalScope().fork()) {
      if (inetAddress instanceof Inet4Address) {

        byte[] name = name().getBytes();
        Array<Byte> bytes = scope.allocateArray(NativeTypes.UINT8, 16);
        for (int i = 0; i < name.length; i++) {
          bytes.set(i, name[i]);
        }
        ifr.get().ifr_ifrn$get().ifrn_name$set(bytes);

        Pointer<sockaddr_in> sa_in =
            ifr.get()
                .ifr_ifru$get()
                .ifru_addr$ptr()
                .cast(NativeTypes.VOID)
                .cast(LayoutType.ofStruct(sockaddr_in.class));
        sa_in.get().sin_family$set((short) AddressFamily.AF_INET());
        sa_in.get().sin_port$set((short) 0);
        sa_in
            .get()
            .sin_addr$get()
            .s_addr$set(NativeInet.inet_addr(scope.allocateCString(inetAddress.toString())));

        if (NativeIoctl.ioctl(socket, SIOCSIFADDR, ifr) < 0) {
          throw new RuntimeException("Failed to set inet address.");
        }

        Pointer<sockaddr_in> sa_in_mask = scope.allocate(LayoutType.ofStruct(sockaddr_in.class));
        NativeString.memset(sa_in_mask, '\0', (int) sa_in_mask.type().bytesSize());
        sa_in_mask.get().sin_port$set((short) 0);
        sa_in_mask.get().sin_family$set((short) AddressFamily.AF_INET());
        sa_in_mask
            .get()
            .sin_addr$get()
            .s_addr$set((NativeInet.inet_addr(scope.allocateCString(mask.toString()))));

        ifr.get()
            .ifr_ifru$get()
            .ifru_netmask$set(
                sa_in_mask.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class)).get());
        if (NativeIoctl.ioctl(socket, SIOCSIFNETMASK, ifr) < 0) {
          throw new RuntimeException("Failed to set netmask");
        }
        return this;
      }
    }
    return this;
  }
}
