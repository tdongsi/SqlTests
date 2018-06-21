---
layout: post
title: "Questions: Operating System drills"
date: 2016-06-03 00:42:41 -0700
comments: true
categories: 
- Questions
- OS
- TODO
---

From "Operating System concepts" book.

<!--more-->

Drill:

1. Chapter 1: Storage device hierarchy
2. Chapter 2: System calls and Linux examples. System boot.
3. Chapter 3: Process diagram. Process state diagram. Zombie, orphan process. Practice Exercise 3.1, 3.2.
4. Conditions of deadlock. How to avoid deadlocks.

Links:

* http://duartes.org/gustavo/blog/post/anatomy-of-a-program-in-memory/
* http://duartes.org/gustavo/blog/post/how-the-kernel-manages-your-memory/
* http://duartes.org/gustavo/blog/post/page-cache-the-affair-between-memory-and-files/

Practice exercises:

* http://codex.cs.yale.edu/avi/os-book/OS9/practice-exer-dir/index.html

### Answers

(4) How to avoid deadlocks based on conditions of deadlock:

1. No pre-emption: 
2. Hold and wait: Obtain all required resources at the beginning, no waiting -> many retries.
3. Circular wait: Reordering acquisition to avoid circular wait.
4. Mutual exclusion: Adding resources.