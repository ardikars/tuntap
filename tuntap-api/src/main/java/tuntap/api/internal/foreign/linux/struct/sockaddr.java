/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct("[u16(sa_family)[14u8](sa_data)](sockaddr)")
public interface sockaddr extends Struct<sockaddr> {

  @NativeGetter("sa_family")
  short sa_family$get();

  @NativeSetter("sa_family")
  void sa_family$set(short var1);

  @NativeAddressof("sa_family")
  Pointer<Short> sa_family$ptr();

  @NativeGetter("sa_data")
  Array<Byte> sa_data$get();

  @NativeSetter("sa_data")
  void sa_data$set(Array<Byte> var1);

  @NativeAddressof("sa_data")
  Pointer<Array<Byte>> sa_data$ptr();
}
