/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeString {

  private static final string_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), string_h.class);
  }

  private NativeString() {
    //
  }

  public static Pointer<Void> memset(Pointer<?> buf, int val, int length) {
    return _theLibrary.memset(buf, val, length);
  }

  @NativeHeader()
  interface string_h {

    @NativeFunction("(u64:vi32u64)u64:v")
    Pointer<Void> memset(Pointer<?> var1, int var2, long var3);
  }
}
