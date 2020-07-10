/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct(
    value =
        "[u64(msg_name):vu32(msg_namelen)x32u64(msg_iov):${iovec}i32(msg_iovlen)x32u64(msg_control):vu32(msg_controllen)i32(msg_flags)](msghdr)",
    resolutionContext = iovec.class)
public interface msghdr extends Struct<msghdr> {

  @NativeGetter("msg_name")
  Pointer<Void> msg_name$get();

  @NativeSetter("msg_name")
  void msg_name$set(Pointer<?> var1);

  @NativeGetter("msg_namelen")
  int msg_namelen$get();

  @NativeSetter("msg_namelen")
  void msg_namelen$set(int var1);

  @NativeGetter("msg_iov")
  Pointer<iovec> msg_iov$get();

  @NativeSetter("msg_iov")
  void msg_iov$set(Pointer<iovec> var1);

  @NativeGetter("msg_iovlen")
  int msg_iovlen$get();

  @NativeSetter("msg_iovlen")
  void msg_iovlen$set(int var1);

  @NativeGetter("msg_control")
  Pointer<Void> msg_control$get();

  @NativeSetter("msg_control")
  void msg_control$set(Pointer<?> var1);

  @NativeGetter("msg_controllen")
  int msg_controllen$get();

  @NativeSetter("msg_controllen")
  void msg_controllen$set(int var1);

  @NativeGetter("msg_flags")
  int msg_flags$get();

  @NativeSetter("msg_flags")
  void msg_flags$set(int var1);
}
