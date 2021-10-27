---
layout: post
title: "Classic interview questions"
date: 2011-02-01 16:18:42 -0700
comments: true
categories: 
- Python
- Questions
---

There are reasons that these interview questions are just popular.
The goal is not to fail at the same question TWICE. 

<!--more-->

### Lowest Common Ancestor (LCA) (LinkedIn 2021, DoorDash 2021)

Read the [Wikipedia entry](https://en.wikipedia.org/wiki/Lowest_common_ancestor) for full description and its usage.
For example, the LCA is involved in the definition of distance between two nodes in a tree.

There are a few variants of LCA problem that are covered in this section:

* Binary Search Tree. Reference: [Leetcode 235](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
* Binary Tree. Reference: [Leetcode 236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
* Binary Tree with parent pointer. 

#### Binary Search Tree (BST)

By using [BST property](https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/), finding LCA can be done easily by performing a binary search ([Leetcode example](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/submissions/)):

* if the current node’s value is greater than both n1 and n2 then our LCA lies in the left side of the node.
* if it’s is smaller than both n1 and n2, then LCA lies on the right side
* otherwise, the root is LCA (assuming that both n1 and n2 are present in BST)

``` python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
    cur = root
    
    while cur:
        if cur.val > p.val and cur.val > q.val:
            cur = cur.left
        elif cur.val < p.val and cur.val < q.val:
            cur = cur.right
        else:
            return cur
        
    return cur
```

#### Binary Tree

The strategy is to find the paths from root to two nodes `p` and `q` and, then, from the two paths, find the last common node.
The [naive solution](https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/) requires three traversal of the tree and extra storage space to keep the paths.
The recursive solution perform a single traversal in one path, as follows:

``` python
def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
    
    # Base case
    if root is None:
        return None
    
    # Base case 2: 
    # If either n1 or n2 matches with root's key, report presence by returning root
    # If n1 & n2 on the same side, it is also the LCA
    if root.val == p.val or root.val == q.val:
        return root
    
    left_lca = lowestCommonAncestor(root.left, p, q)
    right_lca = lowestCommonAncestor(root.right, p, q)
    
    # If both are not None, then n1 and n2 on the opposite side
    # root is the LCA
    if left_lca and right_lca:
        return root
    
    # Otherwise, not-None result is the recursive solution
    if not right_lca:
        return left_lca
    else:
        return right_lca
```

For iterative solution, converting the above recursive solution into one using stack may not be easy or clean. 
Instead, one iterative approach is to perform DFS to construct a mapping of (node -> its parent) and, then, solve the much easier variant of [LCA with parent pointer](https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-tree-set-2-using-parent-pointer/).

``` python
def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        
    if root is None:
        return None
    
    to_visit = [root]
    discovered = {root: None}
    
    # Perform DFS to build a mapping of node -> its parent
    while to_visit:
        cur = to_visit.pop()
        
        children = []
        if cur.left:
            children.append(cur.left)
        if cur.right:
            children.append(cur.right)
        
        for e in children:
            if e not in discovered:
                to_visit.append(e)
                discovered[e] = cur
                
    # Now, we have a mapping of node -> its parent
    if p in discovered and q in discovered:
        # set of p's ancestors
        p_anc = set()
        
        # Use the mapping to find p's ancestors
        cur = p
        while cur:
            p_anc.add(cur)
            cur = discovered[cur]
            
        # Use the set p_anc to detect the first occurence
        # of q's ancestor in p's ancestors
        cur = q
        while cur:
            if cur in p_anc:
                return cur                
            cur = discovered[cur]
            
    # Should not reach here        
    return None
```

### Maximum Subarray (LinkedIn 2021, Facebook 2018)

The problem is based on [Wikipedia](https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm), with comments for more clarity.

``` python 
def maximum_subarray(nums: list) -> list:
    """Maximum subarray without using helper function buy_stock."""
    best_sum = 0  # NOTE: not infinity since you have a choice of empty sub-array.
    best_start = best_end = 0
    cur_sum, cur_start = 0, 0

    for cur_end, e in enumerate(nums):
        if cur_sum <= 0:
            # Start a new sequence
            cur_start = cur_end
            cur_sum = e
        else:
            # Extend the current sequence
            cur_sum += e

        if cur_sum > best_sum:
            best_sum = cur_sum
            best_start = cur_start
            best_end = cur_end + 1

    return nums[best_start:best_end]
```

### Largest rectangle under histogram (AirBnB 2018, TIBCO 2014)

Reference: [Leetcode 84](https://leetcode.com/problems/largest-rectangle-in-histogram/).
Related: [Leetcode 85](https://leetcode.com/problems/maximal-rectangle/).

The stack-based solution is `O(n)`. 
There is another solution which is divide-and-conquer and more intuitive that runs in `O(n logn)`.

The best explanation of the intuition is in [this Youtube video](https://www.youtube.com/watch?v=vcv3REtIvEo):

* To compute area, you need height (already given) and left/right bounds of the rectangles.
* It's possible to traverse from left to right and compute the left bound.
* Similarly, it's also possible to traverse from right to left and compute the right bound.
* The final traversal is to compute rectangle areas and the max area. This traversal can be done at the same time with the second traversal.
* The stack is used to keep track of minimum previous height/index to determine the left/right bound.

``` python Largest rectangle
def largest_rect_histogram_stack(heights: list) -> int:
    """ Find the largest rectangular area possible in a given histogram
    where the largest rectangle can be made of a number of contiguous bars.

    Based on the intuition in this video https://www.youtube.com/watch?v=vcv3REtIvEo

    :param mlist: list of bar heights, each bar width = 1.
    :return: area of the largest rectangle.
    """

    stack = []

    # Find left bound
    left_bound = []
    for idx, h in enumerate(heights):
        save_idx = idx
        while stack:
            # peek top of the stack
            peek_idx, peek_height = stack[-1]
            if h <= peek_height:  # NOTE: "less AND equal"
                stack.pop()
                save_idx = peek_idx
            else:
                break

        stack.append((save_idx, h))
        left_bound.append(save_idx)

    # Find right bound
    stack.clear()  # clear the stack for right bound
    right_bound = [0] * len(heights)  # Need pre-populated list
    for idx, h in reversed(list(enumerate(heights))):
        save_idx = idx
        while stack:
            peek_idx, peek_height = stack[-1]
            if h <= peek_height:  # NOTE: "less AND equal"
                stack.pop()
                save_idx = peek_idx
            else:
                break

        stack.append((save_idx, h))
        right_bound[idx] = save_idx

    # Find max area
    max_area = 0
    for h, l, r in zip(heights, left_bound, right_bound):
        area = h * (r - l + 1)
        max_area = max(area, max_area)

    return max_area
```

### Merge intervals (Facebook 2016)

Given a collection of intervals (start, end), merge all overlapping intervals.

Example: `[[1, 4], [4, 5], [3, 4]] -> [[1, 5]]`

``` python Merge intervals
def merge_intervals(mlol):
    """ Given a collection of intervals, merge all overlapping intervals.

    :param mlol: List of lists
    :return: overlapping intervals.
    """
    mlol.sort(key=lambda x: x[0])

    output = []
    for interval in mlol:
        # if the output list is empty or if the current interval does not overlap with the previous,
        # simply append it.
        if not output or output[-1][1] < interval[0]:
            output.append(interval)
        else:
            # otherwise, there is overlap, so we combine the current and previous intervals.
            output[-1][1] = max(output[-1][1], interval[1])

    return output
```

### How to implement a hash map (Apple 2018, Salesforce 2016)

https://www.geeksforgeeks.org/internal-working-of-hashmap-java/

Main ideas:

* ArrayList of LinkedList of Entry (Key, Value).
* Convert hashcode of Key to integer index (H % bucket).
* Use index to move in array.
* Use Key to find entry in LinkedList.

### Reverse linked list. Find cycle in linked list (Apple 2014)

The first problem "reversing a linked list" used to be the "foobar" challenge, although slightly harder.
They just want to check if you know the most basic stuffs.
Now, they are usually used as a warm-up question.
However, there is a reason for "foobar" challenges since reversing linked list can be tricky with corner cases even for experienced but ill-prepared candidates.
In addition, if you are interviewing for senior positions, they expect you to solve this question quickly (as if you do interview every single day).
So, you usually have no choice but make sure that you can do this problem quickly.

``` python Reverse linked list
def reverse_list(head:Node) -> Node:
    """ Write a function that reverses a linked list
    :param head: The head of the linked list.
    :return: The new head of the reversed list.
    """
    prev = None
    cur = head

    while cur is not None:
        temp = cur
        cur = cur.get_next()
        temp.set_next(prev)
        prev = temp

    return prev
```

Check if there is a cycle in a linked list. 
TODO: Floyd algorithm.

### LRU Cache (Box 2018)

The LRU cache can be easily implemented in Java using LinkedHashMap.
There is [a `protected` method "removeEldestEntry"](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-) that allow you to override to keep the cache size constant. 

In Python, the natural equivalent to Java's LinkedHashMap is OrderedDict.
In fact, one implementation of LRU cache can be as follows:

``` python LRU cache
class LRUCache:
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = collections.OrderedDict()

    def get(self, key):
        try:
            value = self.cache.pop(key)
            self.cache[key] = value
            return value
        except KeyError:
            return None

    def set(self, key, value):
        try:
            self.cache.pop(key)
        except KeyError:
            if len(self.cache) >= self.capacity:
                self.cache.popitem(last=False)
        self.cache[key] = value
```

### Singleton design pattern (Salesforce 2016)

Check [Blog Archive](https://tdongsi.github.io/SqlTests/blog/archives/) for the standalone post.
