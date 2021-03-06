---
layout: post
title: "Book: GTG: Text Processing"
date: 2013-04-23 16:34:23 -0700
comments: true
categories: 
- GTG
- Book
- Algorithm
---

Summary of chapter 14 "Graph Algorithms" in "Data Structures & Algorithms in Python" by GTG.

<!--more-->

### Pattern-Matching Algorithms

For the following algorithms, `m` is the length of the pattern (substring) and `n` is the length of the larger text.

#### Brute-force algorithm

The brute force running time is `O(mn)`.

``` python Brute Force
def find_brute(text:str, subs: str):
    """ Pattern matching with brute force.
    Return the lowest index of text T at which substring P begins (or else -1).

    :param text: text T
    :param subs: substring P
    :return: lowest index of T
    """
    if not text or not subs:
        return -1

    n, m = len(text), len(subs)

    for i in range(n - m + 1):
        k = 0
        while k < m and text[i+k] == subs[k]:
            k += 1
        if k == m:
            return i

    return -1
```

#### Boyer-Moore algorithm

``` python Boyer-Moore algorihtm
def find_boyer_moore(text:str, subs:str):
    """ Pattern matching with Boyer-Moore algorithm.
    Return the lowest index of text T at which substring P begins (or else -1).

    :param text: text T
    :param subs: substring P
    :return: lowest index of T
    """
    if not text or not subs:
        return -1

    n, m = len(text), len(subs)

    last = {}
    for k in range(m):
        last[subs[k]] = k

    # align end of pattern at index m-1 of text
    i = m-1  # index into 'text'
    k = m-1  # index into 'subs'
    while i < n:
        if text[i] == subs[k]:  # a matching character
            if k == 0:
                return i  # pattern begins at index i of text
            else:
                i -= 1  # examine previous characters in both T & P
                k -= 1
        else:
            j = last.get(text[i], -1)
            # Sanity check:
            # 1) text[i] not in last -> i += m
            # 2) text[i] in last -> i += m - (j+1)
            i += m - min(k, j+1)
            k = m -1  # restart at end of pattern

    return -1
```

#### Knuth-Morris-Pratt algorithm.

The KMP running time is `O(m+n)`.

``` Python KMP algorithm
def compute_kmp_fail(pattern: str):
    """ Utility function that computes and returns KMP failure function.

    :param pattern: the pattern
    :return: list as lookup table
    """
    m = len(pattern)
    fail = [0] * m
    j = 1
    k = 0
    while j < m:
        if pattern[j] == pattern[k]:  # k+1 characters match thus far
            fail[j] = k+1
            j += 1
            k += 1
        elif k > 0:
            k = fail[k-1]
        else:  # no match found starting a j
            j += 1
    return fail


def find_kmp(text: str, subs: str):
    """ Pattern matching with Knuth-Morris-Pratt algorithm.
    Return the lowest index of text T at which substring P begins (or else -1).

    :param text: text T
    :param subs: substring P
    :return: lowest index of T
    """
    if not text or not subs:
        return -1

    n, m = len(text), len(subs)

    fail = compute_kmp_fail(subs)

    j = 0
    k = 0
    while j < n:
        if text[j] == subs[k]:
            if k == m-1:
                return j - m + 1
            j += 1
            k += 1
        elif k > 0:
            k = fail[k-1]
        else:
            j += 1

    return -1
```

### DP and Longest Common Subsequence

See [here](TODO).

### Huffman coding method.

SKIP.

### Tries

The idea of tries comes from the fact that text search can be sped up with preprocessing the pattern such as Boyer-More and KMP algorithms.
Such approach is suitable for applications where a series of queries is performed on a fixed text, so that the initial cost of preprocessing is compensated by a speed up in each subsequent query.

A trie is for storing strings in order to support fast pattern matching.
The main application for tries is for information retrieval.
The primary query operations are pattern matching and prefix matching. 

#### Standard trie

One key assumption in trie is that no string in S is a prefix of another string.
This ensures that each string in S is uniquely associated with a leaf of T.
We can satisfy this assumption by adding a special termination character (conventionally `$`) that is not in the alphabet at the end of each string.

Standard operations:

* Seach for a string of length `m`: `O(m)`.
  * Rationale: We visit at most `m+1` nodes in T and we spend `O(1)` at each node if we have secondary hash table/lookup table at each node for search.
  * Only works for exact word matching or prefix matching. Arbitrary substring search does not work.
* Construction of trie: `O(n)`.
  * `n` is the total length of the strings in S.

#### Compressed trie

Compressed trie is also known as Patricia trie for historical reasons.
Each internal node of compressed trie has at least two children.
It enforces this rule by compressing chains of single-child nodes into individual edges.
Nodes in compressed trie are labeled with strings instead of single characters as in standard trie.
The advantange of compressed trie is that the number of nodes of the compressed trie is proportional to number of strings, instead of its total length.

The real space savings come when it is used as auxiliary index structure over a collection of strings already stored in some other primary structure.
Then, we can use numeric indexes in trie instead of storing actual characters.
Searching in a compressed trie is not actually faster than a standard trie.

#### Suffix Trie

More info at [here](https://www.youtube.com/watch?v=N70NPX6xgsA).

A suffix trie is a compressed trie containing all the suffixes of the given text X of size `n` from an alphabet of size `d`.
Note that trivially storing all the suffixes of an X would take `O(n^2)` space.
However, the compact representation of a compressed trie is proportional to number of strings and uses `O(n)` space instead.
It can be constructed with a specialized algorithm in `O(dn)` time (not discussed in book).
The standard trie construction would take `O(d*n^2)` time.

Standard operations:

* Seach for a pattern/prefix of length `m`: `O(dm)`.

Applications:

* Pattern/Prefix searching
* Exact string matching
* Finding the longest repeated substring
* Finding the longest palindrome in a string: Manacher's algorithm.
* Lowest common ancestors
* Finding the longest commont substring

#### Search Engine Indexing

The core information stored by a search engine is a dictionary, called an **inverted index** or **inverted file**, storing the following key-value pairs:

* keys: words (index terms)
* values: occurrence lists: collection of pages containing the corresponding key word.

We can implement the inverted file with a data structure consisting of the following:

* A compressed trie for the set of keys (index terms), where each leaf stores the index of the occurence list.
* An array storing the occurrence lists of the terms.

The compressed trie can be fit into the memory while the much larger array for the occurrence lists can be stored on disk.

A query for a single keyword is similar to a word-matching query. 
Namely, we find the keyword in the trie and we return the associated occurrence list.
When multiple words are given and the desired output are the pages containing all the given keywords, we retrieve all the occurrence lists and find their intersection.
Note that for a real search engine, additional service must be provided such as ranking the pages returned by relevance.


