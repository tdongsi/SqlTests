---
layout: post
title: "Network Flow: Basics"
date: 2015-04-11 18:51:41 -0700
comments: true
categories: 
- Algorithm
- Graph
- Book
- AD
---

Summary of chapter 7 "Graph Algorithms" in "Data Structures & Algorithms in Python" by GTG.

<!--more-->

### Network Flow Basics

Augmenting Path

``` plain Augment pseudocode
```

Ford-Fulkerson method

``` plain Ford-Fulkerson pseudocode

```

If all capacities in the flow network G are integers, then the Ford-Fulkerson method runs in O(mC) time.

### Capacity-Scaling Algorithm

Algorithm

Runtime

### Edmonds-Karp Algorithm

``` python Edmonds-Karp variant of Ford-Fulkerson method
def edmonds_karp(g: Graph, source: Vertex, sink: Vertex):
    """ Edmonds-Karp implementation of Ford-Fulkerson method.
    If you have an original network flow, you should create a deep copy of it AND retrieve the right source/sink vertcies.

    :param g: residual graph
    :param source: source Vertex
    :param sink: sink Vertex
    :return: maximum flow
    """

    def BFS_augment_path(g, s, t):
        """ Find BFS path from s to t in network flow graph.

        :param s: source
        :param t: sink
        :return: list of edges from s to t. Empty if there is no path.
        """
        discovered = BFS_iter(g, s)
        vertices = construct_path(s, t, discovered)
        edges = []

        if vertices:
            for i in range(len(vertices)-1):
                edges.append(g.get_edge(vertices[i], vertices[i+1]))

        return edges

    max_flow = 0
    path = BFS_augment_path(g, source, sink)

    while path:

        path_flow = min([e.element() for e in path])
        max_flow += path_flow

        for e in path:
            u, v = e.endpoints()

            # Update forward residual edge
            cur = e.element()
            if cur - path_flow == 0:
                g.delete_edge(u, v)
            else:
                e.set_element(cur - path_flow)

            # Update backward residual edge
            if g.get_edge(v, u) is None:
                g.insert_edge(v, u, path_flow)
            else:
                reverse_edge = g.get_edge(v, u)
                cur = reverse_edge.element()
                reverse_edge.set_element(cur + path_flow)

        path = BFS_augment_path(g, source, sink)

    return max_flow
```

### Blocking-Flow Algorithm
