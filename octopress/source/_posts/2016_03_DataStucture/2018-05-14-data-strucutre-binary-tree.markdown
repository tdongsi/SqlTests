---
layout: post
title: "Data strucutre: Binary Tree"
date: 2013-05-14 19:10:18 -0700
comments: true
categories: 
---

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