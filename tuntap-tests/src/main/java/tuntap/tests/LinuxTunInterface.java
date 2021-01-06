package tuntap.tests;

import tuntap.Interface;
import tuntap.option.InterfaceOptions;
import tuntap.Service;

public class LinuxTunInterface {

    public static void main(String[] args) {
        Service service = Service.Creator.create("tuntap-linux");
        Interface tuna = service.create(new InterfaceOptions().mode(InterfaceOptions.tun()).name("tuna"));
        System.out.println(tuna);
        tuna.close();
    }
}
