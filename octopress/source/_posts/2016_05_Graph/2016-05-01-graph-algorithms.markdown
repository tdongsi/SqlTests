---
layout: post
title: "Graph Data Structures & Algorithms"
date: 2016-05-01 02:12:53 -0800
comments: true
categories: 
- Algorithm
- Graph
- Book
- GTG
---

Summary of chapter 14 "Graph Algorithms" in "Data Structures & Algorithms in Python" by GTG.

<!--more-->

### Transitive Closure

Background: If the graph representation is adjacency list or adjacency map, we can answer the question of reachability for any u and v in `O(n+m)` (where `n` is the number of nodes, `m` is the number of edges).
However, if you have to answer **many** reachability queries (e.g., navigation application), it is better to construct a transitive closure for that graph.

A transitive closure `G'` is for a directed graph `G`: any u and v that are reachable, there is an edge (u, v) in `G’`.

In the classic adjacency list or adjacency map representation, transitive closure can be constructed in `O(n(n+m))` by repeating DFS at each vertex.
However, when the graph is dense (m -> n^2), you is better off with Floyd-Warshall algorithm `O(n^3)` using **adjacency matrix** representation.
Floyd-Warshall requires get_edge and insert_edge to be done in `O(1)` time, which necessitates adjacency matrix requirements.

In theory, `O(n^3)` is not better than repeated DFS traversals `O(n(n+m))`. 
However, in practice, Floyd-Warshall is faster and easier to implement because there are fewer low-level operations. 
However, when the graph is sparse, repeated DFS traversal approach is better in time and space complexity.

``` python Floyd-Warshall implementation
def floyd_warshall(g:Graph) -> Graph:
    """ Return a new graph that is the transite closure of g.

    :param g: input graph
    :return: the transitive closure of g.
    """
    closure = deepcopy(g)
    verts = list(closure.vertices())
    n = len(verts)

    for k in range(n):
        for i in range(n):
            # verify that edge(i, k) exists in the partial closure
            if i != k and closure.get_edge(verts[i], verts[k]) is not None:
                for j in range(n):
                    # verify that edge(k, j) exists in the partial closure
                    if i != j != k and closure.get_edge(verts[k], verts[j]) is not None:
                        if closure.get_edge(verts[i], verts[j]) is None:
                            # if (i, j) not yet included, add it to the closure
                            closure.insert_edge(verts[i], verts[j])

    return closure
```

### DAG and topological sorting.

A directed graph may have more than one topological ordering.

Proposition: A directed graph has a topological sorting if and only if it is acyclic.

``` python Topological sorting
def topological_sort(g: Graph) -> list:
    """ Return a list of vertices of DAG in topological order

    :param g: a directed acyclic graph (DAG)
    :return: list of topological sort
    """
    topo = []
    ready = []
    incount = {}

    for u in g.vertices():
        incount[u] = g.degree(u, False)
        if incount[u] == 0:
            # u is free of constraints
            ready.append(u)

    while len(ready) > 0:
        u = ready.pop()
        topo.append(u)

        for e in g.incident_edges(u):
            v = e.opposite(u)
            incount[v] -= 1
            if incount[v] == 0:
                ready.append(v)

    return topo
```

