package tuntap.api.foreign;

import jnr.ffi.NativeLong;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;

/*
struct ifmap {
    unsigned long int mem_start;
    unsigned long int mem_end;
    unsigned short int base_addr;
    unsigned char irq;
    unsigned char dma;
    unsigned char port;
}
*/
public class ifmap extends Struct {

    public final NativeLong mem_start = new NativeLong(0);
    public final NativeLong mem_end = new NativeLong(0);
    public final Unsigned16 base_addr = new Unsigned16();
    public final Unsigned8 irq = new Unsigned8();
    public final Unsigned8 dma = new Unsigned8();
    public final Unsigned8 port = new Unsigned8();

    public ifmap(Runtime runtime) {
        super(runtime);
    }
}
