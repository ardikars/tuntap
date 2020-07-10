/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.internal.foreign.linux;

import tuntap.api.internal.foreign.linux.struct.*;

import java.foreign.Libraries;
import java.foreign.annotations.NativeFunction;
import java.foreign.annotations.NativeHeader;
import java.foreign.memory.Pointer;
import java.lang.invoke.MethodHandles;

public class NativeSocket {

  private static final socket_h _theLibrary;

  static {
    _theLibrary = Libraries.bind(MethodHandles.lookup(), socket_h.class);
  }

  private NativeSocket() {
    //
  }

  /**
   * Create a new socket of type TYPE in domain DOMAIN, using protocol PROTOCOL. If PROTOCOL is
   * zero, one is chosen automatically Returns a file descriptor for the new socket, or -1 for
   * errors.
   *
   * @param domain domain.
   * @param type type.
   * @param protocol protocol.
   * @return returns a file descriptor for the new socket, or -1 for errors.
   */
  public static int socket(int domain, int type, int protocol) {
    return _theLibrary.socket(domain, type, protocol);
  }

  /**
   * Create two new sockets, of type TYPE in domain DOMAIN and using protocol PROTOCOL, which are
   * connected to each other, and put file descriptors for them in FDS[0] and FDS[1]. If PROTOCOL is
   * zero, one will be chosen automatically. Returns 0 on success, -1 for errors.
   *
   * @param domain domain.
   * @param type type.
   * @param protocol protocol.
   * @param fds file descriptors.
   * @return returns 0 on success, -1 for errors.
   */
  public static int socketpair(int domain, int type, int protocol, Pointer<Integer> fds) {
    return _theLibrary.socketpair(domain, type, protocol, fds);
  }

  /**
   * Give the socket FD the local address ADDR (which is LEN bytes long).
   *
   * @param fd file descriptor.
   * @param addr address.
   * @param len length.
   */
  public static int bind(int fd, Pointer<sockaddr> addr, int len) {
    return _theLibrary.bind(fd, addr, len);
  }

  /**
   * Put the local address of FD into *ADDR and its length in *LEN.
   *
   * @param fd file descriptor.
   * @param addr address.
   * @param len length.
   */
  public static int getsockname(int fd, Pointer<sockaddr> addr, Pointer<Integer> len) {
    return _theLibrary.getsockname(fd, addr, len);
  }

  /**
   * Open a connection on socket FD to peer at ADDR (which LEN bytes long). For connectionless
   * socket types, just set the default address to send to and the only address from which to accept
   * transmissions. Return 0 on success, -1 for errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param addr address.
   * @param len length.
   * @return return 0 on success, -1 for errors.
   */
  public static int connect(int fd, Pointer<sockaddr> addr, int len) {
    return _theLibrary.connect(fd, addr, len);
  }

  /**
   * Put the address of the peer connected to socket FD into *ADDR (which is *LEN bytes long), and
   * its actual length into *LEN.
   *
   * @param fd file descriptor.
   * @param addr address.
   * @param len length.
   */
  public static int getpeername(int fd, Pointer<sockaddr> addr, Pointer<Integer> len) {
    return _theLibrary.getpeername(fd, addr, len);
  }

  /**
   * Send N bytes of BUF to socket FD. Returns the number sent or -1.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param buf buffer.
   * @param n byte size.
   * @param flags flags.
   * @return returns the number sent or -1.
   */
  public static long send(int fd, Pointer<?> buf, long n, int flags) {
    return _theLibrary.send(fd, buf, n, flags);
  }

  /**
   * Read N bytes into BUF from socket FD. Returns the number read or -1 for errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param buf buffer.
   * @param n byte size.
   * @param flags flags.
   * @return returns the number read or -1 for errors.
   */
  public static long recv(int fd, Pointer<?> buf, long n, int flags) {
    return _theLibrary.recv(fd, buf, n, flags);
  }

