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

### Parameter expansion

Explanation on `VARIABLE1="${VARIABLE1:-some_val}"` idiom.

```
$ echo "$VAR1"

$ VAR1="${VAR1:-default value}"
$ echo "$VAR1"
default value
```

```
$ VAR1="has value"
$ echo "$VAR1"
has value

$ VAR1="${VAR1:-default value}"
$ echo "$VAR1"
has value
```

Other common usage:

* Simple usage
  * $PARAMETER
  * ${PARAMETER}
* Indirection
  * ${!PARAMETER}
* Case modification
  * ${PARAMETER^}: first character to upper case
  * ${PARAMETER^^}: all characters to upper cases
  * ${PARAMETER,}: first character to lower case
  * ${PARAMETER,,}: all characters to lower cases
  * ${PARAMETER~}: first character to reverse case
  * ${PARAMETER~~}: all characters to reverse cases.
* Substring removal: useful for filename manipulation
  * ${PARAMETER#PATTERN}: shortest text matching the pattern. E.g.: `${MYSTRING#* }`
  * ${PARAMETER##PATTERN}: longest text matching the pattern. E.g.: `${MYSTRING##* }`
  * ${PARAMETER%PATTERN}: shortest text fron the end
  * ${PARAMETER%%PATTERN}: longest text from the end

Examples of substring removal:

* Get name without extension: `${FILENAME%.*}` where FILENAME=bash_hackers.txt
* Get extension: `${FILENAME##*.}`
* Get directory name: `${PATHNAME%/*}` where PATHNAME=/home/bash/bash_hackers.txt
* Get filename in path: `${PATHNAME##*/}`


Reference:

* [Stack overflow](https://unix.stackexchange.com/questions/122845/using-a-b-for-variable-assignment-in-scripts/122878)
* [Parameter expansion](http://wiki.bash-hackers.org/syntax/pe)