/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Struct;

@NativeStruct(
    value =
        "[u8(sin6_len)u8(sin6_family)u16(sin6_port)u32(sin6_flowinfo)${in6_addr}(sin6_addr)u32(sin6_scope_id)](sockaddr_in6)",
    resolutionContext = in6_addr.class)
public
interface sockaddr_in6 extends Struct<sockaddr_in6> {

  @NativeGetter("sin6_len")
  byte sin6_len$get();

  @NativeSetter("sin6_len")
  void sin6_len$set(byte value);

  @NativeGetter("sin6_family")
  byte sin6_family$get();

  @NativeSetter("sin6_family")
  void sin6_family$set(byte value);

  @NativeGetter("sin6_port")
  short sin6_port$get();

  @NativeSetter("sin6_port")
  void sin6_port$set(short value);

  @NativeGetter("sin6_flowinfo")
  int sin6_flowinfo$get();

  @NativeSetter("sin6_flowinfo")
  void sin6_flowinfo$set(int value);

  @NativeGetter("sin6_addr")
  in6_addr sin6_addr$get();

  @NativeSetter("sin6_addr")
  void sin6_addr$set(in6_addr value);

  @NativeGetter("sin6_scope_id")
  int sin6_scope_id$get();

  @NativeSetter("sin6_scope_id")
  void sin6_scope_id$set(int value);
}
