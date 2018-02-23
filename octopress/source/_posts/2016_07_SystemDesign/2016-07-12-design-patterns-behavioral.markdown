---
layout: post
title: "Design Patterns: Behavioral"
date: 2016-07-12 21:28:57 -0800
comments: true
categories: 
---

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
