---
layout: post
title: "Graph Data Algorithms"
date: 2015-05-01 02:12:53 -0800
comments: true
categories: 
- Algorithm
- Graph
- Book
- GTG
---

Summary of chapter 14 "Graph Algorithms" in "Data Structures & Algorithms in Python" by GTG.

<!--more-->

### Graph traversals

#### BFS

``` python Book version
def BFS(g:Graph, s:Vertex, discovered):
    """ BFS traversal of the undiscovered portion of Graph g starting at vertex s.

    :param g: give Graph
    :param s: starting Vertex
    :param discovered: Mapping each vertex to the edge used to discover it.
    :return:
    """
    level = [s]

    while len(level) > 0:
        next_level = []
        for v in level:
            for e in g.incident_edges(v):
                u = e.opposite(v)
                if u not in discovered:
                    discovered[u] = e
                    next_level.append(u)

        level = next_level
```

``` python Construct path
def construct_path(u:Vertex, v:Vertex, discovered):
    """ Construct a path from u to v."""
    path = []

    if v in discovered:
        path.append(v)

        walk = v
        while walk is not u:
            e = discovered[walk]
            parent = e.opposite(walk)
            path.append(parent)
            walk = parent

        path.reverse()

    return path
```

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

> **Proposition**: A directed graph has a topological sorting if and only if it is acyclic.

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



### Shortest Paths

We have a few choices for implementing "adaptable priority queue with location-aware entries" (at least `remove_min` required) in Dijkstra's algorithm:

* Unsorted sequence implementation -> `O(n^2 +m)` runtime.
* A heap implementation -> `O( (n+m)log(n) )` runtime.
* Fibonacci heap implementation -> `O(m +nlog(n))` runtime.

The "heap implementation" is "AdaptableHeapPriorityQueue" class from the chapter 9 of the same textbook.
The "Unsorted sequence implementation" is implemented as follows, using the same signature of the above class for later compatibility:

``` python Simple Adaptable Priority Queue implementation
class AdaptableUnsortedPriorityQueue():
    """ Mocking AdaptableHeapPriorityQueue.
    loc => key in internal map
    key, val => value in internal map
    """

    def __init__(self):
        self._map = {}

    def add(self, key, value):
        """Add a key-value pair."""
        self._map[value] = (key, value)
        return value

    def update(self, loc, newkey, newval):
        """Update the key and value for the entry"""
        self._map[loc] = (newkey, newval)

    def is_empty(self):
        return (len(self._map) == 0)

    def remove_min(self):

        min_key = float('inf')
        min_loc = None
        min_return = None

        for loc, val in self._map.items():
            if val[0] < min_key:
                min_key = val[0]
                min_loc = loc
                min_return = val

        del self._map[min_loc]
        return min_return
```

Python implementation of Dijkstra's algorithm is shown below.
Dijkstra's algorithm is analogous to a weighted BFS traversal.
A few assumptions must be true in this implementation:

* Theoretical requirement: All the weights are nonnegative.
* `e.element()` returns the weight of the edge.

``` python Dijkstra's algorithm
def shortest_path_lengths(g:Graph, s:Vertex):
    """ Compute shortest-path distances from src to reachable vertices of g.
    Dijkstra's Algorithm for finding shortest paths.

    :param g: directed or undirected Graph. e.element() must return non-negative weight
    :param s: Starting vertex
    :return: dictionary mapping each reachable vertex to its distance from s.
    """

    d = {}      # d[v] is upper bound from s to v
    cloud = {}  # map reachable v to its d[v] value
    pq = AdaptableUnsortedPriorityQueue()   # vertex v will have key d[v]
    pqlocator = {}      # map from vertex to its pq locator

    for v in g.vertices():
        if v is s:
            d[v] = 0
        else:
            d[v] = float('inf')

        pqlocator[v] = pq.add(d[v], v)

    while not pq.is_empty():
        key, u = pq.remove_min()
        cloud[u] = key

        for e in g.incident_edges(u):
            v = e.opposite(u)
            if v not in cloud:
                wgt = e.element()
                if d[u] + wgt < d[v]:
                    d[v] = d[u] + wgt
                    pq.update(pqlocator[v], d[v], v)

    return cloud
```

Then, based on the returned computed shortest-path distances, we can compute **shortest-path tree**.
We use the same data structure that represents **DFS tree** and **BFS tree**: a map that maps a vertex to its discovery edge (edge connecting to its parent).
Because of using the same data structure, you can reuse the method `construct_path` to compute the path from one vertex to another.

``` python Compute shortest path tree
def shortest_path_tree(g: Graph, s: Vertex, d:dict) -> dict:
    """ Reconstruct shortest-path tree rooted at vertex s, given the distance map d.
    Return tree as a map from vertex v -> discovery edge.

    :param g: Given graph, directed or undirected.
    :param s: starting vertex.
    :param d: distance map, created from Dijkstra's algorithm.
    :return:
    """
    tree = {}

    for v in d:
        if v is not s:
            for e in g.incident_edges(v, False):
                u = e.opposite(v)
                wgt = e.element()
                if d[v] == d[u] + wgt:
                    tree[v] = e

    return tree
```

Example usage:

``` python Example usage
def test_dijkstra_algorithm(self):
    g = ExampleGraphs.airport_graph()

    vertex_map = {v.element() : v for v in g.vertices()}
    starting_vertex = vertex_map["JFK"]

    cloud = shortest_path_lengths(g, starting_vertex)

    for k, v in cloud.items():
        print("%s: %s"% (k,v))

    sp_tree = shortest_path_tree(g, starting_vertex, cloud)
    path = construct_path(vertex_map["JFK"], vertex_map["LAX"], sp_tree)

    print("Shortest path from JFK to LAX")
    for ap in path:
        print(str(ap))
```

``` plain Example output
[Vertex: JFK]: 0
[Vertex: BOS]: 187
[Vertex: ORD]: 740
[Vertex: MIA]: 1090
[Vertex: DFW]: 1542
[Vertex: SFO]: 2586
[Vertex: LAX]: 2777

Shortest path from JFK to LAX
[Vertex: JFK]
[Vertex: ORD]
[Vertex: DFW]
[Vertex: LAX]
```