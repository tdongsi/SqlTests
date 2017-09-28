---
layout: post
title: "Tutorial: Process synchronization"
date: 2016-06-02 00:35:03 -0700
comments: true
categories: 
- Tutorial
- OS
- Java
- TODO
---

Summary of chapter 5 of "Operating System concepts" (Dinosaur book).
Topics in this chapter are the most intensive and frequently asked during interviews. 

<!--more-->

This chapter discuss how to prevent concurrent access to shared data that may result in data inconsistency.

### 5.1 & 5.2: Critical section

Consider the producer–consumer problem, which is representative of
operating systems. Specifically, in Section 3.4.1, we described how a bounded
buffer could be used to enable processes to share memory.

``` cpp Producer process
while (true ) {

  /* produce an item in next produced */
  while (counter == BUFFER SIZE )
    ; /* do nothing */

  buffer[in] = next produced;
  in = (in + 1) % BUFFER SIZE ;
  counter++;
}
```

``` cpp Consumer process
while (true ) {
  while (counter == 0)
    ; /* do nothing */

  next consumed = buffer[out];
  out = (out + 1) % BUFFER SIZE ;
  counter--;
  /* consume the item in next consumed */
}
```

The above code works incorrectly in multi-thread or process situation, due to parallel modifications to "counter".
When several processes access and manipulate the same data concurrently and the
outcome of the execution depends on the particular order in which the access
takes place, is called a race condition.

Critical section: No two processes are executing in their critical sections at the same time.
Solution to a critical section problem requires:
Mutual exclusion: only one process in CS at a time.
Progress: Selection process should not be postponed indefinitely.
Bounded waiting: once a process request to enter, waiting time is bounded.

Nonpreemptive kernel does not allow a process running in kernel mode to be
preempted; a kernel-mode process will run until it exits kernel mode, blocks,
or voluntarily yields control of the CPU. A nonpreemptive kernel is essentially free from race conditions.
A preemptive kernel may be more responsive, since there is less risk that a
kernel-mode process will run for an arbitrarily long period.


### 5.3: Peterson's algorithm

``` cpp Peterson's algorithm
flag[i] = true
turn = j

while ( flag[j] && turn == j ) {
}

// start of CS

// end of CS
flag[i] = false	flag[j] = true
turn = i

while( flag[i] && turn == i ) {
}

// start of CS

// end of CS
flag[j] = false
```

