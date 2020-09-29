package tuntap.api.foreign;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

/*
struct ctl_info {
    u_int32_t   ctl_id;
    char        ctl_name[MAX_KCTL_NAME];
};
 */
public class ctl_info extends Struct {

    private static final int MAX_KCTL_NAME = 96;

    public final Unsigned32 ctl_id = new Unsigned32();
    public final UTF8String ctl_name = new UTF8String(MAX_KCTL_NAME);

    public ctl_info(Runtime runtime) {
        super(runtime);
    }
}
