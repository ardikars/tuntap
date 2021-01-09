package tuntap.tests;

import tuntap.Device;
import tuntap.Service;
import tuntap.option.InterfaceOptions;

public class LinuxTapInterface {

    public static void main(String[] args) {
        Service service = Service.Creator.create("tuntap-linux");
        Device tapa = service.device(new InterfaceOptions().mode(InterfaceOptions.tap()).name("tapa"));
        System.out.println(tapa);
        tapa.close();

    }
}
