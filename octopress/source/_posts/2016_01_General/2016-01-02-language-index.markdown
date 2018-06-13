---
layout: post
title: "Language index"
date: 2011-01-02 10:07:55 -0700
comments: true
categories: 
- java
- python
- javascript
---

Overview of most common languages.

### [Java](https://tdongsi.github.io/java/)

#### Pure Java

Questions:

1. What is OOP? What is polymorphism? etc.
2. Some standard design patterns in Java: Singleton (enum and two locks), Builder, Decorator, Factory.
3. Examples of design patterns in Java.
4. Relations between `hashCode()` and `equals()`.
5. Implements a simple algorithm for `hashCode()`.
6. When to use `finalize()` and when not to use `finalize()`. See "Effective Java".
7. Java concurrency: what is `volatile`? when to use it? `synchronized`?
8. Some legacy items: Diff between StringBuilder vs StringBuffer. 

#### Groovy

#### Tools and Ecosystem

### [Python](https://tdongsi.github.io/python/)

* [What's new in Python 3](https://docs.python.org/3/whatsnew/3.0.html)
  * Essential: Maybe only Python 2 is supported in interviews while you've been using Python 3 or vice versa. Learn this to unblock yourself.
  * Bonus points to explain that to interviewers.

Warm-up question: Write a function that reads file numbers.txt and outputs numbers-mult.txt where each number in numbers.txt is multiplied by its line number, starting from 1.

``` python Warm up exercise
def mult_file(INFILE='numbers.txt', OUTFILE='numbers-mult.txt'):
    try:
        with open(INFILE, 'r') as infile, open(OUTFILE, 'w') as outfile:
            for count, line in enumerate(infile, 1):
                line.strip()
                if line:
                    out = count * int(line)
                    outfile.write("%d\n" % out)
    except IOError as e:
        print("Can't open files %s" % e)
```

This is a good warm-up exercise for Python since it touches many common operations in Python: reading from/writing to files, string to int. 
It is aslo not trivial to write Pythonic code for it.

### [JavaScript](https://tdongsi.github.io/javascript/)

### Next language?

* Golang
* Ruby

<!--more-->

### Java Answers

1. Know your audience. 
   A new grad will mention something about abstraction, polymorphism, inheritance, encapsulation (A PIE) and pass. 
   Alan Kay (father of OOP) will say something about message passing and late binding and might fail. 