NOTE: [Peterson’s algorithm](http://en.wikipedia.org/wiki/Peterson's_algorithm) is restricted to two processes.
Filter algorithm: Peterson's algorithm for N processes
The filter algorithm generalizes Peterson's algorithm for N processes. It uses N different levels - each represents another 'waiting room', before the critical section. Each level will allow at least one process to advance, while keeping one process in waiting.


http://cs.stackexchange.com/questions/12621/understanding-peterson-s-and-dekker-s-algorithms 
Both processes indicates if the other want to enter CS, it can proceed. If both processes enter at the same time, turn will be set to i and j at the same time, and only one will last.
Proof of bounded waiting: Pi will enter the CS after at most one entry by Pj.

``` plain Analogies of Peterson's algorithm
Peterson's: "I want to enter."                 flag[0]=true;
            "You can enter next."              turn=1;
            "If you want to enter and          while(flag[1]==true&&turn==1){
            it's your turn I'll wait."         }
            Else: Enter CS!                    // CS
            "I don't want to enter any more."  flag[0]=false;

Dekker's:   "I want to enter."                 flag[0]=true;
            "If you want to enter              while(flag[1]==true){
             and if it's your turn               if(turn!=0){
             I don't want to enter any more."      flag[0]=false;
            "If it's your turn                     while(turn!=0){
             I'll wait."                           }
            "I want to enter."                     flag[0]=true;
                                                 }
                                               }
            Enter CS!                          // CS
            "You can enter next."              turn=1;
            "I don't want to enter any more."  flag[0]=false;
```

### 5.4: Sync using hardware

Protect critical section by locking.

Many modern computer systems therefore provide special hardware
instructions that allow us either to test and modify the content of a word or
to swap the contents of two words atomically—that is, as one uninterruptible unit.

Atomic test_and_set() and compare_and_swap() for locking:

boolean test_and_set(boolean *target) {
boolean rv = *target;
*target = true;
return rv;
}

int compare_and_swap(int *value, int expected, int new value) {
int temp = *value;
if (*value == expected)
    *value = new value;
return temp;
}

Simple Mutex with atomic test_and_set()
Figure 5.5

Bounded-Waiting mutex with atomic test_and_set(): data structure and algorithm
Figure 5.7



### 5.5: Mutex locks

We use the mutex to lock to protect critical regions and thus prevent race conditions.

Calls to either acquire() or release() must be performed atomically.

acquire() {
while (!available)
; /* busy wait */
available = false;
}

release() {
available = true;
}
Usage:

acquire()

// start of CS

// end of CS

release()



The main disadvantage of the implementation given here is that it requires busy waiting. This type of mutex lock is also called a spinlock.
Spinlocks do have an advantage, however, in that no context switch is required.

### 5.6: Semaphores

A semaphore S is an integer variable that, apart from initialization, is
accessed only through two standard atomic operations: wait() and signal().

wait(S) {
while (S <= 0 )
; // busy wait
S--;
}

signal(S) {
S++;
}

The value of a counting semaphore can range over an unrestricted domain. Versus binary semaphore, which is similar to mutex.
Counting semaphores can be used to control access to a given resources of a finite number of instances.

We can also use semaphores to solve various synchronization problems.
For example,consider two concurrently running processes: P1 with a statement
S1 and P2 with a statement S2 . Suppose we require that S2 be executed only
after S1 has completed. We can implement this scheme readily by letting P1
and P2 share a common semaphore synch, initialized to 0. In process P1 , we
insert the statements
S1;
signal(synch);

In process P2 , we insert the statements
wait(synch);
S 2 ;

Because synch is initialized to 0, P2 will execute S2 only after P1 has invoked
signal(synch) , which is after statement S1 has been executed.

Deadlock:
P 0 P 1
wait(S); wait(Q);
wait(Q); wait(S);
. .
. .
. .
signal(S); signal(Q);
signal(Q); signal(S);

Priority inversion:
The problem of priority inversion is when three processes of different priorities L < M < H. H is waiting for L to finish with a certain resource. M process becomes runnable and preempts L. Indirectly, process M with lower priority affects how long process H must wait for resource.
It occurs when the system has more than two priorities. However, it is almost always the case.
Solution: priority-inheritance protocol: all processes that use a resource, waited by a higher priority process, will inherit the highest priority until they are done with the resource.

#### Semaphore implementation

The naive definition of wait() and signal() above presents the same problem of busy waiting.
In actual implementation, when a process execute wait() operation and find that semaphore value is not positive, it must wait. However, instead of busy waiting, the process block itself.
In this implementation, semaphore values may be negative, while they are never negative in classical definition with busy waiting. If a semaphore value is negative, its magnitude indicates the number of waiting processes (note different order of decrement in wait()). 

It is critical that semaphore operations be executed atomically: no two processes can execute wait() and signal() operations on the same semaphore at the same time.
In a multiprocessor environment, usually compare_and_swap() or spin locks are used to ensure wait() and signal() are atomic.
So, we admit that busy waiting is NOT eliminated in this implementation. However, busy waiting is limited to CS of the wait() and signal() operations. These CSs are short (about 10 instructions). Thus, CS is almost never occupied, and busy waiting is rare and short, if ever happens.

### 5.7: Classic Problems of Synchronization

Use semaphores for synchronization. Actual implementation can use mutex instead of binary semaphore.

Bounded-Buffer (Consumer-Producer) problem
Problem: See 5.1.
Solution: The producer and consumer share the following data structure:
The mutex is used to provide mutual exclusion for accesses to the buffer pool.
int n;
semaphore mutex = 1;
semaphore empty = n;
semaphore full = 0

Producer	Consumer
do {
. . .
/* produce an item in next produced */
. . .
wait(empty);
wait(mutex);
. . .
/* add next produced to the buffer */
. . .
signal(mutex);
signal(full);
} while (true);
do {
wait(full);
wait(mutex);
. . .
/* remove an item from buffer to next consumed */
. . .
signal(mutex);
signal(empty);
. . .
/* consume the item in next consumed */
. . .
} while (true);


Reader-Writer problem:
Writers should have exclusive access while writing to the shared database.
There are several variants of “reader-writer” problems:
First problem: No reader to be kept waiting unless a writer has already obtained accesss.
Second problem: Once writer is ready, that writer perform its write ASAP. No new readers may start reading.
A solution to either problem may result in starvation.

Solution to first reader-writer problem: shared data structure
semaphore rw_utex = 1;
semaphore mutex = 1;
int read count = 0;
The mutex semaphore is used to ensure mutual exclusion when the variable read count is updated. 
The read count variable keeps track of how many processes are currently reading the object. 
The semaphore rw_mutex functions as a mutual exclusion semaphore for the writers.

Writer	Reader
do {
wait(rw mutex);
. . .
/* writing is performed */
. . .
signal(rw mutex);
} while (true);
do {
wait(mutex);
read count++;
if (read count == 1)
wait(rw mutex);
signal(mutex);
. . .
/* reading is performed */
. . .
wait(mutex);
read count--;
if (read count == 0)
signal(rw mutex);
signal(mutex);
} while (true);

Dining Philosopher problem: This solution can create a deadlock
semaphore chopstick[5];

do {
wait(chopstick[i]);
wait(chopstick[(i+1) % 5]);
. . .
/* eat for awhile */
. . .
signal(chopstick[i]);
signal(chopstick[(i+1) % 5]);
. . .
/* think for awhile */
. . .
} while (true);

Several possible remedies to the deadlock problem are replaced by:
• Allow at most four philosophers to be sitting simultaneously at the table.
• Allow a philosopher to pick up her chopsticks only if both chopsticks are
available (to do this, she must pick them up in a critical section).
• Use an asymmetric solution—that is,an odd-numbered philosopher picks
up first her left chopstick and then her right chopstick, whereas an even-
numbered philosopher picks up her right chopstick and then her left
chopstick.

In 5.8, we use monitor (equivalent to a waiter to tell which philosopher should eat) to provides deadlock-free solution.
A deadlock-free solution does not necessarily eliminate the possibility of starvation.

### 5.8: Monitors

Semaphore is not a complete solution. If a single process is not well-behaved (semaphore used incorrectly), the system break down.
Incorrect order of signal() and wait(): mutual exclusion is no longer guaranteed.
wait() is used in place of signal(): a deadlock may occur.
wait() or signal() or both are omitted: mutual exclusion violated or deadlock.

Syntax of a monitor:


Local variables of a monitor can be accessed by only the local functions. Only one process at a time is active within the monitor.
We also defines condition construct: condition x, y; // condition variables
The only operations that can be invoked on a condition variable are wait() and signal().
The operation x.wait(); means that the process invoking this operation is suspended until another process invokes x.signal();
The x.signal() operation resumes exactly one suspended process. If no process is suspended, then the signal() operation has no effect.
(Different from semaphore’s signal(): semaphore() signal always change the state of semaphore, condition’s signal() may not).

Dining Philosophers solution using Monitors:
Monitor is acting like a waiter/moderator. Before a philosopher starts eating, she informs the waiter (invoked operation pickup()) and the waiter will tell her what to do.
After she is done eating, she again informs the waiter (putdown()). It is still possible that some philosopher will starve to death.



condition self[5];
allows philosopher i to delay herself when she is hungry but is unable to obtain the chopsticks she needs.

Implement a Monitor with Semaphores
Check Section 5.8.3 page 229.

Resuming Processes within a Monitor
One simple solution is to use FIFO ordering.
Another solution is conditional-wait construct, with c is the priority number input.
x.wait(c);
When x.signal() is executed, the process with the smallest priority number is resumed next.

Java monitors
Java uses monitor for thread synchronization. 
Every object in Java has a single lock associated with it. When a method is declared synchronized, calling the method requires owning the lock of the object.
If the lock is not available, the synchronized method is placed in the entry set for the object’s lock.
The Java Object class’s method wait() and notify() are similar to wait() and signal() statements for a monitor.

### Reference

* [Java Concurrency](/download/Java_Concurrency.pdf)