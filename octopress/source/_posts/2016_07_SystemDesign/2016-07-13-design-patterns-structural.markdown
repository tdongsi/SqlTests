---
layout: post
title: "Design Patterns: Structural"
date: 2017-07-13 21:28:46 -0800
comments: true
categories: 
- Design
---

In [summary](https://en.wikipedia.org/wiki/Facade_pattern#Usage):

* Adapter: Converts one interface to another so that it matches what the client is expecting
* Decorator: Dynamically adds responsibility to the interface by wrapping the original code
* Facade: Provides a simplified interface to a number of subsystems
* Proxy: Real object and Proxy object both implementing the same interface.
* Composite: Composite object containing multiple Single objects implementing the same interface.

<!--more-->

### Composite pattern

A group of objects that is treated the same way as a single instance of the same type of object. 
The intent of a composite is to "compose" objects into tree structures to represent some hierarchies.

``` java Java example
interface Graphic {
    public void print();
}

class CompositeGraphic implements Graphic {
    private List<Graphic> childGraphics = new ArrayList<Graphic>();

    public void print() {
        for (Graphic graphic : childGraphics) {
            graphic.print();
        }
    }
    ...
}
```

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

Both Real object and Proxy object implementing the same interface with Proxy object to control access to Real object.
Proxy object can add functionality when Real object is accessed (filter, enrich).

* [Wikipedia has good descriptions and diagrams](https://en.wikipedia.org/wiki/Proxy_pattern)

```java Java example
interface Image {
    public void displayImage();
}

class RealImage implements Image {
    private String filename = null;

    public RealImage(final String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }

    public void displayImage() {
        System.out.println("Displaying " + filename);
    }

}

class ProxyImage implements Image {
    private RealImage image = null;
    private String filename = null;

    public ProxyImage(final String filename) {
        this.filename = filename;
    }

    public void displayImage() {
        // Lazy loading is possible due to controlled access
        if (image == null) {
           image = new RealImage(filename);
        }
        image.displayImage();
    }

}
```

```python Python example
from abc import ABCMeta, abstractmethod

class AbstractCar:
    __metaclass__ = ABCMeta

    @abstractmethod
    def drive(self):
        raise NotImplementedError(NOT_IMPLEMENTED)


class Car(AbstractCar):
    def drive(self):
        print("Car has been driven!")


class Driver(object):
    def __init__(self, age):
        self.age = age


class ProxyCar(AbstractCar):
    def __init__(self, driver):
        self.car = Car()
        self.driver = driver

    def drive(self):
        if self.driver.age <= 16:
            print("Sorry, the driver is too young to drive.")
        else:
            self.car.drive()
```
