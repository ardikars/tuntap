package tuntap.api.foreign.constant;

/**
 * /usr/include/x86_64-linux-gnu/bits/ioctls.h
 */
public interface Ioctl {

    int SIOCGIFADDR = 0x8915; /* get PA address       */
    int SIOCSIFADDR = 0x8916; /* set PA address       */
    int SIOCGIFNAME = 0x8910; /* get iface name               */
    int SIOCSIFNAME = 0x8923; /* set interface name           */
    int SIOCGIFFLAGS = 0x8913; /* get flags                    */
    int SIOCSIFFLAGS = 0x8914; /* set flags                    */
    int SIOCGIFMTU = 0x8921; /* get MTU size                 */
    int SIOCSIFMTU = 0x8922; /* set MTU size                 */
    int SIOCSIFHWADDR = 0x8924; /* set hardware address         */
    int SIOCGIFHWADDR = 0x8927; /* Get hardware address         */

    int SIOCGIFNETMASK = 0x891b;
    int SIOCSIFNETMASK = 0x891c;
}
