---
layout: post
title: "System Design: Distributed System"
date: 2017-02-01 22:49:54 -0700
comments: true
categories: 
- Design
---

### Collection of advices on Blind

Just make sure you study consistent hashing and understand it well, almost every system design interview I've had boiled down to applying it (Airbnb, FB, and Google twice).

Theory : Understand basic things like CAP, PAXOS, Blockchain, LSM/B-Tree, two phase commit.

Implement: Consistent hashing (I observed more than one candidate that didn’t really understand what it is - it is not complicated. Code it up, you will get it)

Read : Existing designs/research - Zookeeper, Haystack , Cassandra, MapReduce, HDFS/Hadoop, Spanner (too many to list)

Scale : Practice analyzing bottlenecks in all the systems above. Throughput/Storage/Number of Items/something else? Network/memory/cpu/IOPS/.../$$

Like in real projects, understand requirements - ask questions and/or state assumptions you are making. Do not BS - no need to take any risk here, just ask for help when you need it.

Good luck on your job hunt - leetcode might not be sufficient for senior backend roles anymore.

### Topics

* General training:
  * [Safari course](https://www.safaribooksonline.com/library/view/distributed-systems-in/9781491924914/)

Basic topics in distributed system (based on the above Safari course):

* Storage: Relational/MongoDB, Cassandra, HDFS
* Computation: Hadoop, Storm, Spark
* Sync: NTP, vector clocks
* Consensus: Paxos, Zookeeper
* Messaging: Kafka

#### Consistent Hashing

* https://www.toptal.com/big-data/consistent-hashing
* http://michaelnielsen.org/blog/consistent-hashing/
  * http://michaelnielsen.org/blog/a-number-theoretic-approach-to-consistent-hashing/
* 