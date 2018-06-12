---
layout: post
title: "Dynamic Programming"
date: 2012-02-11 02:12:32 -0800
comments: true
categories: 
- Algorithm
---

Classic problems using Dynamic Programming.

<!--more-->

### [Longest Common Subsequence](https://en.wikipedia.org/wiki/Longest_common_subsequence_problem)

The longest common subsequence problem is a classic computer science problem, the basis of data comparison programs such as the diff utility, and has applications in bioinformatics. 
It is also widely used by revision control systems such as Git for reconciling multiple changes made to a revision-controlled collection of files.

The solution to this problem can be divided into two parts:

* A DP solution to find the **length** of the longest common subsequence by constructing a 2D matrix.
* Tracing that constructed matrix to find the common subsequence.

``` python
def longest_common_subsequence(str1:str, str2:str) -> str:
    if not str1 or not str2:
        return 0

    m = len(str1)
    n = len(str2)
    mtable = [[0]*(n+1) for _ in range(m+1)]

    for i in range(1, m+1):
        for j in range(1, n+1):
            if str1[i-1] == str2[j-1]:
                mtable[i][j] = 1 + mtable[i-1][j-1]
            else:
                mtable[i][j] = max(mtable[i-1][j], mtable[i][j-1])

    # return mtable[m][n]
    # We have longest_common_subsequence_length at this point

    subsequence = [""] * mtable[m][n]
    idx = mtable[m][n] - 1

    i = m
    j = n
    while i > 0 and j > 0:
        if str1[i-1] == str2[j-1]:
            # if character matches, add to the array
            subsequence[idx] = str2[j - 1]
            i -= 1
            j -= 1
            idx -= 1
        else:
            # else, follow the larger number
            if mtable[i-1][j] > mtable[i][j-1]:
                i -= 1
            else:
                j -= 1

    return "".join(subsequence)
```

Some optimization pointers:

* The matrix in the naive algorithm grows quadratically with the lengths of the sequences.
  In most real-world cases, especially source code diffs and patches, the beginnings and ends of files rarely change.
  In that case, we can do preprocessing to remove the unchanged portions.
* If only the length of the LCS is required, the matrix can be reduced to a `2*min(n,m)` matrix with ease, or to a `min(m,n)+1` vector as the DP approach only needs the current and previous columns of the matrix.

### Subset sum problem

TODO: NP-complete problem. Pseudo-polynomial solution.

``` python Subset sum problem
def subset_sum(values: list, total: int) -> bool:
    """ Given an array of non-negative numbers and a total, is there a way to add up those numbers to the total.
    """

    if not values:
        return False

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
