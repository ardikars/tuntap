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
    "[u8(ss_len)u8(ss_family)[6u8](__ss_pad1)i64(__ss_align)[112u8](__ss_pad2)](sockaddr_storage)")
public interface sockaddr_storage extends Struct<sockaddr_storage> {

  @NativeGetter("ss_len")
  byte ss_len$get();

  @NativeSetter("ss_len")
  void ss_len$set(byte var1);

  @NativeAddressof("ss_len")
  Pointer<Byte> ss_len$ptr();

  @NativeGetter("ss_family")
  byte ss_family$get();

  @NativeSetter("ss_family")
  void ss_family$set(byte var1);

  @NativeGetter("__ss_pad1")
  Array<Byte> __ss_pad1$get();

  @NativeSetter("__ss_pad1")
  void __ss_pad1$set(Array<Byte> var1);

  @NativeGetter("__ss_align")
  long __ss_align$get();

  @NativeSetter("__ss_align")
  void __ss_align$set(long var1);

  @NativeGetter("__ss_pad2")
  Array<Byte> __ss_pad2$get();

  @NativeSetter("__ss_pad2")
  void __ss_pad2$set(Array<Byte> var1);
}
