/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.socket;

import pcap.common.memory.Memory;
import pcap.common.net.Inet4Address;
import pcap.common.net.Inet6Address;
import tuntap.api.internal.foreign.darwin.*;
import tuntap.api.internal.foreign.darwin.struct.*;
import tuntap.spi.socket.Socket;
import tuntap.spi.socket.Inet6SocketAddress;
import tuntap.spi.socket.InetSocketAddress;
import tuntap.spi.socket.SocketAddress;
import tuntap.api.constant.AddressFamily;
import tuntap.api.constant.SendFlag;
import tuntap.api.constant.Shutdown;
import tuntap.spi.socket.SocketOption;

import java.foreign.NativeTypes;
import java.foreign.Scope;
import java.foreign.memory.Array;
import java.foreign.memory.LayoutType;
import java.foreign.memory.Pointer;
import java.nio.ByteBuffer;

public class DarwinSocket implements Socket {

  private final int fd;
  private final int domain;
  private final int type;
  private final int protocol;
  private final SocketAddress localAddress;
  private final SocketAddress remoteAddress;

  public DarwinSocket(int domain, int type, int protocol) {
    this.fd = NativeSocket.socket(domain, type, protocol);
    if (this.fd == -1) {
      throw new RuntimeException("Failed to open socket.");
    }
    this.domain = domain;
    this.type = type;
    this.protocol = protocol;
    if (domain == AddressFamily.AF_INET()) {
      this.localAddress = new InetSocketAddress(AddressFamily.AF_INET(), 0, Inet4Address.ZERO);
      this.remoteAddress = new InetSocketAddress(AddressFamily.AF_INET(), 0, Inet4Address.ZERO);
    } else if (domain == AddressFamily.AF_ALG()) {
      this.localAddress =
          new Inet6SocketAddress(AddressFamily.AF_INET6(), 0, 0, Inet6Address.ZERO, 0x10);
      this.remoteAddress =
          new Inet6SocketAddress(AddressFamily.AF_INET6(), 0, 0, Inet6Address.ZERO, 0x10);
    } else {
      this.localAddress = null;
      this.remoteAddress = null;
    }
  }

  private DarwinSocket(
      int fd,
      int domain,
      int type,
      int protocol,
      SocketAddress localAddress,
      SocketAddress remoteAddress) {
    this.fd = fd;
    this.domain = domain;
    this.type = type;
    this.protocol = protocol;
    this.localAddress = localAddress;
    this.remoteAddress = remoteAddress;
  }

  @Override
  public Socket option(SocketOption option, Object optval) {
    if (optval instanceof Integer) {
      try (Scope scope = Scope.globalScope().fork()) {
        Pointer<Integer> val = scope.allocate(NativeTypes.INT32);
        val.set((Integer) optval);
        NativeSocket.setsockopt(
            fd, option.level(), option.name(), val, (int) val.type().bytesSize());
      }
      return this;
    }
    throw new UnsupportedOperationException("Option not supported yet.");
  }

