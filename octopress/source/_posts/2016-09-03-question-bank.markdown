---
layout: post
title: "Algorithm Question bank"
date: 2016-09-03 23:31:29 -0700
comments: true
categories: 
- Questions
- Algorithm
- TODO
---

These questions are gathered from Glassdoor.

<!--more-->

### Questions

LinkedIn

1. Serialize binary tree.
1. Count the number of occurrences of a given element in a sorted array.
1. Modify binary search to find the index of first/last occurrence of x in the array. Two binary searches. 
1. Design a program to take a string and store, each character, into an array. The array should return a count of each character when called. [The interviewer was clearly trying to blur the lines between a simple program and a MapReduce]
1. Senior Test Engineer: A user logs in to a website with proper credentials and the user is taken to a blank page. How would you troubleshoot that?  
1. Senior Test Engineer: Create a stack with the usual push() & pop(), but with an additional function getMiddle() that returns the middle element of the stack in constant time. 
1. Senior Test Engineer: Search a sorted array for the first element larger than k.
1. You have a potentially very-large set of documents, which are potentially very-large, and contain text. For searching these documents, they've been pre-processed into a (very-large) table mapping words to the set of documents that contain each word. E.g. (word) : (documents (referenced by ID) containing that word) Apple: 1, 4, 5, 6, 32 Banana: 5, 6, 7, 9, 32 Cantaloupe: 1, 2, 6 ... Clients will pass in a set ...  
1. Given a nested list of integers, return the sum of all integers in the list weighted by their depth For example, given the list `[[1,1],2,[1,1]]` the function should return 10 (four 1 at depth 2, one 2 at depth 1). Answer: Recursion or Use a stack while iterating through the input string.
1. Create an isNumber(string) function. Handle signed / unsigned, floating point, any number of digits, etc. Probably commas, and currency signs, or whatever. It was open ended and governed by whatever unit tests he wanted you to make it work against.
1. Find out at least one 3 elements array in a given integer array. The 3 elements have to be able to form a triangle.
1. Write a program to replace 0s with 5 in a given number. Eg: 1208 -> 1258, 120096045 -> 125596545  
1. Write a function to find the power of a^b
1. Find the minimum distance between 2 words in a dictionary
1. Evaluate a post-fix expression
1. Given an array of numbers , replace each number with the product of all the numbers divided by the number at that index without using the division operator.
1. Write a function that, given a list of integers (both positive and negative) returns the sum of the contiguous subsequence with maximum sum. Thus, given the sequence (1, 2, -4, 1, 3, -2, 3, -1) it should return 5.
1. Write a function that would find the largest palindrome (phone interview).
1. Write a Binary Search Tree class with isBST() method that will validate if the tree is a BST (onsite). 

Google

1. Given a list of integers and another integer. Write a program that returns the posible combinations of the list which added, match the integer, numbers can repeat itself.
2. Write a function to check if a string is palindrome? Write a function to return the largest Palindrome as possible in a given string. Hint: O(N)
3. Check if Sudoku is valid
4. You have unsorted array. You must design algorithm to create array where every even value is greater than it's odd neighbors. So if a(i) is value at index i (=0,1,2...n), the result must hold that: a1 < a2 > a3 < a4 > a5...
5. Merge K sorted lists. 
6. System design: design a system that given a point on earth will return the highest building in 100m radios. The question was developing. Start by assuming everything in memory. Then assume not everything fits, then use several machines, then consider what happens when one is down. The purpose is to answer as quickly as possible but you have preprocessing time.
7. 0-1 Knapsack problem. NP-complete/NP-hard.
8. Design a load balancer for web servers.

Salesforce

1. Q: Explain Encupsulation
2. Q: Inheritance vs composition
3. Q: What's the difference between tree and HashTable.
4. Also asks me to create classes for file system API.
5. How to ensure locking in Singleton class
6. Do a producer consumer problem
7. Reach 2D Pt x to Pt y in spiral form -> right -> down -> left -> up moving 1 point at a time.
8. Find all words which start from particular letter (case insensitive)
9. The number of paths in a m x n grid to a given cell?
10. Given an ordered large array of integers, find all pairs of numbers that add up to a particular given sum.
11. Given a regular English dictionary, and a word to start with (e.g. "head"), you can only change one letter a time, but every change has to be a word in the dictionary (e.g. head -> heal) What is the quickest way to an end word (e.g. "tail")?
12. How to efficiently merge two sorted binary trees?
13. Why its said a good practice to override() hashcode() of an object when you override equals()? 
14. Given an array of integers, return top K elements from it.
15. Given 2 unsorted integer arrays, get the intersection of the 2.   
16. question about finding out the character that occurs most in a given string.
17. What is the JRE and what does it have to do with .jar files? 
18. Explain / implement a hash table.
19. Given 3 tasks from 3 bosses, how would u handle the priority if you could only finish one of them.
20. Three major difference between C++ and Java.
21. Given a constant input stream of integers design a way to, at any point, return the current median of that stream.
22. Get unique items from an array.
23. given an binary search tree (that is, an ordered tree), how would you find the element of the tree at a particular "index", where index is defined as the position in the tree's ordering.
24. Fastest way to find the middle node in a linked list
25. Eliminate the duplicate elements from a given linked list.
26. Union find algorithm.
27. clockwise print out a M*N matrix. Solution: recursive
28. Explain way to optimize SQL.
    1. http://www.vertabelo.com/blog/technical-articles/5-tips-to-optimize-your-sql-queries

