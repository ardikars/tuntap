/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct(
    "[u16(sin6_family)u16(sin6_port)u32(sin6_flowinfo)${in6_addr}(sin6_addr)u32(sin6_scope_id)](sockaddr_in6)")
public interface sockaddr_in6 extends Struct<sockaddr_in6> {

  @NativeGetter("sin6_family")
  short sin6_family$get();

  @NativeSetter("sin6_family")
  void sin6_family$set(short var1);

  @NativeAddressof("sin6_family")
  Pointer<Short> sin6_family$ptr();

  @NativeGetter("sin6_port")
  short sin6_port$get();

  @NativeSetter("sin6_port")
  void sin6_port$set(short var1);

  @NativeAddressof("sin6_port")
  Pointer<Short> sin6_port$ptr();

  @NativeGetter("sin6_flowinfo")
  int sin6_flowinfo$get();

  @NativeSetter("sin6_flowinfo")
  void sin6_flowinfo$set(int var1);

  @NativeAddressof("sin6_flowinfo")
  Pointer<Integer> sin6_flowinfo$ptr();

  @NativeGetter("sin6_addr")
  in6_addr sin6_addr$get();

  @NativeSetter("sin6_addr")
  void sin6_addr$set(in6_addr var1);

  @NativeAddressof("sin6_addr")
  Pointer<in6_addr> sin6_addr$ptr();

  @NativeGetter("sin6_scope_id")
  int sin6_scope_id$get();

  @NativeSetter("sin6_scope_id")
  void sin6_scope_id$set(int var1);

  @NativeAddressof("sin6_scope_id")
  Pointer<Integer> sin6_scope_id$ptr();
}
