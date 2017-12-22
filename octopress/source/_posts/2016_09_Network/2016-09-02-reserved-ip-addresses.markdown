---
layout: post
title: "Reserved IP addresses"
date: 2017-12-21 19:22:02 -0800
comments: true
categories: 
- Network
---

Various IP addresses are reserved for special purposes.

### IP v4

Broadcast address: an address where all host bits in the IP address are set to one (1). 
All hosts are to accept and respond to the broadcast address.

The 127.0.0.0/8 class 'A' subnet is used for special local addresses, most commonly the loopback address 127.0.0.1.

**Private IP addresses**: a number of IP blocks which were set aside by ARIN for use as private addresses on private networks that are not directly connected to the Internet.

``` plain Private IP addresses
Class   CIDR            Start       End
A       10.0.0.0/8	    10.0.0.0	10.255.255.255
B       172.16.0.0/12	172.16.0.0	172.31.255.255
C	    192.168.0.0/16  192.168.0.0	192.168.255.255
```

**Special purposes**: There are a number of addresses that are set aside for special purposes, such as the IP's used in OSPF, Multicast, and experimental purposes that cannot be used on the Internet.

``` plain Reserved for multicast
Class   CIDR            Start       End
D       224.0.0.0/4	    224.0.0.0	239.255.255.255
```

**Others**:

```
Address Block	CIDR Mask	Used for	                            Reference
0.0.0.0	        /8	        Used to communicate with "This" network	RFC1700, p. 4
10.0.0.0	    /8	        Private-Use Networks	                RFC 1918
14.0.0.0	    /8	        Public-Data Network	                    RFC1700, p.181
24.0.0.0	    /8	        Cable TV Networks	                    --
127.0.0.0	    /8	        Loopback address
```

### IP v6

``` plain Important adress blocks
Address block (CIDR)	Range	                Number of addresses	Scope	    Purpose
::/128	                ::	                    1	                Software	Unspecified address
::1/128	                ::1	                    1	                Host	    Loopback address to the local host.
::ffff:0:0/96	        ::ffff:0.0.0.0 –
                        ::ffff:255.255.255.255	2^32	                Software	IPv4 mapped addresses
```

### References

* [Wiki page](https://en.wikipedia.org/wiki/Reserved_IP_addresses)
* [Easier to read](http://www.inetdaemon.com/tutorials/internet/ip/addresses/special.shtml)
