package tuntap.tests;

import pcap.codec.TransportLayer;
import pcap.codec.icmp.Icmp4;
import pcap.codec.ip.Ip4;
import pcap.common.memory.Memories;
import pcap.common.memory.Memory;
import pcap.common.net.Inet4Address;
import pcap.common.net.InetAddress;
import tuntap.api.TunTap;
import tuntap.spi.Tun;

public class TunTest {

  public static void main(String[] args) {
    int mtu = 1500;
    Tun tun = TunTap.tun(0);
    tun.inetAddress(InetAddress.valueOf("10.14.22.1"), Inet4Address.valueOf("255.255.0.0"));
    tun.up();
    System.out.println("Interface " + tun.name() + " created.");
    Memory buf = Memories.directAllocator().allocate(mtu);
    System.out.println("Allocating buffer: " + buf.capacity());

    while (true) {
      int read = tun.read(buf);
      buf.writerIndex(read);
      Ip4 packets = Ip4.newPacket(buf);
      packets.forEach(System.out::println);
      //
      buf.writerIndex(read);
      tun.write(buf);
    }
  }

  static {
    TransportLayer.register(TransportLayer.ICMP, new Icmp4.Builder());
  }
}
