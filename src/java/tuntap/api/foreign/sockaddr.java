package tuntap.api.foreign;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public abstract class sockaddr extends Struct {

    public final static int ADDR_LENGTH = 14;

    public sockaddr(Runtime runtime) {
        super(runtime);
    }

    public static class default_sockaddr extends sockaddr {

        public final Unsigned16 sa_family = new Unsigned16();
        public final Signed8[] sa_data = array(new Signed8[ADDR_LENGTH]);

        public default_sockaddr(Runtime runtime) {
            super(runtime);
        }
    }


    public static class bsd_sockaddr extends sockaddr {

        public final Unsigned8 sa_len = new Unsigned8();
        public final Unsigned8 sa_family = new Unsigned8();
        public final Signed8[] sa_data = array(new Signed8[ADDR_LENGTH]);

        public bsd_sockaddr(Runtime runtime) {
            super(runtime);
        }
    }

    public static class sockaddr_ctl extends sockaddr {

        private static final int RESERVED = 5;

        public final Unsigned8 sc_len = new Unsigned8();
        public final Unsigned8 sc_family = new Unsigned8();
        public final Unsigned16 ss_sysaddr = new Unsigned16();
        public final Unsigned32 sc_id = new Unsigned32();
        public final Unsigned32 sc_unit = new Unsigned32();
        public final Unsigned32[] sc_reserved = array(new Unsigned32[RESERVED]);

        public sockaddr_ctl(Runtime runtime) {
            super(runtime);
        }
    }
}