  /**
   * Send N bytes of BUF on socket FD to peer at address ADDR (which is ADDR_LEN bytes long).
   * Returns the number sent, or -1 for errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param buf buffer.
   * @param n byte size.
   * @param flags flags.
   * @param addr address.
   * @param addrLen address length.
   */
  public static long sendto(
      int fd, Pointer<?> buf, long n, int flags, Pointer<sockaddr> addr, int addrLen) {
    return _theLibrary.sendto(fd, buf, n, flags, addr, addrLen);
  }

  /**
   * Read N bytes into BUF through socket FD. If ADDR is not NULL, fill in *ADDR_LEN bytes of it
   * with tha address of the sender, and store the actual size of the address in *ADDR_LEN. Returns
   * the number of bytes read or -1 for errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param buf buffer.
   * @param n byte size.
   * @param flags flags.
   * @param addr address.
   * @param addrLen address length.
   * @return returns the number of bytes read or -1 for errors.
   */
  public static long recvfrom(
      int fd, Pointer<?> buf, long n, int flags, Pointer<sockaddr> addr, Pointer<Integer> addrLen) {
    return _theLibrary.recvfrom(fd, buf, n, flags, addr, addrLen);
  }

  /**
   * Send a message described MESSAGE on socket FD. Returns the number of bytes sent, or -1 for
   * errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param message message.
   * @param flags flags.
   * @return returns the number of bytes sent, or -1 for errors.
   */
  public static long sendmsg(int fd, Pointer<msghdr> message, int flags) {
    return _theLibrary.sendmsg(fd, message, flags);
  }

  /**
   * Receive a message as described by MESSAGE from socket FD. Returns the number of bytes read or
   * -1 for errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param message message.
   * @param flags flags.
   */
  public static long recvmsg(int fd, Pointer<msghdr> message, int flags) {
    return _theLibrary.recvmsg(fd, message, flags);
  }

  /**
   * Put the current value for socket FD's option OPTNAME at protocol level LEVEL into OPTVAL (which
   * is *OPTLEN bytes long), and set *OPTLEN to the value's actual length. Returns 0 on success, -1
   * for errors.
   *
   * @param fd file descriptor.
   * @param level level.
   * @param optname option name.
   * @param optval option value.
   * @param optlen option length.
   * @return returns 0 on success, -1 for errors.
   */
  public static int getsockopt(
      int fd, int level, int optname, Pointer<?> optval, Pointer<Integer> optlen) {
    return _theLibrary.getsockopt(fd, level, optname, optval, optlen);
  }

  /**
   * Set socket FD's option OPTNAME at protocol level LEVEL to *OPTVAL (which is OPTLEN bytes long).
   *
   * @param fd file descriptor.
   * @param level level.
   * @param optname option name.
   * @param optval option value.
   * @param optlen option length.
   */
  public static int setsockopt(int fd, int level, int optname, Pointer<?> optval, int optlen) {
    return _theLibrary.setsockopt(fd, level, optname, optval, optlen);
  }

  /**
   * Prepare to accept connections on socket FD. N connection requests will be queued before further
   * requests are refused. Returns 0 on success, -1 for errors.
   *
   * @param fd file descriptor.
   * @param n number of connection request will queued.
   * @return returns 0 on success, -1 for errors.
   */
  public static int listen(int fd, int n) {
    return _theLibrary.listen(fd, n);
  }

  /**
   * Await a connection on socket FD. When a connection arrives, open a new socket to communicate
   * with it, set *ADDR (which is *ADDR_LEN bytes long) to the address of the connecting peer and
   * *ADDR_LEN to the address's actual length, and return the new socket's descriptor, or -1 for
   * errors.
   *
   * <p>This function is a cancellation point and therefore not marked with __THROW.
   *
   * @param fd file descriptor.
   * @param addr address.
   * @param addrLen address length.
   * @return return the ew socket's descriptor, or -1 for errors.
   */
  public static int accept(int fd, Pointer<sockaddr> addr, Pointer<Integer> addrLen) {
    return _theLibrary.accept(fd, addr, addrLen);
  }

