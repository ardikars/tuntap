/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.darwin.struct;

import java.foreign.annotations.NativeGetter;
import java.foreign.annotations.NativeSetter;
import java.foreign.annotations.NativeStruct;
import java.foreign.memory.Pointer;
import java.foreign.memory.Struct;

@NativeStruct("[u64(iov_base):vu64(iov_len)](iovec)")
public interface iovec extends Struct<iovec> {

  @NativeGetter("iov_base")
  Pointer<Void> iov_base$get();

  @NativeSetter("iov_base")
  void iov_base$set(Pointer<?> var1);

  @NativeGetter("iov_len")
  long iov_len$get();

  @NativeSetter("iov_len")
  void iov_len$set(long var1);
}
