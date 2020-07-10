/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeFnctl {

  private static final fnctl_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), fnctl_h.class);
  }

  public static int open(Pointer<Byte> pathname, int mode) {
    return _theLibrary.open(pathname, mode);
  }

  public static int open(Pointer<Byte> pathname, int flags, int mode) {
    return _theLibrary.open(pathname, flags, mode);
  }

  @NativeHeader
  interface fnctl_h {

    @NativeFunction("(u64:u8u32)u64:i32")
    int open(Pointer<Byte> pathname, int mode);

    @NativeFunction("(u64:u8u32u32)u64:i32")
    int open(Pointer<Byte> pathname, int flags, int mode);
  }
}
