package tuntap.tests;

import tuntap.Interface;
import tuntap.option.InterfaceOptions;
import tuntap.Service;

public class LinuxTapInterface {

    public static void main(String[] args) {
        Service service = Service.Creator.create("tuntap-linux");
        Interface tapa = service.create(new InterfaceOptions().mode(InterfaceOptions.tap()).name("tapa"));
        System.out.println(tapa);
        tapa.close();
    }
}
