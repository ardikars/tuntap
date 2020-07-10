/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeUnistd {

  private static final unistd_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), unistd_h.class);
  }

  private NativeUnistd() {
    //
  }

  public static int close(int fd) {
    return _theLibrary.close(fd);
  }

  public static int read(int fd, Pointer<Byte> buf, int count) {
    return _theLibrary.read(fd, buf, count);
  }

  public static int write(int fd, Pointer<Byte> buf, int count) {
    return _theLibrary.write(fd, buf, count);
  }

  @NativeHeader
  interface unistd_h {

    @NativeFunction("(i32)i32")
    int close(int var1);

    @NativeFunction("(u32u64:u8u32)i32")
    int read(int fd, Pointer<Byte> buf, int count);

    @NativeFunction("(u32u64:u8u32)i32")
    int write(int fd, Pointer<Byte> buf, int count);
  }
}
