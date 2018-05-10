---
layout: post
title: "Design Patterns: Behavioral"
date: 2017-07-12 21:28:57 -0800
comments: true
categories: 
- Design
---

They are:

* Observer
* State
* Visitor

<!--more-->

### Observer pattern

* [Example](https://en.wikipedia.org/wiki/Observer_pattern#Example)

``` java Java example
// Available in java.util
interface Observer{
    public void update(Observable obj, Object arg);
}

class EventSource extends Observable implements Runnable {
    public void run() {
        while (true) {
            String response = new Scanner(System.in).next();
            setChanged();
            notifyObservers(response);
        }
    }
}

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Enter Text: ");
        EventSource eventSource = new EventSource();

        eventSource.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
                System.out.println("Received response: " + arg);
            }
        });

        new Thread(eventSource).start();
    }
}
```

``` python Python example
class Observable:
    def __init__(self):
        self.__observers = []
    
    def register_observer(self, observer):
        self.__observers.append(observer)
    
    def notify_observers(self, *args, **kwargs):
        for observer in self.__observers:
            observer.notify(self, *args, **kwargs)

class Observer:
    def __init__(self, observable):
        observable.register_observer(self)
    
    def notify(self, observable, *args, **kwargs):
        print('Got', args, kwargs, 'From', observable)

subject = Observable()
observer = Observer(subject)
subject.notify_observers('test')
```

### State pattern

An object can change its behavior when its internal state changes.
It is similar to Strategy pattern, except there is automatic state transitions and the strategies are changed for each state.

* State interface <- Implemented in StateOne and StateTwo class.
* In both StateOne and StateTwo classes, there is a method `operation(mainObj, op)` that defines the current strategy AND state transition of mainObj.
* Object class has a private State, provides public method `setState` to be called in `operation` method.
* Object also provides a public method `doOperation` that will call State's `operation(this, op)` to carry out the action.

Reference

* [Full example](https://github.com/tdongsi/java/tree/master/AdvancedJava/src/main/java/my/learning/patterns/state) based on [here](https://en.wikipedia.org/wiki/State_pattern#Java)
* [Python example](https://github.com/tdongsi/gtg/blob/develop/hackerrank/Encircular.py) used to solve a HackerRank problem.

``` java Java example
public interface State {
    void operation(StatefulObject object, String params);
}

public class StateOne implements State {
    @Override
    public void operation(StatefulObject object, String params) {
        System.out.println(params.toLowerCase());
        object.setState(new StateTwo());
    }
}

public class StatefulObject {
    private State state;

    public StatefulObject() {
        setState(new StateOne());
    }

    public void setState(final State newState) {
        this.state = newState;
    }

    public void doOperation(String params) {
        state.operation(this, params);
    }
}
```

### Visitor

[Use Visitor pattern when](https://en.wikipedia.org/wiki/Visitor_pattern#Uses)

* the classes that make up the object structure are known and NOT expected to change much
* new operations need to be added frequently
