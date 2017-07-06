---
layout: post
title: "User group and related commands"
date: 2016-12-01 19:21:32 -0700
comments: true
categories: 
- Bash
---

User groups and commands to manipulate them.

<!--more-->

### `groupadd` examples

The following example creates a new group called apache

```
$ groupadd apache
```

Make sure it is created successfully.

```
# grep apache /etc/group
apache:x:1004:
```

If you don’t specify a groupid, Linux will assign one automatically.
If you want to create a group with a specific group id, do the following.

```
$ groupadd apache -g 9090

$ grep 9090 /etc/group
apache:x:9090:
```

Group account information is stored in `/etc/group`.
Or you can run the following command to find out what groups you belong to.

``` plain Check user group
+ groups $USER

+ cat /etc/group
...
docker:x:999:jenkins
...
```

### References

* [groupadd man pages](https://linux.die.net/man/8/groupadd)
* [groupadd examples](http://linux.101hacks.com/unix/groupadd/)
* [chown examples](http://www.thegeekstuff.com/2012/06/chown-examples/)
* [find files with group name or ID](https://www.unixtutorial.org/2008/06/find-files-which-belong-to-a-user-or-unix-group/)
* https://unix.stackexchange.com/questions/33844/change-gid-of-a-specific-group