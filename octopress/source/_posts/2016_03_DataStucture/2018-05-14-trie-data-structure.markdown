---
layout: post
title: "Data structure: Trie"
date: 2013-05-14 19:10:03 -0700
comments: true
categories: 
- Python
---

TODO: Link to the Python code

``` python Trie data structure
class TrieNode():
    __slots__ = 'children', 'word_end', 'size'

    def __init__(self):
        self.children = {}
        self.word_end = False
        self.size = 0


class Trie():

    def __init__(self):
        self._root = TrieNode()

    def insert(self, word):
        current = self._root

        for c in word:
            current.size += 1
            node = current.children.get(c)
            if node is None:
                node = TrieNode()
                current.children[c] = node

            current = node

        # Mark the last one as end of word
        current.size += 1
        current.word_end = True

    def search(self, word):
        """Check if the given word is present"""
        current = self._root

        for c in word:
            node = current.children.get(c)
            if node is None:
                return False
            current = node

        return current.word_end

    def search_partial(self, prefix):
        """Count the number of contacts starting with prefix."""
        current = self._root

        for c in prefix:
            node = current.children.get(c)
            if node is None:
                return 0
            current = node

        return current.size

    def delete(self, word):
        self._delete(self._root, word, 0)

    def _delete(self, current: TrieNode, word: str, idx: int):
        """Return True if parent node should delete the mapping."""
        if idx == len(word):
            if not current.word_end:
                return False

            current.word_end = False
            return len(current.children) == 0

        c = word[idx]
        node = current.children.get(c)
        if node is None:
            return False

        shouldDelete = self._delete(node, word, idx+1)

        if shouldDelete:
            del current.children[c]
            return len(current.children) == 0

        return False

    def list(self):
        return list(self._list(self._root, ""))

    def _list(self, current: TrieNode, prefix: str):
        if current.word_end:
            # print(prefix)
            yield prefix
        else:
            for k in current.children:
                yield from self._list(current.children[k], prefix + k)
```

