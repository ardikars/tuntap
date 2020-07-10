/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct(
    value =
        "[u8(sin_len)u8(sin_family)u16(sin_port)${in_addr}(sin_addr)[8u8](sin_zero)](sockaddr_in)",
    resolutionContext = in_addr.class)
public interface sockaddr_in extends Struct<sockaddr_in> {

  @NativeGetter("sin_len")
  byte sin_len$get();

  @NativeSetter("sin_len")
  void sin_len$set(byte var1);

  @NativeGetter("sin_family")
  byte sin_family$get();

  @NativeSetter("sin_family")
  void sin_family$set(byte var1);

  @NativeGetter("sin_port")
  short sin_port$get();

  @NativeSetter("sin_port")
  void sin_port$set(short var1);

  @NativeGetter("sin_addr")
  in_addr sin_addr$get();

  @NativeSetter("sin_addr")
  void sin_addr$set(in_addr var1);

  @NativeGetter("sin_zero")
  Array<Byte> sin_zero$get();

  @NativeSetter("sin_zero")
  void sin_zero$set(Array<Byte> var1);

  @NativeAddressof("sin_zero")
  Pointer<Array<Byte>> sin_zero$ptr();
}
