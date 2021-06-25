---
layout: post
title: "Questions: Bit Manipulation"
date: 2012-02-28 01:23:25 -0700
comments: true
categories: 
- Questions
- Algorithm
- Apple
---


### Questions

1. Addition using only bit manipulation.
2. Subtraction using only bit manipulation.
3. Odd Man Out: Given an unsorted array of integers where every integer appears exactly twice, except for one integer which appears once. Find the odd one out.
4. Quickly determine if a number is a power of 2 (i.e., n = 2^x).
5. What is big-endian? What is little-endian?
6. How would you determine if the system is big-endian or little-endian.
7. Isolate the right-most 1 bit in `x`.
8. Hamming Weight of an integer x (Hamming distance of x and 0).
9. Java: Write a function to calculate the absolute value of a 32-bit integer without branching.
10. Hamming Distance of two integers a and b.

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

(7) `x & ~(x-1)`.

(8) 

``` python Hamming weight
def hamming_weight(x: int) -> int:
    """ Hamming weight is effectively Hamming distance between x and 0.
    """
    count = 0

    while x != 0:
        # increment the cound
        count += 1
        # clear the least significant bit
        x &= x - 1

    return count
```

(9)

``` java Absolute without branching
public int abs(int num) {
    // mask will be all 1s for negative, all 0s for positive
    int mask = num >> 31

    // Effectively: if num < 0
    // Toggle the bits and add 1. 
    // For two's complement format. All 1s = -1
    return num ^ mask - mask
}
```

(10)

```python Hamming distance
def hamming_distance_int(x: int, y: int) -> int:
    diff = x ^ y
    count = 0

    while diff != 0:
        # increment the cound
        count += 1
        # clear the least significant bit
        diff &= diff - 1

    return count
```
