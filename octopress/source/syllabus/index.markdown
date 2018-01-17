---
layout: page
title: "Guide to technical interviews"
date: 2016-08-28 18:42
comments: true
sharing: true
footer: true
---

### Organization

This blog is intentionally private, for personal reference and reflection, and not linked to anywhere.
If you (a stranger) stumble upon this site, feel free to walk away now.

This blog's categories are based on these topics (first order of the list in the next section).
Namely, there will be Java, Python, JavaScript, SQL, Algorithm, Data Structure, Math, Graph, OS, System Design, Database, Network.

When writing a post, specify the month by the number of the topic.
For example, posts that are related to "Algorithm" should be February 2016.
The year will be always 2016.

December is reserved to random topics, for posts that have been removed from the main blog.

### Topics

1. Coding: Java, Python, JavaScript, SQL.
    1. Code basics: see list of basic operations below.
    3. [Overall testing](/blog/2016/01/01/tutorial-test-driven-development/)
    1. OOP
    2. Functional Programming
    4. Logging
    4. Package management
    5. Language-specific: see below.
2. Algorithms
    1. Sorting
        1. Quick sort, Merge sort, Heap sort, Insertion sort, Radix sort
        6. Runtime
    2. Binary search:
        1. See [here](https://web.archive.org/web/20170308042038/https://googleyasheck.com/binary-search/)
    3. Tree
        1. Construction
        2. Traversal: In-Order, Pre-order, Post-order, Level-order (breadth-first) (stack, recursion).
        3. BFS, DFS, Tree Insert, Tree Height.
        3. [Tries](https://www.reddit.com/r/cscareerquestions/comments/6jaj91/hey_folks_after_hearing_a_bunch_of_folks_were/)
    4. Dynamic Programming
    5. Bit Manipulation
    5. Problems
    6. Special algorithms
3. Data structures
    1. Hashtables
        1. Implementation: You should be able to implement one using only arrays in your favorite language, in one interview.
    2. List
        1. Arrays
        2. Linked Lists
        3. Stacks
        4. Queues
    3. Priority Queues
        1. Heap: variants of heap (binary, binomial, Fibonacci).
    4. NP-complete problems
        1. Traveling salesman. Knapsack problem.
4. Mathematics
    1. Probability
5. Graph
    1. Representation
        1. 3 ways
        2. Pros & Cons
    2. BFS, DFS
    7. [Path finding](https://www.reddit.com/r/dataisbeautiful/comments/74i11w/path_finding_animation_in_2d_maze_using_4_common/): Dijkstra, A*, Breadth First and Depth First.
    6. Cycle detection
6. [Operating Systems](/blog/2016/09/07/questions-operating-system-drills/)
    1. [Dinasaur book](/blog/2016/09/06/tutorial-process-synchronization/)
7. [System Design](/blog/2016/09/14/system-design-questions/)
    1. Distributed System. Inspiration: Kubernetes.
    1. Log management: log forwarding. Inspiration: Fluentd.
    1. Service mesh: service-service communication. Inspiration: Linkerd.
8. Database
    1. [Normalization, normal forms](/blog/2016/09/12/database-question-bank/).
    2. [Data Warehouse, dimensional modeling](/blog/2016/09/15/tutorial-dimensional-modelling/).
        1. [Business Analytics](/blog/2016/09/16/tutorial-dashboard-for-business-analytics/).
    3. [Database interview questions](/blog/2016/09/12/database-question-bank/).
9. [Network](/blog/2016/09/13/network-question-bank/)
    1. HTTP
        1. [HTTP Made Really Easy](https://www.jmarshall.com/easy/http/)
10. Soft questions
11. Specialized areas: I used to work with these areas extensively.
    1. [Estimation + Computer vision](/blog/2016/09/04/tutorial-estimation-theory/)
    1. Machine Learning
12. Bash: Cookbooks for various bash commands
12. Random hidden notes: going in year 2015
    1. Sql Test Runner: test automation approaches for SQL-based ETL processes. Category:SqlRunner.

#### Language-specific

When picking a language, make sure you have at least the following operations down pat:

* Initializing and using hash-backed maps (dictionaries) and sets
* Performing queue and stack operations (whether using an array, like with Ruby’s pop/shift, or an explicit standard library implementation like Java’s Stack and Linked List-backed Queue classes)
* Defining classes with constructors and attributes, static and instance methods, and defining a subclass and interface
* Throwing exceptions for (1) invalid input and (2) custom messages
* Iterating through arrays and dictionaries (both key and values)—including iterating while maintaining an index (Ruby’s eachwithindex, Python’s enumerate)
* Casting between strings and integers
* Strings: get character at index, get range, concatenate
* Initializing 2d arrays
* Arithmetic: modulus (remainder), division (if the first operand is an integer, will it round the result to an integer?), rounding (ceiling and floor)
* (Bonus) Switch statements
* (Bonus) Built-in language sorting. Quickly defining a comparator to sort an array.
* (More advanced candidates) The 5 standard bit manipulation operations (L/R shifts, not, or, and, xor)

Have some opinions about it
* What do you like about language X?
* What don’t you like about it?
* How does it compare to (another language you profess to know)? Similarities/differences?

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

* [Technical development](https://www.google.com/about/careers/students/guide-to-technical-development.html)
