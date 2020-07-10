/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct("[u16(ss_family)[118u8](__ss_padding)u64(__ss_align)](sockaddr_storage)")
public interface sockaddr_storage extends Struct<sockaddr_storage> {

  @NativeGetter("ss_family")
  short ss_family$get();

  @NativeSetter("ss_family")
  void ss_family$set(short var1);

  @NativeAddressof("ss_family")
  Pointer<Short> ss_family$ptr();

  @NativeGetter("__ss_padding")
  Array<Byte> __ss_padding$get();

  @NativeSetter("__ss_padding")
  void __ss_padding$set(Array<Byte> var1);

  @NativeAddressof("__ss_padding")
  Pointer<Array<Byte>> __ss_padding$ptr();

  @NativeGetter("__ss_align")
  long __ss_align$get();

  @NativeSetter("__ss_align")
  void __ss_align$set(long var1);

  @NativeAddressof("__ss_align")
  Pointer<Long> __ss_align$ptr();
}
