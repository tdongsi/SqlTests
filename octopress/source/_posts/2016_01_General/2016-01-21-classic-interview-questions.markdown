---
layout: post
title: "Classic interview questions"
date: 2011-01-21 16:18:42 -0700
comments: true
categories: 
- Java
- Questions
---

There are reasons that these interview questions are just popular.

<!--more-->

### Merge intervals

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

### How to implement a hash map

https://www.geeksforgeeks.org/internal-working-of-hashmap-java/

ArrayList of LinkedList of Entry (Key, Value).
Convert hashcode of Key to integer index (H % bucket).
Use index to move in array.
Use Key to find entry in LinkedList.

### Reverse linked list. Find cycle in linked list

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

### LRU Cache 

The LRU cache can be easily implemented in Java using LinkedHashMap.
There is a `protected` method that allow you to override to specify the cache size. 
TODO: that method.

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

### Singleton design pattern

This question seems to be common because it shows that if a candidate knows “design pattern”, best practices, concurrency (`synchronized`, `volatile` keywords), and `enum` (newer, less common Java features).
It also involves lazy initialization and performance, leaving opportunities to drill further.

#### Standard Singleton

According to "Effective Java" book, the best way to implement Singleton is to use single-item `enum`.

``` java Singeton with example attribute, constructor, and method.
enum OnlyOneOfMe {
    SINGLETON("King");

    private String title;
    
    OnlyOneOfMe(String title) {
        this.title = title;
    }

    public String sayHello() {
        return "Hello";
    }
}
```

Interviewers while appreciating that you know `enum` and best practices, they won't let you go easily.
They will ask how it is implemented under the hood or using the older version.
Then, you should provide the standard idiom with lazy initialization and private constructor.

``` java Singleton with private constructor
public class OnlyOneOfMe {
    private static OnlyOneOfMe singleton = null;

    private OnlyOneOfMe() {
        // init code
    }

    public static OnlyOneOfMe getSingleton() {
        if (singleton == null) {
            singleton = new OnlyOneOfMe();
        }
        return singleton;
    }
}
```

The common follow-up question is to ask whether if it is thread-safe.
You should be able to point out that since there are write operations on `singleton` variable, it is not thread-safe if many threads call `getSingleton` method.
To make it thread-safe (the next question), the simple fix is to add `synchronized` keyword to the function.

``` java
public synchronized static OnlyOneOfMe getSingleton() {
    ...
}
```

If you come this far, the next question is whether it is fast.
You should be able to point out that since there are locking at "getSingleton" method, it will be a bottleneck when many threads want to get the singleton instance.
The next subsection will address the best ways to do lazy initialization for performance.
However, it is shown here for completeness.

``` java Lazy initialization for performance
public class OnlyOneOfMe {
    private static class SingletonHolder {
        static final OnlyOneOfMe singleton = new OnlyOneOfMe();
    }

    private OnlyOneOfMe() {
        // init code
    }

    public static OnlyOneOfMe getSingleton() {
        return SingletonHolder.singleton;
    }
}
```

#### Lazy initialization and Performance

According to [Bloch ("Effective Java" author)](http://www.oracle.com/technetwork/articles/javase/bloch-effective-08-qa-140880.html), the single most important piece of advice is "Don't do it unless you need to." The great majority of your initialization code should look like this:

``` java
// Normal initialization, not lazy!
private final FieldType field = computeFieldValue();
```

If you need lazy initialization for correctness -- but not for performance -- just use a `synchronized` accessor. It's simple and clearly correct.

If you need better performance, your best choice depends on whether you're initializing a static field or an instance field. If it's a static field, use the lazy initialization holder class idiom:

``` java Singleton static field.
// Lazy initialization holder class idiom for static fields
private static class FieldHolder {
     static final FieldType field = computeFieldValue();
}
static FieldType getField() { return FieldHolder.field; }
```
 
This idiom is almost magical. There's synchronization going on, but it's invisible. 
The Java Runtime Environment does it for you, behind the scenes. 
And many VMs actually patch the code to eliminate the synchronization once it's no longer necessary, so this idiom is extremely fast.

If you need high-performance lazy initializing of an instance field, use the double-check idiom with a volatile field. 
This idiom wasn't guaranteed to work until release 5.0, when the platform got a new memory model. 
The idiom is very fast but also complicated and delicate, so don't be tempted to modify it in any way. 
Just copy and paste -- normally not a good idea, but appropriate here:

``` java Double-check idiom for Singleton instance fields.
// Double-check idiom for lazy initialization of instance fields.
private volatile FieldType field;
FieldType getField() {
    FieldType result = field;
    if (result == null) { // First check (no locking)
        synchronized(this) {
            result = field;
            if (result == null) // Second check (with locking)
                field = result = computeFieldValue();
        }
    }
    return result;
}
```

#### References

* [Double-checked locking](https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java)
* [Analysis of idiom before Java 1.5](http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html)
* [Bloch's interview](http://www.oracle.com/technetwork/articles/javase/bloch-effective-08-qa-140880.html)