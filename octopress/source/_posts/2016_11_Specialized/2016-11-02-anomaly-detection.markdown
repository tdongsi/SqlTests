---
layout: post
title: "Anomaly Detection"
date: 2017-09-27 22:42:54 -0700
comments: true
categories: 
- Math
- MachineLearning
---

Anomaly detection is pretty important in DevOps world. 
It is great if it can generate some alarm when something odd is happening in monitoring metrics.
This post will discuss some basic ideas of how to approach Anomaly Detection.

<!--more-->

### Anomaly Detection

According to [PDF mirror](/download/microsoft-machine-learning-algorithm-cheat-sheet-v6.pdf), the standard approaches are PCA-based anomaly detection and one-class SVM (>100 features, aggressive boundary).
These are rules of thumb: for specific sets of data with specific advanced information can lead to another more efficient approach.

An analogy: without specific information, merge sort is the safest choice for sorting.
However, with extra information about incoming data such as its randomness (quicksort for really random data) or range of possible values (radix sort for range much smaller than numbers), you can find a better choice for sorting.

### Principle Component Analysis (PCA)

#### Theory

#### Application to Anomaly Detection

### Support Vector Machine (SVM)

### Reference

* [Machine Learning cheat sheet](https://docs.microsoft.com/en-us/azure/machine-learning/studio/algorithm-cheat-sheet)
* [PDF mirror](/download/microsoft-machine-learning-algorithm-cheat-sheet-v6.pdf)
