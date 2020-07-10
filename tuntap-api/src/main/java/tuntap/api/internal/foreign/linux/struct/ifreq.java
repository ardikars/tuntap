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
    value = "[${anon$ifr_ifrn}(ifr_ifrn)${anon$ifr_ifru}(ifr_ifru)](ifreq)",
    resolutionContext = {ifreq.anon$ifr_ifrn.class, ifreq.anon$ifr_ifru.class})
public interface ifreq extends Struct<ifreq> {

  @NativeGetter("ifr_ifrn")
  anon$ifr_ifrn ifr_ifrn$get();

  @NativeSetter("ifr_ifrn")
  void ifr_ifrn$set(anon$ifr_ifrn var1);

  @NativeAddressof("ifr_ifrn")
  Pointer<anon$ifr_ifrn> ifr_ifrn$ptr();

  @NativeGetter("ifr_ifru")
  anon$ifr_ifru ifr_ifru$get();

  @NativeSetter("ifr_ifru")
  void ifr_ifru$set(anon$ifr_ifru var1);

  @NativeAddressof("ifr_ifru")
  Pointer<anon$ifr_ifru> ifr_ifru$ptr();

  @NativeStruct("[[16u8](ifrn_name)](anon$ifr_ifrn)")
  interface anon$ifr_ifrn extends Struct<anon$ifr_ifrn> {

    @NativeGetter("ifrn_name")
    Array<Byte> ifrn_name$get();

    @NativeSetter("ifrn_name")
    void ifrn_name$set(Array<Byte> var1);

    @NativeAddressof("ifrn_name")
    Pointer<Array<Byte>> ifrn_name$ptr();
  }

  @NativeStruct(
      value =
          "[${sockaddr}(ifru_addr)|${sockaddr}(ifru_dstaddr)|${sockaddr}(ifru_broadaddr)|${sockaddr}(ifru_netmask)|${sockaddr}(ifru_hwaddr)|i16(ifru_flags)|i32(ifru_ivalue)|i32(ifru_mtu)|${ifmap}(ifru_map)|[16u8](ifru_slave)|[16u8](ifru_newname)|u64(ifru_data):u8](anon$ifr_ifru)",
      resolutionContext = sockaddr.class)
  interface anon$ifr_ifru extends Struct<anon$ifr_ifru> {

    @NativeGetter("ifru_addr")
    sockaddr ifru_addr$get();

    @NativeSetter("ifru_addr")
    void ifru_addr$set(sockaddr var1);

    @NativeAddressof("ifru_addr")
    Pointer<sockaddr> ifru_addr$ptr();

    @NativeGetter("ifru_dstaddr")
    sockaddr ifru_dstaddr$get();

    @NativeSetter("ifru_dstaddr")
    void ifru_dstaddr$set(sockaddr var1);

    @NativeAddressof("ifru_dstaddr")
    Pointer<sockaddr> ifru_dstaddr$ptr();

    @NativeGetter("ifru_broadaddr")
    sockaddr ifru_broadaddr$get();

    @NativeSetter("ifru_broadaddr")
    void ifru_broadaddr$set(sockaddr var1);

    @NativeAddressof("ifru_broadaddr")
    Pointer<sockaddr> ifru_broadaddr$ptr();

    @NativeGetter("ifru_netmask")
    sockaddr ifru_netmask$get();

    @NativeSetter("ifru_netmask")
    void ifru_netmask$set(sockaddr var1);

    @NativeAddressof("ifru_netmask")
    Pointer<sockaddr> ifru_netmask$ptr();

    @NativeGetter("ifru_hwaddr")
    sockaddr ifru_hwaddr$get();

    @NativeSetter("ifru_hwaddr")
    void ifru_hwaddr$set(sockaddr var1);

    @NativeAddressof("ifru_hwaddr")
    Pointer<sockaddr> ifru_hwaddr$ptr();

    @NativeGetter("ifru_flags")
    short ifru_flags$get();

    @NativeSetter("ifru_flags")
    void ifru_flags$set(short var1);

    @NativeAddressof("ifru_flags")
    Pointer<Short> ifru_flags$ptr();

    @NativeGetter("ifru_ivalue")
    int ifru_ivalue$get();

    @NativeSetter("ifru_ivalue")
    void ifru_ivalue$set(int var1);

    @NativeAddressof("ifru_ivalue")
    Pointer<Integer> ifru_ivalue$ptr();

    @NativeGetter("ifru_mtu")
    int ifru_mtu$get();

    @NativeSetter("ifru_mtu")
    void ifru_mtu$set(int var1);

    @NativeAddressof("ifru_mtu")
    Pointer<Integer> ifru_mtu$ptr();

    @NativeGetter("ifru_map")
    ifmap ifru_map$get();

    @NativeSetter("ifru_map")
    void ifru_map$set(sockaddr var1);

    @NativeAddressof("ifru_map")
    Pointer<sockaddr> ifru_map$ptr();

    @NativeGetter("ifru_slave")
    Array<Byte> ifru_slave$get();

    @NativeSetter("ifru_slave")
    void ifru_slave$set(Array<Byte> var1);

    @NativeAddressof("ifru_slave")
    Pointer<Array<Byte>> ifru_slave$ptr();

    @NativeGetter("ifru_newname")
    Array<Byte> ifru_newname$get();

    @NativeSetter("ifru_newname")
    void ifru_newname$set(Array<Byte> var1);

    @NativeAddressof("ifru_newname")
    Pointer<Array<Byte>> ifru_newname$ptr();

    @NativeGetter("ifru_data")
    Pointer<Byte> ifru_data$get();

    @NativeSetter("ifru_data")
    void ifru_data$set(Pointer<Byte> var1);

    @NativeAddressof("ifru_data")
    Pointer<Pointer<Byte>> ifru_data$ptr();
  }

  @NativeStruct("[u64(mem_start)u64(mem_end)u16(base_addr)u8(irq)u8(dma)u8(port)x24](ifmap)")
  interface ifmap extends Struct<ifmap> {

    @NativeGetter("mem_start")
    long mem_start$get();

    @NativeSetter("mem_start")
    void mem_start$set(long var1);

    @NativeAddressof("mem_start")
    Pointer<Long> mem_start$ptr();

    @NativeGetter("mem_end")
    long mem_end$get();

    @NativeSetter("mem_end")
    void mem_end$set(long var1);

    @NativeAddressof("mem_end")
    Pointer<Long> mem_end$ptr();

    @NativeGetter("base_addr")
    short base_addr$get();

    @NativeSetter("base_addr")
    void base_addr$set(short var1);

    @NativeAddressof("base_addr")
    Pointer<Short> base_addr$ptr();

    @NativeGetter("irq")
    byte irq$get();

    @NativeSetter("irq")
    void irq$set(byte var1);

    @NativeAddressof("irq")
    Pointer<Byte> irq$ptr();

    @NativeGetter("dma")
    byte dma$get();

    @NativeSetter("dma")
    void dma$set(byte var1);

    @NativeAddressof("dma")
    Pointer<Byte> dma$ptr();

    @NativeGetter("port")
    byte port$get();

    @NativeSetter("port")
    void port$set(byte var1);

    @NativeAddressof("port")
    Pointer<Byte> port$ptr();
  }
}
