/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.lang.invoke.MethodHandles;

public class NativeIn {

  private static final in_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), in_h.class);
  }

  private NativeIn() {
    //
  }

  public static short htons(short value) {
    return _theLibrary.htons(value);
  }

  @NativeHeader
  interface in_h {

    @NativeFunction("(u16)u16")
    short htons(short value);
  }
}
