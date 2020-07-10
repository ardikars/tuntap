/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux.struct;

import java.foreign.annotations.NativeAddressof;
import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Array;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct(
    value = "[${anon$in_h$7887}(__in6_u)](in6_addr)",
    resolutionContext = in6_addr.anon$in6_u.class)
public interface in6_addr extends Struct<in6_addr> {

  @NativeGetter("__in6_u")
  anon$in6_u __in6_u$get();

  @NativeSetter("__in6_u")
  void __in6_u$set(anon$in6_u var1);

  @NativeAddressof("__in6_u")
  Pointer<anon$in6_u> __in6_u$ptr();

  @NativeStruct("[[16u8](__u6_addr8)|[8u16](__u6_addr16)|[4u32](__u6_addr32)](anon$in6_u)")
  interface anon$in6_u extends Struct<anon$in6_u> {

    @NativeGetter("__u6_addr8")
    Array<Byte> __u6_addr8$get();

    @NativeSetter("__u6_addr8")
    void __u6_addr8$set(Array<Byte> var1);

    @NativeAddressof("__u6_addr8")
    Pointer<Array<Byte>> __u6_addr8$ptr();

    @NativeGetter("__u6_addr16")
    Array<Short> __u6_addr16$get();

    @NativeSetter("__u6_addr16")
    void __u6_addr16$set(Array<Short> var1);

    @NativeAddressof("__u6_addr16")
    Pointer<Array<Short>> __u6_addr16$ptr();

    @NativeGetter("__u6_addr32")
    Array<Integer> __u6_addr32$get();

    @NativeSetter("__u6_addr32")
    void __u6_addr32$set(Array<Integer> var1);

    @NativeAddressof("__u6_addr32")
    Pointer<Array<Integer>> __u6_addr32$ptr();
  }
}
