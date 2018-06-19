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

Warm-up question 1: Write a function that reads file numbers.txt and outputs numbers-mult.txt where each number in numbers.txt is multiplied by its line number, starting from 1.

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

Warm-up question 2: You are given a search string and a magazine. 
You seek to generate all the characters in search string by cutting them out from the magazine. 
Give an algorithm to efficiently determine whether the magazine contains all the letters in the search string.

``` python Text from magazine
def create_text_from_magazine(text, magazine):
    """ Create search string from magazines.
    :param text: Text you want to generate.
    :param magazine: Where you get characters from.
    :return: True or False
    """
    from collections import Counter

    text_count = Counter(text.strip().lower())
    del text_count[' ']  # We don't care about spaces

    lines = magazine.split("\n")
    for line in lines:
        mag_count = Counter(line.strip().lower())
        text_count -= mag_count
        # print(text_count)
        if not text_count:
            return True

    return False
```

### [JavaScript](https://tdongsi.github.io/javascript/)

### Next language?

* Golang
* Ruby

<!--more-->

### Java Answers

1. Know your audience. 
   A new grad will mention something about abstraction, polymorphism, inheritance, encapsulation (A PIE) and pass. 
   Alan Kay (father of OOP) will say something about message passing and late binding and might fail. 