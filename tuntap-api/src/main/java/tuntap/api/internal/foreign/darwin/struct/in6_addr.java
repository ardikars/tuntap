/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Struct;

@NativeStruct(
    value = "[${anon$__u6_addr}(__u6_addr)](in6_addr)",
    resolutionContext = in6_addr.anon$__u6_addr.class)
public
interface in6_addr extends Struct<in6_addr> {

  @NativeGetter("__u6_addr")
  anon$__u6_addr __u6_addr$get();

  @NativeSetter("__u6_addr")
  void __u6_addr$set(anon$__u6_addr value);

  @NativeStruct("[[16u8](__u6_addr8)[8u16](__u6_addr16)[4u32](__u6_addr32)](anon$__u6_addr)")
  interface anon$__u6_addr extends Struct<anon$__u6_addr> {

    @NativeGetter("__u6_addr8")
    Array<Byte> __u6_addr8$get();

    @NativeSetter("__u6_addr8")
    void __u6_addr8$set(Array<Byte> value);

    @NativeGetter("__u6_addr16")
    Array<Short> __u6_addr16$get();

    @NativeSetter("__u6_addr16")
    void __u6_addr16$set(Array<Short> value);

    @NativeGetter("__u6_addr32")
    Array<Integer> __u6_addr32$get();

    @NativeSetter("__u6_addr32")
    void __u6_addr32$set(Array<Integer> value);
  }
}
