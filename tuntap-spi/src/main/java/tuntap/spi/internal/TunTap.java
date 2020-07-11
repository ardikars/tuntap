/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.internal;

import pcap.common.memory.Memory;

public interface TunTap {

  String name();

  TunTap up();

  TunTap down();

  int read(Memory buffer);

  int write(Memory buffer);

  void close();
}
