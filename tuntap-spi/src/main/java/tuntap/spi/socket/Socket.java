/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.socket;

import pcap.common.memory.Memory;

public interface Socket {

  Socket option(SocketOption option, Object optval);

  Socket connect(SocketAddress address);

  Socket bind(SocketAddress address);

  Socket listen(int backlog);

  Socket accept();

  long receive(Memory memory);

  long send(Memory memory);

  void shutdown();

  void shutdownInput();

  void shutdownOutput();

  void close();

  SocketAddress localAddress();

  SocketAddress remoteAddress();
}
