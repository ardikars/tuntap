/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Struct;

@NativeStruct("[u8(sa_len)u8(sa_family)[14u8](sa_data)](sockaddr)")
public
interface sockaddr extends Struct<sockaddr> {

  @NativeGetter("sa_len")
  byte sa_len$get();

  @NativeSetter("sa_len")
  void sa_len$set(byte value);

  @NativeGetter("sa_family")
  byte sa_family$get();

  @NativeSetter("sa_family")
  void sa_family$set(byte value);

  @NativeGetter("sa_data")
  Array<Byte> sa_data$get();

  @NativeSetter("sa_data")
  void sa_data$set(Array<Byte> value);
}