29. Counting the frequency of words in a list.. hashtable with word keys and word count values..
30. Find Pairs with least absolute difference in an given unsorted array.
    1. NOTE: if the numbers are in a given range

31. Explain difference in Heap and Stack memory for JVM.
    1. http://javarevisited.blogspot.com/2013/01/difference-between-stack-and-heap-java.html
    2. https://www.youtube.com/watch?v=450maTzSIvA

32. Image url parser from an html page and its child pages.  
33. Implement Que using 2 stacks. write test cases for it.  
34. Write thread safe singleton class  
35. what are joins in database, some scenario where u used it
36. Given two sorted arrays, find the intersection of the two arrays.
37. Find duplicate from a very large list (trillion) of immutable integers in known range. O(N) and O(1) additional space solution.

Set B
5. JS - what is eval()
6. JS - what is the diffenrence between == and ===
9. How hashmaps work - hashCode and equals operators
10. Principles of OO - encapsulation, inheritance
11. What is synchronized?
12. Big O of data searching on different data structures - array , linked list, hashtable
13. Java difference of a hashtable and hashmap
14. Something that you are proud of - code and other - industry related
15. Describe a development process - engaging client - requirements - etc
16. Favorite java classes
17. What does AJAX stands for?
18. what type of response can you get from and AJAX call - string or XML

Set C:
1) Describe Singleton in a Real Life Example , like if you want to explain concept of singleton pattern to your grandmother, who don't know the computer at all. what would be your approach.
2) How would you design a Chat Session (gtalk) ?
3) write program to calculate power(x,n) in log(n) time
4) Find intersection node of two linked lists.
6) Merge two sorted linked lists into one without extra space.
7) Given an array, which contains integers in the range of 1 to n. one number is missing from it and one number is repeated in array. for ex -- array is {1,2,3,4,4,6,7,8,9} , range given is 1- n(n=9).
determine which number is missing and which is repeated in array.
8) given char array = {a,a,a,a,b,b,b,c,c,d,d,,e,,f,f}
output should be -- {a,4,b,3,c,2,d,2,e,f,2}, i.e. occurrences of every element followed by character, without using other array.



Others

1. In-order traversal? In-order traversal without recursion?

Old notes

1) Implement a FIFO queue from 2 FILO stacks
2) Calculate Fibonacci-like sequence: f(n) = f(n-1) + … + f(n-k) where f(i) = 0 if i < 0 and f(0) = 1. Your algorithm should avoid shifting lots of number.
3) Given 2 sorted arrays, how do I get the median of the combined 2 arrays.
4) Given a sorted array and a number k, find 2 number a, b such that a + b = k. Your algorithm should be O(n). If the array is unsorted, can you still do in O(n)? (This question is asked in Google phone interview and Salesforce onsite interview)
5) Dynamic programming problems: at least one dynamic programming problem will be asked during on-site interview. It's unlikely in a phone interview.
Given a number n, find a set such that

	* Sum of all the elements should result into n.
	* Multiplication of all the elements should be greater than any other similar set (whose elements result n when summed up).

[edit]Concurrency
	1. How to write a thread-safe or reentrant function. What is the difference between a reentrant function.

		* Reentrant and thread-safe is actually two separate concepts.
Object oriented programming
	* What is OOP? What is abstraction, encapsulation, inheritance, polymorphism?
	* 
		* OOP: a programming paradigm that uses objects to design computer applications. Here, objects are special data structures that consist of data fields and methods interacting with those data fields. OOP techniques includes the followings: data abstraction, encapsulation, inheritance, polymorphism, messaging.
		* Data abstraction: means that we interact with data in objects using its abstract interface. For example, we access private data of an object using its public methods. It is a mechanism to restrict the access to some of data structure's component, usually to separate the implementation details of the data structure.
		* Encapsulation: in simple definition, the implementation details of a program is separated from its representation. It means that we separate "what an object can do" and "how the object do it".
		* Inheritance: is the ability of a class to expand as a subclass, so that the code can be reused.
		* Polymorphism: is the ability of a class can be considered as different forms.
		* Message passing: in OOP, a message is the single means to pass control to an object. Alan Kay has argued that message passing is more important than objects in OOP, and that objects themselves are often over-emphasized [2].



### Answers


3) [5]: The basic idea is that if you are given two arrays A and B and know the length of each, you can check whether an element A[i] is the median in constant time. Suppose that the median is A[i]. Since the array is sorted, it is greater than exactly i − 1 values in array A. Then if it is the median, it is also greater than exactly j = ceil(n/2) − (i − 1) elements in B. It requires constant time to check if B[j] <= A[i] <= B[j + 1]. If A[i] is not the median, then depending on whether A[i] is greater or less than B[j] and B[j + 1], you know that A[i] is either greater than or less than the median. Thus you can binary search for A[i] in O(lg n) worst-case time.
4) Time O(n) and O(n) for sorted and unsorted array.

	* If the array is sorted, use two pointers at the two ends of the aray.
	* If the array is not sorted, create a hash table with keys as (k-a[i]). Then look up for each a[i] in the table. Table insertion and table look-up is O(1).
