---
layout: post
title: "Graph Data Structures"
date: 2015-05-02 10:54:59 -0700
comments: true
categories: 
- Algorithm
- Graph
- Book
- GTG
---

Summary of chapter 14 "Graph Algorithms" in "Data Structures & Algorithms in Python" by GTG.

<!--more-->

### Vertices and Edges

TODO

### Graph

TODO

``` python Graph
class Graph():

    def __init__(self, directed=False):
        self._directed = directed
        self._outgoing = {}
        self._incoming = {} if directed else self._outgoing
        
    def is_directed(self):
        return self._directed
        
    def vertex_count(self):
        return len(self._outgoing)
        
    def vertices(self):
        return self._outgoing.keys()
        
    def edge_count(self):
        total = sum([len(e) for e in self._outgoing.values()])
        return total if self.is_directed() else total//2
        
    def edges(self):
        out = set()
        for e in self._outgoing.values():
            out.update(e.values())
        return out
```

### Modifications for Network Flows

TODO