---
layout: post
title: "Data strucutre: Binary Tree/Binary Search Tree"
date: 2013-05-14 19:10:18 -0700
comments: true
categories: 
---

### Binary Search

See "Sorting".

``` python Binary search (recrusive)
def binary_search(mlist, item):

    def _bs(start, end):
        if start == end:
            # empty
            return -1
        elif start == end-1:
            # singleton
            if mlist[start] == item:
                return start
            else:
                return -1
        else:
            med = (start+end)//2
            if mlist[med] == item:
                return med
            elif mlist[med] < item:
                return _bs(med+1, end)
            else:
                return _bs(start, med)

    if mlist:
        return _bs(0, len(mlist))
    else:
        return -1
```

``` python Binary search (iterative)
# TODO
```

Variations: Binary search to find start and end indices.

``` python Find start index
def search_start(mlist, target):
    if mlist is None:
        return -1
    else:
        lo = 0
        hi = len(mlist)

        while lo < hi:
            mid = (lo + hi) // 2
            if mlist[mid] == target:
                if mid >= 1 and mlist[mid-1] == target:  # add this
                    hi = mid  # add this
                else:
                    return mid
            elif mlist[mid] < target:
                lo = mid + 1
            else:
                hi = mid

        return -1
```

### Binary Search Tree
From HackerRank.

``` python
class BSTreeNode:
    def __init__(self, node_value):
        self.value = node_value
        self.left = self.right = None

def _insert_node_into_binarysearchtree(node, data):
    if node == None:
        node = BSTreeNode(data)
    else:
        if (data <= node.value):
            node.left = _insert_node_into_binarysearchtree(node.left, data);
        else:
            node.right = _insert_node_into_binarysearchtree(node.right, data);
    return node
```