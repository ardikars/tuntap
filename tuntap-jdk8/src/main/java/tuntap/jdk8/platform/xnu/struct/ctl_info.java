package tuntap.jdk8.platform.xnu.struct;

import jnr.ffi.Runtime;

/*
struct ctl_info {
    u_int32_t   ctl_id;
    char        ctl_name[MAX_KCTL_NAME];
};
 */
public class ctl_info extends tuntap.jdk8.struct.ctl_info {

    private static final int MAX_KCTL_NAME = 96;

    public final Unsigned32 ctl_id = new Unsigned32();
    public final UTF8String ctl_name = new UTF8String(MAX_KCTL_NAME);

    public ctl_info(Runtime runtime) {
        super(runtime);
    }
}
