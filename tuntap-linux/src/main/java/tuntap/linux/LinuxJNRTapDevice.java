package tuntap.linux;

import tuntap.Device;

class LinuxJNRTapDevice extends LinuxJNRAbstractDevice {

    public LinuxJNRTapDevice(Device.Options options) {
        super(options);
    }

    @Override
    protected String requiredName(LinuxJNRNative.ifreq ifr, String reqName) {
        ifr.ifru_flags.set(IFF_TAP | IFF_NO_PI | IFF_MULTI_QUEUE);
        if (reqName == null) {
            reqName = "tap" + 0;
        } else {
            reqName = reqName + 0;
        }
        return reqName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LinuxJNRTapInterface{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", mode='").append(mode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