  @Override
  public Socket connect(SocketAddress address) {
    try (Scope scope = Scope.globalScope().fork()) {
      if (address instanceof InetSocketAddress) {
        InetSocketAddress socketAddress = (InetSocketAddress) address;
        Pointer<sockaddr_in> in = scope.allocate(LayoutType.ofStruct(sockaddr_in.class));

        in.get().sin_len$set((byte) in.type().bytesSize());
        in.get().sin_family$set((byte) socketAddress.family());
        in.get().sin_port$set(NativeIn.htons((short) socketAddress.port()));

        in_addr in_addr = scope.allocateStruct(in_addr.class);
        in_addr.s_addr$set(
            NativeInet.inet_addr(scope.allocateCString(socketAddress.address().toString())));
        in.get().sin_addr$set(in_addr);

        NativeString.memset(in.get().sin_zero$ptr(), 0, (int) in.get().sin_zero$get().bytesSize());

        Pointer<sockaddr> sockaddr =
            in.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class));
        if (NativeSocket.connect(fd, sockaddr, (int) in.type().bytesSize()) == 0) {
          int addr = in.get().sin_addr$get().s_addr$get();
          int port = in.get().sin_port$get() & 0xFFFF;
          return new DarwinSocket(
              fd,
              domain,
              type,
              protocol,
              localAddress,
              new InetSocketAddress(in.get().sin_family$get(), port, Inet4Address.valueOf(addr)));
        } else {
          NativeUnistd.close(fd);
          throw new RuntimeException("Failed to connect to " + address);
        }
      } else if (address.family() == AddressFamily.AF_INET6()) {
        Inet6SocketAddress socketAddress = (Inet6SocketAddress) address;
        byte[] raw = socketAddress.address().address();

        Array<Byte> data = scope.allocateArray(NativeTypes.INT8, raw.length);
        Pointer<sockaddr_in6> in6 = scope.allocate(LayoutType.ofStruct(sockaddr_in6.class));
        Pointer<in6_addr> in6_addr = scope.allocate(LayoutType.ofStruct(in6_addr.class));
        Pointer<in6_addr.anon$__u6_addr> anon$in6_u =
            scope.allocate(LayoutType.ofStruct(in6_addr.anon$__u6_addr.class));

        for (int i = 0; i < raw.length; i++) {
          data.set(i, raw[i]);
        }

        anon$in6_u.get().__u6_addr8$set(data);
        // anon$in6_u.get().__u6_addr16$set();
        // anon$in6_u.get().__u6_addr32$set();
        in6_addr.get().__u6_addr$set(anon$in6_u.get());

        in6.get().sin6_family$set((byte) socketAddress.family());
        in6.get().sin6_port$set((short) socketAddress.port());
        in6.get().sin6_flowinfo$set((int) socketAddress.flowInfo());
        in6.get().sin6_addr$set(in6_addr.get());
        in6.get().sin6_scope_id$set((int) socketAddress.scopeId());

        Pointer<sockaddr> sockaddr =
            in6.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class));

        if (NativeSocket.connect(fd, sockaddr, (int) in6.type().bytesSize()) == 0) {
          Array<Byte> byteArray = in6.get().sin6_addr$get().__u6_addr$get().__u6_addr8$get();
          byte[] addr = new byte[(int) byteArray.bytesSize()];
          int port = in6.get().sin6_port$get() & 0xFFFF;
          for (int i = 0; i < addr.length; i++) {
            addr[i] = byteArray.get(i);
          }
          return new DarwinSocket(
              fd,
              domain,
              type,
              protocol,
              localAddress,
              new Inet6SocketAddress(
                  in6.get().sin6_family$get(),
                  port,
                  in6.get().sin6_flowinfo$get(),
                  Inet6Address.valueOf(addr),
                  in6.get().sin6_scope_id$get()));
        } else {
          NativeUnistd.close(fd);
          throw new RuntimeException("Failed to connect to " + address);
        }
      }
      throw new UnsupportedOperationException("Option not supported yet.");
    }
  }

  @Override
  public Socket bind(SocketAddress address) {
    try (Scope scope = Scope.globalScope().fork()) {
      if (address instanceof InetSocketAddress) {
        InetSocketAddress socketAddress = (InetSocketAddress) address;
        Pointer<sockaddr_in> in = scope.allocate(LayoutType.ofStruct(sockaddr_in.class));

        in.get().sin_len$set((byte) in.type().bytesSize());
        in.get().sin_family$set((byte) socketAddress.family());
        in.get().sin_port$set(NativeIn.htons((short) socketAddress.port()));

        in_addr in_addr = scope.allocateStruct(in_addr.class);
        in_addr.s_addr$set(
            NativeInet.inet_addr(scope.allocateCString(socketAddress.address().toString())));
        in.get().sin_addr$set(in_addr);

        NativeString.memset(in.get().sin_zero$ptr(), 0, (int) in.get().sin_zero$get().bytesSize());

        Pointer<sockaddr> sockaddr =
            in.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class));
        if (NativeSocket.bind(fd, sockaddr, (int) in.type().bytesSize()) == -1) {
          if (NativeUnistd.close(fd) == -1) {
            throw new RuntimeException("Failed to close file descriptor..");
          }
          throw new RuntimeException("Failed to bind socket address.");
        }
      } else if (address instanceof Inet6SocketAddress) {
        Inet6SocketAddress socketAddress = (Inet6SocketAddress) address;
        byte[] raw = socketAddress.address().address();

        Array<Byte> data = scope.allocateArray(NativeTypes.INT8, raw.length);
        Pointer<sockaddr_in6> in6 = scope.allocate(LayoutType.ofStruct(sockaddr_in6.class));
        Pointer<in6_addr> in6_addr = scope.allocate(LayoutType.ofStruct(in6_addr.class));
        Pointer<in6_addr.anon$__u6_addr> anon$in6_u =
            scope.allocate(LayoutType.ofStruct(in6_addr.anon$__u6_addr.class));

        for (int i = 0; i < raw.length; i++) {
          data.set(i, raw[i]);
        }

        anon$in6_u.get().__u6_addr8$set(data);
        // anon$in6_u.get().u6_addr16$set();
        // anon$in6_u.get().u6_addr32$set();
        in6_addr.get().__u6_addr$set(anon$in6_u.get());

        in6.get().sin6_family$set((byte) socketAddress.family());
        in6.get().sin6_port$set((short) socketAddress.port());
        in6.get().sin6_flowinfo$set((int) socketAddress.flowInfo());
        in6.get().sin6_addr$set(in6_addr.get());
        in6.get().sin6_scope_id$set((int) socketAddress.scopeId());

        Pointer<sockaddr> sockaddr =
            in6.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class));
        if (NativeSocket.bind(fd, sockaddr, (int) sockaddr.type().bytesSize()) == -1) {
          if (NativeUnistd.close(fd) == -1) {
            throw new RuntimeException("Failed to close file descriptor..");
          }
          throw new RuntimeException("Failed to bind socket address.");
        }
      } else {
        throw new RuntimeException("Invalid socket address");
      }
      return this;
    }
  }

  @Override
  public Socket listen(int backlog) {
    if (NativeSocket.listen(fd, backlog) == -1) {
      if (NativeUnistd.close(fd) == -1) {
        throw new RuntimeException("Failed to close file descriptor..");
      }
      throw new RuntimeException("Failed to listen on socket descriptor.");
    }
    return this;
  }

  @Override
  public Socket accept() {
    try (Scope scope = Scope.globalScope().fork()) {
      Pointer<sockaddr_storage> storage =
          scope.allocate(LayoutType.ofStruct(sockaddr_storage.class));
      Pointer<Integer> length = scope.allocate(NativeTypes.INT32);
      length.set((int) storage.type().bytesSize());
      int newFd =
          NativeSocket.accept(
              fd, storage.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr.class)), length);
      if (storage.get().ss_family$get() == AddressFamily.AF_INET()) {
        Pointer<sockaddr_in> in =
            storage.cast(NativeTypes.VOID).cast(LayoutType.ofStruct(sockaddr_in.class));
        int address = in.get().sin_addr$get().s_addr$get();
        int port = in.get().sin_port$get() & 0xFFFF;
        return new DarwinSocket(
            newFd,
            domain,
            type,
            protocol,
            localAddress,
            new InetSocketAddress(in.get().sin_family$get(), port, Inet4Address.valueOf(address)));
      } else if (storage.get().ss_family$get() == AddressFamily.AF_INET6()) {
        Pointer<sockaddr_in6> in6 = storage.cast(LayoutType.ofStruct(sockaddr_in6.class));
        Array<Byte> data = in6.get().sin6_addr$get().__u6_addr$get().__u6_addr8$get();
        byte[] address = new byte[Inet6Address.IPV6_ADDRESS_LENGTH];
        for (int i = 0; i < address.length; i++) {
          address[i] = data.get(i);
        }
        int port = in6.get().sin6_port$get() & 0xFFFF;
        return new DarwinSocket(
            newFd,
            domain,
            type,
            protocol,
            localAddress,
            new Inet6SocketAddress(
                in6.get().sin6_family$get(),
                in6.get().sin6_flowinfo$get(),
                port,
                Inet6Address.valueOf(address),
                in6.get().sin6_scope_id$get()));
      }
      throw new RuntimeException("");
    }
  }

  @Override
  public long receive(Memory memory) {
    long reads = 0L;
    if (memory instanceof Memory.Direct && !(memory instanceof Memory.Sliced)) {
      ByteBuffer buffer = memory.nioBuffer();
      Pointer<Byte> buf = Pointer.fromByteBuffer(buffer);
      reads = NativeSocket.recv(fd, buf, memory.capacity(), 0);
    }
    memory.setIndex(0, (int) reads);
    return reads;
  }

  @Override
  public long send(Memory memory) {
    long writes = 0L;
    if (memory instanceof Memory.Direct && !(memory instanceof Memory.Sliced)) {
      ByteBuffer buffer = memory.nioBuffer();
      Pointer<Byte> buf = Pointer.fromByteBuffer(buffer);
      writes = NativeSocket.send(fd, buf, memory.writerIndex(), SendFlag.MSG_ZEROCOPY());
    }
    memory.setIndex((int) writes, (int) writes);
    return writes;
  }

  @Override
  public void shutdown() {
    NativeSocket.shutdown(fd, Shutdown.SHUT_RDWR());
  }

  @Override
  public void shutdownInput() {
    NativeSocket.shutdown(fd, Shutdown.SHUT_RD());
  }

  @Override
  public void shutdownOutput() {
    NativeSocket.shutdown(fd, Shutdown.SHUT_WR());
  }

  @Override
  public void close() {
    NativeUnistd.close(fd);
  }

  @Override
  public SocketAddress localAddress() {
    return localAddress;
  }

  @Override
  public SocketAddress remoteAddress() {
    return remoteAddress;
  }
}
