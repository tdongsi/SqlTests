---
layout: post
title: "System Design questions"
date: 2016-09-14 02:13:01 -0700
comments: true
categories: 
- SystemDesign
- Questions
- TODO
---


https://www.quora.com/What-system-design-distributed-systems-+-scalability-topics-should-I-study-in-order-to-adequately-prepared-for-a-Google-Software-Engineer-interview


Reviews:
The Technical Design Interview - A Guide to Success
Test Design & Architecture Interview - Tips to success

Read “Architecting in AWS”: replacing AWS Load Balancer with generic load balancer.

== Questions ==

1. Design a simple file system using OO programming. Just folder and files.


== Answers ==

1. Use composite pattern

class FileNode {
  String name;
}

class File extends FileNode {
  long size;
}

class Folder extends FileNode {
  Collection<FileNode> children;
}
