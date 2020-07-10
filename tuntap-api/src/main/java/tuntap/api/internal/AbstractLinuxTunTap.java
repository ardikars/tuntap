/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal;

import pcap.common.memory.Memory;
import tuntap.api.constant.AddressFamily;
import tuntap.api.constant.SocketType;
import tuntap.api.internal.foreign.linux.NativeIoctl;
import tuntap.api.internal.foreign.linux.NativeSocket;
import tuntap.api.internal.foreign.linux.NativeUnistd;
import tuntap.api.internal.foreign.linux.struct.ifreq;
import tuntap.spi.internal.TunTap;

import java.foreign.NativeTypes;
import java.foreign.Scope;
import java.foreign.memory.Array;
import java.foreign.memory.LayoutType;
import java.foreign.memory.Pointer;

abstract class AbstractLinuxTunTap implements TunTap {

  /* TUNSETIFF ifr flags */
  static final int IFF_TUN = 0x0001;
  static final int IFF_TAP = 0x0002;

  /* open-only flags */
  static final int O_RDWR = 0x0002; /* open for reading and writing */

  static final int IFF_NO_PI = 0x1000;
  static final int TUNTAP_ID_MAX = 256;

  static final int TUNTAP_ID_ANY = 257;
  static final int TUNSETPERSIST = 1074025675;
  static final int TUNSETIFF = 1074025674;
  static final int IFF_UP = 0x1;
  static final int SIOCSIFFLAGS = 0x8914;
  static final int SIOCGIFFLAGS = 0x8913;
  static final int FIONREAD = 0x541B;
  static final int ARPHRD_ETHER = 1;

  static final int SIOCSIFHWADDR = 0x8924;
  static final int SIOCSIFADDR = 0x8916;
  static final int SIOCSIFNETMASK = 0x891c;

  protected final Pointer<ifreq> ifr;
  protected final int socket;
  protected int fd;

  AbstractLinuxTunTap() {
    this.ifr = Scope.globalScope().fork().allocate(LayoutType.ofStruct(ifreq.class));
    this.socket = NativeSocket.socket(AddressFamily.AF_INET(), SocketType.SOCK_DGRAM(), 0);
    this.fd = -1;
  }

  @Override
  public String name() {
    Array<Byte> byteArray = ifr.get().ifr_ifrn$get().ifrn_name$get();
    byte[] bytes = new byte[(int) byteArray.bytesSize()];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = byteArray.get(i);
    }
    return new String(bytes);
  }

  @Override
  public TunTap up() {
    int flags = ifr.get().ifr_ifru$get().ifru_flags$get();
    flags |= IFF_UP;
    ifr.get().ifr_ifru$get().ifru_flags$set((short) flags);
    if (NativeIoctl.ioctl(socket, SIOCSIFFLAGS, ifr) < 0) {
      throw new RuntimeException("Failed to turning up interface.");
    }
    return this;
  }

  @Override
  public TunTap down() {
    int flags = ifr.get().ifr_ifru$get().ifru_flags$get();
    flags &= ~IFF_UP;
    ifr.get().ifr_ifru$get().ifru_flags$set((short) flags);
    if (NativeIoctl.ioctl(socket, SIOCSIFFLAGS, ifr) < 0) {
      throw new RuntimeException("Failed to turning up interface.");
    }
    return this;
  }

  @Override
  public int mtu() {
    return ifr.get().ifr_ifru$get().ifru_mtu$get();
  }

  @Override
  public int readable() {
    try (Scope scope = Scope.globalScope().fork()) {
      Pointer<Integer> n = scope.allocate(NativeTypes.INT32);
      if (NativeIoctl.ioctl(fd, FIONREAD, n) < 0) {
        return mtu();
      }
      return n.get();
    }
  }

  @Override
  public int read(Memory buffer) {
    return NativeUnistd.read(fd, Pointer.fromByteBuffer(buffer.nioBuffer()), buffer.capacity());
  }

  @Override
  public int write(Memory buffer) {
    return NativeUnistd.write(
        fd, Pointer.fromByteBuffer(buffer.nioBuffer()), buffer.writableBytes());
  }

  @Override
  public void close() {
    if (NativeIoctl.ioctl(fd, TUNSETPERSIST, 0) == -1) {
      throw new RuntimeException("Can't destroy the interface");
    }
    if (NativeUnistd.close(socket) < 0) {
      throw new RuntimeException("Failed to close socket file descriptor");
    }
    ifr.scope().close();
  }
}
