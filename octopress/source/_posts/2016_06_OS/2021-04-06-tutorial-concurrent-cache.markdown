---
layout: post
title: "Tutorial: Concurrent Cache"
date: 2016-04-06 21:08:39 -0700
comments: true
categories: 
- Concurrency
- Golang
---

One common interview question is "How to design/implement a Concurrent Cache (or Concurrent HashMap)?".
After reading "Go Programming Language" book, this problem can be approached in a methodical way, especially in Go. 

<!--more-->

### Summary

* Naive answer: putting a HashMap in a Critical Section, guarded by a Mutex. It will be blocking for anyone who tries to access it.
* Better: Separate locking/unlocking for first entry and repeated entry.
* Expected: Duplicate supression.

### Naive design

```go
// A Memo caches the results of calling a Func.
type Memo struct {
	f Func
	mu sync.Mutex // guards cache
	cache map[string]result
}

// Func is the type of the function to memoize.
type Func func(key string) (interface{}, error)

type result struct {
	value interface{}
	err error
}

func New(f Func) *Memo {
	return &Memo {
		f: f,
		cache: make(map[string]result),
	}
}

func (memo *Memo) Get(key string) (interface{}, error) {
	memo.mu.Lock()
	res, ok := memo.cache[key]
	if !ok {
		res.value, res.err = memo.f(key)
		memo.cache[key] = res
	}
	memo.mu.Unlock()
	return res.value, res.err
}
```

### Better: Separate locking/unlocking for repeated entries

```go
// A Memo caches the results of calling a Func.
type Memo struct {
	f Func
	mu sync.Mutex // guards cache
	cache map[string]result
}

// Func is the type of the function to memoize.
type Func func(key string) (interface{}, error)

type result struct {
	value interface{}
	err error
}

func New(f Func) *Memo {
	return &Memo {
		f: f,
		cache: make(map[string]result),
	}
}

func (memo *Memo) Get(key string) (interface{}, error) {
	memo.mu.Lock()
	res, ok := memo.cache[key]
	memo.mu.Unlock()

	if !ok {
		res.value, res.err = memo.f(key)

		memo.mu.Lock()
		memo.cache[key] = res
		memo.mu.Unlock()
	}

	return res.value, res.err
}
```

### Expected: Duplicate-suppressing

```go
// A Memo caches the results of calling a Func.
type Memo struct {
	f Func
	mu sync.Mutex // guards cache
	cache map[string]*entry
}

// Func is the type of the function to memoize.
type Func func(key string) (interface{}, error)

type entry struct {
	res result
	ready chan struct{}  // closed when res is ready
}

type result struct {
	value interface{}
	err error
}

func New(f Func) *Memo {
	return &Memo {
		f: f,
		cache: make(map[string]*entry),
	}
}

func (memo *Memo) Get(key string) (interface{}, error) {
	memo.mu.Lock()
	e := memo.cache[key]
	if e == nil {
		// first request
		e = &entry{ready: make(chan struct{})}
		memo.cache[key] = e
		memo.mu.Unlock()

		e.res.value, e.res.err = memo.f(key)

		close(e.ready) // broadcast ready condition
	} else {
		// repeat request
		memo.mu.Unlock()

		<- e.ready // wait for ready condition
	}

	return e.res.value, e.res.err
}
```
