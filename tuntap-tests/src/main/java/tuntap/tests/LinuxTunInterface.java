package tuntap.tests;

import tuntap.Device;
import tuntap.option.InterfaceOptions;
import tuntap.Service;

public class LinuxTunInterface {

    public static void main(String[] args) {
        Service service = Service.Creator.create("tuntap-linux");
        Device tuna = service.device(new InterfaceOptions().mode(InterfaceOptions.tun()).name("tuna"));
        System.out.println(tuna);
        tuna.close();
    }
}
