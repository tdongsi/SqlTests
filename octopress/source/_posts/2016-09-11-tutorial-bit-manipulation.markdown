---
layout: post
title: "Questions: Bit Manipulation"
date: 2016-09-11 01:23:25 -0700
comments: true
categories: 
- Questions
- Algorithm
- Apple
- TODO
---


### Questions

1. Addition using only bit manipulation.
1. Subtraction using only bit manipulation.
1. Find odd one out of pairs.

### Answers

(1) Addition: Recursive formula `x + y = (x ^ y) + (x & y) << 1` is the most easy to understand.
In code, it is implemented as follows:

``` java Addition
    private int add(int a, int b) {
        if(b == 0)
            return a;

        return add( a ^ b, (a & b) << 1);
    }
```

(2) Subtraction: The recursive formula is `x + y = (x ^ y) + (x & y) << 1`.
In code, it is implemented as follows:

``` java Subtraction
TODO
```

(3) Find odd one.

TODO: From hard-copy notes.