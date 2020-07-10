/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct("[u32(s_addr)](in_addr)")
public interface in_addr extends Struct<in_addr> {

  @NativeGetter("s_addr")
  int s_addr$get();

  @NativeSetter("s_addr")
  void s_addr$set(int var1);

  @NativeAddressof("s_addr")
  Pointer<Integer> s_addr$ptr();
}
