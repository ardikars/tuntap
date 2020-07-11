module tuntap.api {
  requires pcap.common;
  requires tuntap.spi;

  exports tuntap.api;
  exports tuntap.api.constant;
}
