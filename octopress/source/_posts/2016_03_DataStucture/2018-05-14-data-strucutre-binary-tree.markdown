---
layout: post
title: "Data strucutre: Binary Tree/Binary Search Tree"
date: 2013-05-14 19:10:18 -0700
comments: true
categories: 
---

### Binary Search

See "Sorting". 
The iterative version is recommended over the recursive version since it is likely expected by interviewers as well as shorter to write.

``` python Binary search (iterative)
def binary_search_iterative(mlist, target):
    if mlist is None:
        return -1

    lo = 0
    hi = len(mlist)
    while lo < hi:
        mid = (lo+hi) // 2
        if mlist[mid] == target:
            return mid
        elif mlist[mid] < target:
            lo = mid+1
        else:
            hi = mid

    return -1
```

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

```python Insert node (iterative)
def insert_node(root: BstNode, val: int):
    if root is None:
        return BstNode(val)
    
    prev = None
    cur = root
    while cur is not None:
        if cur.val == val:
            raise ValueError()
        elif cur.val < val:
            prev = cur
            cur = cur.right
        else:
            prev = cur
            cur = cur.left
            
    cur = BstNode(val)
    if val < prev.val:
        prev.left = cur
    else:
        prev.right = cur
        
    return root
```

``` python Delete node
def delete_node(root:BinaryTreeNode, target) -> BinaryTreeNode:
    """Delete a given value from a BST rooted at given node."""
    if root is None:
        return
    elif root.element() > target:
        new_left = delete_node(root.left(), target)
        root.set_left(new_left)
        return root
    elif root.element() < target:
        new_right = delete_node(root.right(), target)
        root.set_right(new_right)
        return root
    else:
        # root.element() == target
        if root.left() is None and root.right() is None:
            # del root or let garbage collector delete the node
            return None
        elif root.left() is None:
            return root.right()
        elif root.right() is None:
            return root.left()
        else:
            right_min = find_min(root.right())
            root.set_value(right_min)
            new_right = delete_node(root.right(), right_min)
            root.set_right(new_right)
            return root
```


``` python Find successor
def find_sucessor(root: BinaryTreeNode, target) -> int:
    """Get the successor of a value in a BST rooted by given node. Returns int."""
    if root is None:
        return None

    prev = None
    curr = root

    while curr is not None:
        if curr.element() == target:
            if curr.right() is not None:
                return find_min(curr.right())
            else:
                if prev is not None and prev.element() > target:
                    return prev.element()
                else:
                    return None
        elif curr.element() < target:
            # prev = curr  # TRICKY: enabling this is a bug
            curr = curr.right()
        else:
            prev = curr
            curr = curr.left()

    return None
```