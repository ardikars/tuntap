#include <stdio.h>
#include <fcntl.h>
#include <stdint.h>
#include <string.h>

#include <sys/socket.h>
#include <linux/if.h>
#include <linux/if_tun.h>
#include <sys/ioctl.h>
#include <unistd.h>

int tuntap_set_name(int fd, char *name, int flags) {
    int rc;
	struct ifreq ifr;
	memset(&ifr, 0, sizeof ifr);
	if ((rc = snprintf(ifr.ifr_name, IFNAMSIZ, "%s", name)) < 0) {
	    return rc;
	}
    ifr.ifr_flags = flags;
	if ((rc = ioctl(fd, TUNSETIFF, &ifr)) != 0) {
		return rc;
	}
	snprintf(name, IFNAMSIZ, "%s", ifr.ifr_name);
	return 0;
}

int tuntap_set_up(char *name) {
    int fd;
    int rc;
    struct ifreq ifr;
    memset(&ifr, 0, sizeof ifr);

    if ((fd = socket(AF_INET, SOCK_DGRAM, PF_UNSPEC)) < 0) {
        return fd;
    }
    if ((rc = snprintf(ifr.ifr_name, IFNAMSIZ, "%s", name)) < 0) {
        close(fd);
        return rc;
    }
    if ((rc = ioctl(fd, SIOCGIFFLAGS, &ifr)) != 0) {
        close(fd);
        return rc;
    }
    ifr.ifr_flags |= IFF_UP;
    if ((rc = ioctl(fd, SIOCSIFFLAGS, &ifr)) != 0) {
        close(fd);
        return rc;
    }
    close(fd);
    return 0;
}

int tuntap_set_down(char *name) {
    int fd;
    int rc;
    struct ifreq ifr;
    memset(&ifr, 0, sizeof ifr);

    if ((fd = socket(AF_INET, SOCK_DGRAM, PF_UNSPEC)) < 0) {
        return fd;
    }
    if ((rc = snprintf(ifr.ifr_name, IFNAMSIZ, "%s", name)) < 0) {
        close(fd);
        return rc;
    }
    if ((rc = ioctl(fd, SIOCGIFFLAGS, &ifr)) != 0) {
        close(fd);
        return rc;
    }
    ifr.ifr_flags |= IFF_UP;
    if ((rc = ioctl(fd, SIOCSIFFLAGS, &ifr)) != 0) {
        close(fd);
        return rc;
    }
    close(fd);
    return 0;
}
