---
layout: post
title: "Questions: Bit Manipulation"
date: 2016-02-28 01:23:25 -0700
comments: true
categories: 
- Questions
- Algorithm
- Apple
---


### Questions

1. Addition using only bit manipulation.
1. Subtraction using only bit manipulation.
1. Odd Man Out: Given an unsorted array of integers where every integer appears exactly twice, except for one integer which appears once. Find the odd one out.
1. Quickly determine if a number is a power of 2 (i.e., n = 2^x).
1. What is big-endian? What is little-endian?
1. How would you determine if the system is big-endian or little-endian.

<!--more-->

### Answers

(1) [Addition](http://stackoverflow.com/questions/4068033/add-two-integers-using-only-bitwise-operators): Recursive formula `x + y = (x ^ y) + (x & y) << 1` is the most easy to understand.
In code, it is implemented as follows:

``` java Addition
    private int add(int a, int b) {
        if(b == 0)
            return a;

        return add( a ^ b, (a & b) << 1);
    }
```

(2) [Subtraction](http://www.geeksforgeeks.org/subtract-two-numbers-without-using-arithmetic-operators/): The recursive formula is `x - y = (x ^ y) - (~x & y) << 1`.
In code, it is implemented as follows:

``` java Subtraction
    private int subtract(int a, int b) {
        if(b == 0)
            return a;

        return subtract( a ^ b, (~a & b) << 1);
    }
```

(3) Find odd one.

XOR all the values of the array together. `a XOR a = 0`.  

(4) Quickly determine if a number is a power of 2.

If x == 0, return False. Then, check `x & (x-1) == 0`.

(5) Big-endian stores the most significant byte first, while little-endian stores the least significant byte first.
Fields in the protocols of the Internet protocol suite, such as IPv4, IPv6, TCP, and UDP, are transmitted in big-endian order. 
For this reason, big-endian byte order is also referred to as network byte order.

(6) Use union type in C. Then check it based on definition.

``` c Check endianess
union Data
{
int i;
char str[2];
} data;

data.i = 0x00ff;
printf( "data.str[0]: %d\n", data.str[0]);
```

Or use [`htonl`](https://linux.die.net/man/3/htonl) function (Host TO Network Long) in Linux.
If outputs are the same as inputs, it is big-endian since data is transmitted in big-endian order in many Internet protocols.
