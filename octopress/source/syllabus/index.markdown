---
layout: page
title: "Guide to technical interviews"
date: 2016-08-28 18:42
comments: true
sharing: true
footer: true
---

### Topics


	1. Coding
		1. OOP
		2. Functional Prog
		3. Unit testing
		4. Logging
		5. Language-specific

	2. Algorithms
		1. Sorting
			1. Quick sort
			2. Merge sort
			3. Heap sort
			4. Insertion sort
			5. Radix sort
			6. Runtime

		2. Binary search
		3. Tree
			1. Construction
			2. Traversal

		4. Dynamic Programming
		5. Problems
		6. Special algorithms you come across

	3. Data structures
		1. Hashtables
			1. Implementation: You should be able to implement one using only arrays in your favorite language, in about the space of one interview.

		2. List
			1. Arrays
			2. Linked Lists
			3. Stacks
			4. Queues

		3. Priority Queues
			1. Heap: variants

		4. NP-complete problems

	4. Mathematics
		1. Probability

	5. Graph
		1. Representation
			1. 3 ways
			2. Pros & Cons

		2. BFS
		3. DFS
		4. Dijkstra
		5. A*
		6. Cycle detection

	6. Operating Systems
	7. System Design
	8. Database
		1. Normalization.
		2. Normal forms.
		3. Database interview questions

	9. Network
	10. Soft questions

#### More on Technical Preparation

Summarized from [this Google doc](https://docs.google.com/presentation/d/1_6c6eu1oaDcJeKGcu43wtal8OeFNL6xMmmoSiDt9l5A/edit?pref=2&pli=1#slide=id.gcb7e7ef4e_478_71).

Algorithms: You will be expected to know the complexity of an algorithm and how you can improve/change it.
Big-O notation is also known as the run time characteristic of an algorithm. 
If you get a chance, try to study up on fancier algorithms, such as Dijkstra and A*. 
For more information on algorithms you can visit TopCoder.
More on **Sorting**: What common sorting functions are there? On what kind of input data are they efficient, when are they not? What does efficiency mean in these cases in terms of runtime and space used? E.g. in exceptional cases insertion-sort or radix-sort are much better than the generic QuickSort / MergeSort / HeapSort answers.

Testing: Candidates with 3+ years of industry experience should have hands on testing experience. 
Less than 3 years we look for testing aptitude.  
Expect questions like: How would you unit test the code you write? 
What interesting inputs or test cases can you think of? 
How would you design end to end, integration, load and performance or security tests for a real world system, for example Gmail?

Data structures: You should study up on as many data structures and algorithms as possible. You should especially know about the most famous classes of NP-complete problems, such as traveling salesman and the knapsack problem. Be able to recognize them when an interviewer asks you in disguise. Find out what NP-complete means. You will also need to know about Trees,  basic tree construction, traversal and manipulation algorithms, hash tables, stacks, arrays, linked lists, priority queues.

Mathematics: Some interviewers ask basic discrete math questions. This is more prevalent at Google than at other companies because counting problems, probability problems and other Discrete Math 101 situations surrounds us. Spend some time before the interview refreshing your memory on (or teaching yourself) the essentials of elementary probability theory and combinatorics. You should be familiar with n-choose-k problems and their ilk – the more the better.

Graphs: To consider a problem as a graph is often a very good abstraction to apply, since well known graph algorithms for distance, search, connectivity, cycle-detection etc. will then yield a solution to the original problem. There are 3 basic ways to represent a graph in memory (objects and pointers, matrix, and adjacency list); familiarize yourself with each representation and its pros/cons. You should know the basic graph traversal algorithms, breadth-first search and depth-first search. Know their computational complexity, their tradeoffs and how to implement them in real code.

Operating systems: You should understand processes, threads, concurrency issues, locks, mutexes, semaphores, monitors and how they all work. Understand deadlock, livelock and how to avoid them. Know what resources a process needs and a thread needs. Understand how context switching works, how it's initiated by the operating system and underlying hardware. Know a little about scheduling. The world is rapidly moving towards multi-core, so know the fundamentals of "modern" concurrency constructs.

System design: Candidates with 5+ years of industry experience are expected to have System Design experience. These questions are used to assess a candidate's ability to combine knowledge, theory, experience and judgement toward solving a real-world engineering problem.  Sample topics include: features SWE-TI’s, interfaces, class hierarchies, distributed systems, designing a system under certain constraints, simplicity, limitations, robustness and tradeoffs. You should also have an understanding of how the Internet actually works and be familiar with the various pieces (routers, domain name servers, load balancers, firewalls, etc.).


### More Readings

Books:

	1. Programming Interview Exposed
	2. Programming Perls
	3. Cracking the Coding Interview

Publications:

	* The Google File System
	* Bigtable: A Distributed Storage System for Structured Data
	* MapReduce: Simplified Data Processing on Large Clusters
	* Google Spanner: Google's Globally-Distributed Database
	* Google Chubby

Others:

* [Student development](https://www.google.com/about/careers/students/guide-to-technical-development.html)
