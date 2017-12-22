---
layout: post
title: "Bash cookbook"
date: 2016-12-03 00:31:51 -0800
comments: true
categories: 
- Bash
---

All various tricky bash/Linux questions.

<!--more-->

### Remove files with special characters such as `-`

Sometimes, when you run a script accidentally, you might end up creating a file with special characters in its name, making deletion hard in conventional way. Some examples include `> option[value='2016']` and `-myfile`.

Based on [this](https://unix.stackexchange.com/questions/229348/how-to-remove-a-file-that-start-with-or-other-unusual-characters), it is possible to remove those files by inode number.

``` plain
ls -i 
-> 5233 > option[value='2016']   5689 foo
find . -inum 5233 -print
find . -inum 5233 -delete
```

You can also use the `--` option according to `man`:

```
 The rm command uses getopt(3) to parse its arguments, which allows it to
 accept the `--' option which will cause it to stop processing flag options at
 that point.  This will allow the removal of file names that begin with a dash
 (`-').  For example:
       rm -- -filename
```