  /**
   * Shut down all or part of the connection open on socket FD. HOW determines what to shut down:
   * SHUT_RD = No more receptions; SHUT_WR = No more transmissions; SHUT_RDWR = No more receptions
   * or transmissions. Returns 0 on success, -1 for errors.
   *
   * @param fd file descriptor.
   * @param how cause.
   * @return returns 0 on success, -1 for errors.
   */
  public static int shutdown(int fd, int how) {
    return _theLibrary.shutdown(fd, how);
  }

  /**
   * Determine whether socket is at a out-of-band mark.
   *
   * @param fd file descriptor.
   */
  public static int sockatmark(int fd) {
    return _theLibrary.sockatmark(fd);
  }

  /**
   * FDTYPE is S_IFSOCK or another S_IF* macro defined in <sys/stat.h>; returns 1 if FD is open on
   * an object of the indicated type, 0 if not, or -1 for errors (setting errno).
   *
   * @param fd file descriptor.
   * @param fdtype file descriptor type.
   * @return returns 1 if FD is open on an object of the indicated type, 0 if not, or -1 for errors
   *     (setting errno).
   */
  public static int isfdtype(int fd, int fdtype) {
    return _theLibrary.isfdtype(fd, fdtype);
  }

  @NativeHeader(
      resolutionContext = {
        in6_addr.class,
        in_addr.class,
        iovec.class,
        msghdr.class,
        sockaddr.class,
        sockaddr_in.class,
        sockaddr_in6.class,
        sockaddr_storage.class
      })
  interface socket_h {

    @NativeFunction("(i32i32i32)i32")
    int socket(int var1, int var2, int var3);

    @NativeFunction("(i32i32i32u64:i32)i32")
    int socketpair(int var1, int var2, int var3, Pointer<Integer> var4);

    @NativeFunction("(i32u64:${sockaddr}u32)i32")
    int bind(int var1, Pointer<sockaddr> var2, int var3);

    @NativeFunction("(i32u64:${sockaddr}u64:u32)i32")
    int getsockname(int var1, Pointer<sockaddr> var2, Pointer<Integer> var3);

    @NativeFunction("(i32u64:${sockaddr}u32)i32")
    int connect(int var1, Pointer<sockaddr> var2, int var3);

    @NativeFunction("(i32u64:${sockaddr}u64:u32)i32")
    int getpeername(int var1, Pointer<sockaddr> var2, Pointer<Integer> var3);

    @NativeFunction("(i32u64:vu64i32)i64")
    long send(int var1, Pointer<?> var2, long var3, int var5);

    @NativeFunction("(i32u64:vu64i32)i64")
    long recv(int var1, Pointer<?> var2, long var3, int var5);

    @NativeFunction("(i32u64:vu64i32u64:${sockaddr}u32)i64")
    long sendto(int var1, Pointer<?> var2, long var3, int var5, Pointer<sockaddr> var6, int var7);

    @NativeFunction("(i32u64:vu64i32u64:${sockaddr}u64:u32)i64")
    long recvfrom(
        int var1,
        Pointer<?> var2,
        long var3,
        int var5,
        Pointer<sockaddr> var6,
        Pointer<Integer> var7);

    @NativeFunction("(i32u64:${msghdr}i32)i64")
    long sendmsg(int var1, Pointer<msghdr> var2, int var3);

    @NativeFunction("(i32u64:${msghdr}i32)i64")
    long recvmsg(int var1, Pointer<msghdr> var2, int var3);

    @NativeFunction("(i32i32i32u64:vu64:u32)i32")
    int getsockopt(int var1, int var2, int var3, Pointer<?> var4, Pointer<Integer> var5);

    @NativeFunction("(i32i32i32u64:vu32)i32")
    int setsockopt(int var1, int var2, int var3, Pointer<?> var4, int var5);

    @NativeFunction("(i32i32)i32")
    int listen(int var1, int var2);

    @NativeFunction("(i32u64:${sockaddr}u64:u32)i32")
    int accept(int var1, Pointer<sockaddr> var2, Pointer<Integer> var3);

    @NativeFunction("(i32i32)i32")
    int shutdown(int var1, int var2);

    @NativeFunction("(i32)i32")
    int sockatmark(int var1);

    @NativeFunction("(i32i32)i32")
    int isfdtype(int var1, int var2);
  }
}
