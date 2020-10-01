package tuntap.tests;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import tuntap.jdk8.struct.sockaddr;

import java.util.Objects;

public class sockaddr_in extends sockaddr {

    public final Unsigned16 sin_family = new Unsigned16();
    public final Unsigned16 sin_port = new Unsigned16();
    public final Unsigned32 sin_addr = new Unsigned32();
    public final Padding sin_zero = new Padding(NativeType.SCHAR, 8);

    public sockaddr_in(Runtime runtime) {
        super(runtime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        sockaddr_in that = (sockaddr_in) o;
        return sin_family.get() == that.sin_family.get() &&
                sin_port.get() == that.sin_port.get() &&
                sin_addr.get() == that.sin_addr.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sin_family.get(), sin_port.get(), sin_addr.get());
    }
}
