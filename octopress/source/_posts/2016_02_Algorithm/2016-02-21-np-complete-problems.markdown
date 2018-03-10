---
layout: post
title: "NP-complete problems"
date: 2016-02-21 23:26:20 -0800
comments: true
categories: 
- Algorithm
---

Summary of what you need to know about NP-complete problems.

<!--more-->

### Basic definitions

* P
* NP
* NP-hard
* NP-complete: intersection between NP-hard and NP.

{% img /images/algorithms/NP.png 480 300 %}

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
            -   **[Directed Hamilton cycle]**
                -   **[Undirected Hamilton cycle]**
    -   **[Satisfiability with at most 3 literals per clause]**
        (equivalent to 3-SAT)
        -   **[Chromatic number]** (also called the [Graph Coloring
            Problem])
            -   **[Clique cover]**
            -   **[Exact cover]**
                -   **[Hitting set]**
                -   **[Steiner tree]**
                -   **[3-dimensional matching]**
                -   **[Knapsack]** (Karp's definition of Knapsack is
                    closer to [Subset sum])
                    -   **[Job sequencing]**
                    -   **[Partition]**
                        -   **[Max cut]**

Their relations are discussed in the Karp's paper.

### Other related concepts

Pseudo-polynomial solution: the complexity depends on the *value* of the input, instead of the *length* of the input.

Difference between *value* and *length* of the input: an integer N has its *length* at `O(log(N))`, no matter what base it is using.
Therefore, an algorithm that is polynomial to the *value* of the input is actually exponential to the size of the input.

### Problems that I know

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

#### Subset sum

The [Subset Sum problem](https://en.wikipedia.org/wiki/Subset_sum_problem) is this: given a set (or multiset) of integers, is there a non-empty subset whose sum is zero (or some value)?
The problem is NP-complete, meaning roughly that while it is easy to confirm whether a proposed solution is valid, it may inherently be prohibitively difficult to determine in the first place whether any solution exists.

The Dynamic Programming solution below runs in pseudo-polynomial time.
As mentioned above, it means that the complexity is actually exponential.

``` python
def subset_sum(values: list, total: int) -> bool:
    """ Given an array of non-negative numbers and a total, is there a way to add up those numbers to the total.

    :param values: array of non-negative numbers
    :param total: target total
    :return: True if possible
    """

    n = len(values)
    check = [[False] * (total+1) for _ in range(n+1)]

    # if sum == 0, it is True no matter how many in the set
    for i in range(n+1):
        check[i][0] = True

    for i in range(1, n+1):
        for j in range(1, total+1):
            if j < values[i-1]:
                check[i][j] = check[i-1][j]
            else:
                check[i][j] = check[i-1][j] or check[i-1][j - values[i-1]]

    return check[n][total]
```