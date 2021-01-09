package tuntap.linux;

import tuntap.Device;

class LinuxJNRTunDevice extends LinuxJNRAbstractDevice {

    public LinuxJNRTunDevice(Device.Options options) {
        super(options);
    }

    @Override
    protected String requiredName(LinuxJNRNative.ifreq ifr, String reqName) {
        ifr.ifru_flags.set(IFF_TUN | IFF_NO_PI | IFF_MULTI_QUEUE);
        if (reqName == null) {
            reqName = "tun" + 0;
        } else {
            reqName = reqName + 0;
        }
        return reqName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LinuxJNRTunInterface{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", mode='").append(mode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
