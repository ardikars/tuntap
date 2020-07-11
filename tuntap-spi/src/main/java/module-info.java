module tuntap.spi {
  requires pcap.common;

  exports tuntap.spi;
  exports tuntap.spi.socket;
  exports tuntap.spi.internal to
      tuntap.api;
}
