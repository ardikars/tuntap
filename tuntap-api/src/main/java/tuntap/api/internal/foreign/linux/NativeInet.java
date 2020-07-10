/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux;

import tuntap.api.internal.foreign.linux.struct.in_addr;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeInet {

  private static final inet_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), inet_h.class);
  }

  public static int inet_addr(Pointer<Byte> addr) {
    return _theLibrary.inet_addr(addr);
  }

  public static int inet_pton(int var1, Pointer<Byte> var2, Pointer<?> var3) {
    return _theLibrary.inet_pton(var1, var2, var3);
  }

  public static Pointer<Byte> inet_ntop(
          int var1, Pointer<?> var2, Pointer<Byte> var3, int var4) {
    return _theLibrary.inet_ntop(var1, var2, var3, var4);
  }

  public static int inet_aton(Pointer<Byte> var1, Pointer<in_addr> var2) {
    return _theLibrary.inet_aton(var1, var2);
  }

  @NativeHeader
  interface inet_h {

    @NativeFunction("(u64:u8)u32")
    int inet_addr(Pointer<Byte> var1);

    @NativeFunction("(i32u64:u8u64:v)i32")
    int inet_pton(int sa_family, Pointer<Byte> var2, Pointer<?> var3);

    @NativeFunction("(i32u64:vu64:u8u32)u64:u8")
    Pointer<Byte> inet_ntop(int sa_family, Pointer<?> var2, Pointer<Byte> var3, int var4);

    int inet_aton(Pointer<Byte> var1, Pointer<in_addr> var2);
  }
}
