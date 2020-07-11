/** This code is licenced under the BSD-3-Clause. */
package tuntap.spi.socket;

import pcap.common.util.MultipleObject;

import java.util.Arrays;
import java.util.HashSet;

public class SocketOption extends MultipleObject<Integer> {

  private final int level;
  private final int name;

  public SocketOption(int level, int optname) {
    super(new HashSet<>(Arrays.asList(level, optname)));
    this.level = level;
    this.name = optname;
  }

  public int level() {
    return level;
  }

  public int name() {
    return name;
  }
}
