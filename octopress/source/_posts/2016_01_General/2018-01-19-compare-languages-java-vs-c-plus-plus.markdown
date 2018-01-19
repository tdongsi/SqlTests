---
layout: post
title: "Compare languages: Java vs. C++"
date: 2016-01-11 10:11:40 -0800
comments: true
categories: 
- Java
- C++
---

How to answer the question "What is the difference between Java and C++?".
Knowing the subtle difference between similar languages shows mastery of both.

<!--more-->

### Strict differences

Java

1. Multiple inheritance: by implementing multiple interfaces, only extending one superclass.
2. Java is reflective, allowing dynamic code generation.
3. Automatic garbage collection. The concept of destructor and using finalize() is not recommended.
4. Operator overloading is not possible [2]. Only + overladed for String.
5. Generic programming: syntactic sugar for backward compatibility (can be removed), just a wrapper for object casting.

C++

1. Extending multiple classes are allowed
2. C++ provides only object types and class name.
3. Explicit memory allocation and deallocation.
4. Operator overloading is very common.
5. Advanced version of preprocessor/macro programming.

### Practical differences

In practice, the patterns of using two languages can be very different.

Java

1. Using Interfaces and Listeners to achieve similar effects.
2. final has more limited usage.
3. Label can be used in loops, such as break from nested `for` loops.
4. Most libraries are based on class inheritance and object-oriented programming.

C++

1. Function pointers, function objects are common.
2. const keyword has more functions and is more commonly used.
3. goto statement is supported but not recommended.
4. Most libraries are based on templates and generic programming.

Java Generics are massively different to C++ templates. 
Basically in C++ templates are basically a glorified preprocessor/macro set.
Java Generics are basically syntactic sugar (things can be removed from the language, added just to make expressions more clearly)