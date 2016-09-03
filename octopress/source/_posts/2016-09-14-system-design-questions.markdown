---
layout: post
title: "System Design questions"
date: 2016-09-14 02:13:01 -0700
comments: true
categories: 
- Tutorial
- SystemDesign
- Questions
---

How to practice for System Design questions and some design questions.

<!--more-->

### Readings

For Web Services, read "Architecting in AWS": recognize scalability problems that AWS services trying to address and replace, for example, "AWS Load Balancer" with generic load balancer.

Read these to know the broad topics that are expected.

1. [Quora question](https://www.quora.com/What-system-design-distributed-systems-+-scalability-topics-should-I-study-in-order-to-adequately-prepared-for-a-Google-Software-Engineer-interview)
2. [The Technical Design Interview - A Guide to Success](https://www.linkedin.com/pulse/technical-design-interview-guide-success-joey-addona)
3. [Test Design & Architecture Interview - Tips to success](https://www.linkedin.com/pulse/test-design-architecture-interview-tips-success-kane-ho)

### Questions

1. Design a simple file system using OO programming. Just folder and files.
1. How to design a load balancer?

### Answers

(1) Design a simple file system using OO programming. Just folder and files.

Use Composite pattern.

``` java
class FileNode {
  String name;
}

class File extends FileNode {
  long size;
}

class Folder extends FileNode {
  Collection<FileNode> children;
}
```

(2) How to design a load balancer?

Simple: hash and assign random. What are pros and cons?

