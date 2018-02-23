---
layout: post
title: "Design Patterns: Structural"
date: 2016-07-13 21:28:46 -0800
comments: true
categories: 
---

In [summary](https://en.wikipedia.org/wiki/Facade_pattern#Usage):

* Adapter: Converts one interface to another so that it matches what the client is expecting
* Decorator: Dynamically adds responsibility to the interface by wrapping the original code
* Facade: Provides a simplified interface to a number of subsystems
* Proxy: Real object and Proxy object both implementing the same interface.

<!--more-->

### Flyweight pattern

Uses sharing to support large numbers of fine-grained objects efficiently.

Java example: String interning (`intern()` method in class String) is the example of Flyweight pattern.

Python example: from [here](https://en.wikipedia.org/wiki/Flyweight_pattern)

```python
class CheeseShop(object):
    menu = {}  # Shared container to access the Flyweights
    
    def __init__(self):
        self.orders = {}  # per-instance container with private attributes

    def stock_cheese(self, brand, cost):
        cheese = CheeseBrand(brand, cost)
        self.menu[brand] = cheese   # Shared Flyweight

    def sell_cheese(self, brand, units):
        self.orders.setdefault(brand, 0)
        self.orders[brand] += units   # Instance attribute

shop1 = CheeseShop()
shop2 = CheeseShop()

shop1.stock_cheese('white', 1.25)
shop1.stock_cheese('blue', 3.75)
# Now every CheeseShop have 'white' and 'blue' on the inventory
# The SAME 'white' and 'blue' CheeseBrand

shop1.sell_cheese('blue', 3)    # Both can sell
shop2.sell_cheese('blue', 8)    # But the units sold are stored per-instance
```

### Proxy pattern
