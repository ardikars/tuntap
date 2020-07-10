/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.lang.invoke.MethodHandles;

public class NativeIoctl {

  private static final iocth_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), iocth_h.class);
  }

  public static int ioctl(int var1, long var2, Object... var4) {
    return _theLibrary.ioctl(var1, var2, var4);
  }

  @NativeHeader
  interface iocth_h {

    @NativeFunction("(i32u64*)i32")
    int ioctl(int var1, long var2, Object... var4);
  }
}
