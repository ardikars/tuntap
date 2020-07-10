/** This code is licenced under the BSD-3-Clause. */
package tuntap.api.constant;

import pcap.common.util.Platforms;

public class SendFlag {

  /* Process out-of-band data.  */
  public static int MSG_OOB() {
    if (Platforms.isLinux()) {
      return 0x01;
    }
    return 0;
  }

  /* Peek at incoming messages.  */
  public static int MSG_PEEK() {
    if (Platforms.isLinux()) {
      return 0x02;
    }
    return 0;
  }

  /* Don't use local routing.  */
  public static int MSG_DONTROUTE() {
    if (Platforms.isLinux()) {
      return 0x04;
    }
    return 0;
  }

  /* Control data lost before delivery.  */
  public static int MSG_CTRUNC() {
    if (Platforms.isLinux()) {
      return 0x08;
    }
    return 0;
  }

  /* Supply or ask second address.  */
  public static int MSG_PROXY() {
    if (Platforms.isLinux()) {
      return 0x10;
    }
    return 0;
  }

  public static int MSG_TRUNC() {
    if (Platforms.isLinux()) {
      return 0x20;
    }
    return 0;
  }

  /* Nonblocking IO.  */
  public static int MSG_DONTWAIT() {
    if (Platforms.isLinux()) {
      return 0x40;
    }
    return 0;
  }

  /* End of record.  */
  public static int MSG_EOR() {
    if (Platforms.isLinux()) {
      return 0x80;
    }
    return 0;
  }

  /* Wait for a full request.  */
  public static int MSG_WAITALL() {
    if (Platforms.isLinux()) {
      return 0x100;
    }
    return 0;
  }

  public static int MSG_FIN() {
    if (Platforms.isLinux()) {
      return 0x200;
    }
    return 0;
  }

  public static int MSG_SYN() {
    if (Platforms.isLinux()) {
      return 0x400;
    }
    return 0;
  }

  /* Confirm path validity.  */
  public static int MSG_CONFIRM() {
    if (Platforms.isLinux()) {
      return 0x800;
    }
    return 0;
  }

  public static int MSG_RST() {
    if (Platforms.isLinux()) {
      return 0x1000;
    }
    return 0;
  }

  /* Fetch message from error queue.  */
  public static int MSG_ERRQUEUE() {
    if (Platforms.isLinux()) {
      return 0x2000;
    }
    return 0;
  }

  /* Do not generate SIGPIPE.  */
  public static int MSG_NOSIGNAL() {
    if (Platforms.isLinux()) {
      return 0x4000;
    }
    return 0;
  }

  /* Sender will send more.  */
  public static int MSG_MORE() {
    if (Platforms.isLinux()) {
      return 0x8000;
    }
    return 0;
  }

  /* Wait for at least one packet to return. */
  public static int MSG_WAITFORONE() {
    if (Platforms.isLinux()) {
      return 0x10000;
    }
    return 0;
  }

  /* sendmmsg: more messages coming.  */
  public static int MSG_BATCH() {
    if (Platforms.isLinux()) {
      return 0x40000;
    }
    return 0;
  }

  /* Use user data in kernel path.  */
  public static int MSG_ZEROCOPY() {
    if (Platforms.isLinux()) {
      return 0x4000000;
    }
    return 0;
  }

  /* Send data in TCP SYN.  */
  public static int MSG_FASTOPEN() {
    if (Platforms.isLinux()) {
      return 0x20000000;
    }
    return 0;
  }

  /* Set close_on_exit for file descriptor received through SCM_RIGHTS.  */
  public static int MSG_CMSG_CLOEXEC() {
    if (Platforms.isLinux()) {
      return 0x40000000;
    }
    return 0;
  }
}
