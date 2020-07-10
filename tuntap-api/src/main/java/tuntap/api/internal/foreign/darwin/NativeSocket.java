/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin;

import tuntap.api.internal.foreign.darwin.struct.*;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeSocket {

  private static final socket_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), socket_h.class);
  }

  private NativeSocket() {
    //
  }

  public static int accept(int var1, Pointer<sockaddr> var2, Pointer<Integer> var3) {
    return _theLibrary.accept(var1, var2, var3);
  }

  public static int bind(int var1, Pointer<sockaddr> var2, int var3) {
    return _theLibrary.bind(var1, var2, var3);
  }

  public static int connect(int var1, Pointer<sockaddr> var2, int var3) {
    return _theLibrary.connect(var1, var2, var3);
  }

  public static int listen(int var1, int var2) {
    return _theLibrary.listen(var1, var2);
  }

  public static long recv(int var1, Pointer<?> var2, long var3, int var5) {
    return _theLibrary.recv(var1, var2, var3, var5);
  }

  public static long send(int var1, Pointer<?> var2, long var3, int var5) {
    return _theLibrary.send(var1, var2, var3, var5);
  }

  public static int setsockopt(int var1, int var2, int var3, Pointer<?> var4, int var5) {
    return _theLibrary.setsockopt(var1, var2, var3, var4, var5);
  }

  public static int shutdown(int var1, int var2) {
    return _theLibrary.shutdown(var1, var2);
  }

  public static int socket(int var1, int var2, int var3) {
    return _theLibrary.socket(var1, var2, var3);
  }

  @NativeHeader(
      resolutionContext = {
        in6_addr.class,
        in_addr.class,
        iovec.class,
        msghdr.class,
        sockaddr.class,
        sockaddr_in.class,
        sockaddr_in6.class,
        sockaddr_storage.class
      })
  interface socket_h {

    @NativeFunction("(i32u64:${sockaddr}u64:u32)(accept)i32")
    int accept(int var1, Pointer<sockaddr> var2, Pointer<Integer> var3);

    @NativeFunction("(i32u64:${sockaddr}u32)(bind)i32")
    int bind(int var1, Pointer<sockaddr> var2, int var3);

    @NativeFunction("(i32u64:${sockaddr}u32)(connect)i32")
    int connect(int var1, Pointer<sockaddr> var2, int var3);

    @NativeFunction("(i32i32)(listen)i32")
    int listen(int var1, int var2);

    @NativeFunction("(i32u64:vu64i32)(recv)i64")
    long recv(int var1, Pointer<?> var2, long var3, int var5);

    @NativeFunction("(i32u64:vu64i32)(send)i64")
    long send(int var1, Pointer<?> var2, long var3, int var5);

    @NativeFunction("(i32i32i32u64:vu32)i32")
    int setsockopt(int var1, int var2, int var3, Pointer<?> var4, int var5);

    @NativeFunction("(i32i32)i32")
    int shutdown(int var1, int var2);

    @NativeFunction("(i32i32i32)i32")
    int socket(int var1, int var2, int var3);
  }
}
