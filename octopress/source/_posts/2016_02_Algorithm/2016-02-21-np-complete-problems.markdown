---
layout: post
title: "NP-complete problems"
date: 2016-02-21 23:26:20 -0800
comments: true
categories: 
- Algorithm
---

### Basic definitions

* P
* NP
* NP-hard
* NP-complete: intersection between NP-hard and NP.

### Common NP-complete problems

The most classic ones that you should know are [these NP-complete problems](https://en.wikipedia.org/wiki/Karp%27s_21_NP-complete_problems):

-   **[Satisfiability]**: the boolean satisfiability problem for
    formulas in [conjunctive normal form] (often referred to as SAT)
    -   **[0--1 integer programming]** (A variation in which only the
        restrictions must be satisfied, with no optimization)
    -   **[Clique]** (see also [independent set problem])
        -   **[Set packing]**
        -   **[Vertex cover]**
            -   **[Set covering]**
            -   **[Feedback node set]**
            -   **[Feedback arc set]**
            -   **[Directed Hamilton circuit]** (Karp\'s name, now
                usually called **Directed Hamiltonian cycle**)
                -   **[Undirected Hamilton circuit][Directed Hamilton
                    circuit]** (Karp\'s name, now usually called
                    **Undirected Hamiltonian cycle**)
    -   **[Satisfiability with at most 3 literals per clause]**
        (equivalent to 3-SAT)
        -   **[Chromatic number]** (also called the [Graph Coloring
            Problem][Chromatic number])
            -   **[Clique cover]**
            -   **[Exact cover]**
                -   **[Hitting set]**
                -   **[Steiner tree]**
                -   **[3-dimensional matching]**
                -   **[Knapsack]** (Karp\'s definition of Knapsack is
                    closer to [Subset sum])
                    -   **[Job sequencing]**
                    -   **[Partition]**
                        -   **[Max cut]**

Their relations are discussed in the Karp's paper.

#### Problems that I know

* Satisfiability
  * 0-1 integer programming
  * Clique
    * Vertex cover
      * Set cover
      * Directed Hamilton cycle
        * Undirected Hamilton cycle
  * 3-SAT
    * Chromatic number / Graph Coloring Problem
    * Exact cover
      * Knapsack / Subset sum

### Other related concepts

Pseudo-polynomial solution: the complexity depends on the *value* of the input, instead of the *length* of the input.

Difference between *value* and *length* of the input: an integer N has its *length* at `log(N)`, no matter what base it is using.
Therefore, an algorithm that is polynomial to the *value* of the input is actually exponential to the size of the input.
