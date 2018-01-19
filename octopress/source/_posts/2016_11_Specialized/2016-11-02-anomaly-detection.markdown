---
layout: post
title: "Anomaly Detection"
date: 2021-11-02 22:42:54 -0700
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

According to [this cheat sheet](/download/microsoft-machine-learning-algorithm-cheat-sheet-v6.pdf), the standard approaches are PCA-based anomaly detection and one-class SVM (>100 features, aggressive boundary).
These are rules of thumb: for specific sets of data with specific advanced information can lead to another more efficient approach.

An analogy: without specific information, merge sort is the safest choice for sorting.
However, with extra information about incoming data such as its randomness (quicksort for really random data) or range of possible values (radix sort for range much smaller than numbers), you can find a better choice for sorting.

### Principle Component Analysis (PCA)

#### Theory

#### Application to Anomaly Detection

### Support Vector Machine (SVM)

General theory: The most common theory is two-class SVM where we find the hyperplane that best divides the samples of two classes. 
The problem can be formulated as a constrained optimization problem, which can be solved by quadratic programming methods.
At the end, there are some samples that are closest to the optimal hyperplane is called support vectors.
The awesome thing about SVM is that you can apply non-linear transformations, including adding more dimensions, to samples in both classes and SVM still works.
Such transformations (kernels) must have some properties and there are list of common kernel types.

The error is bounded by the number of support vectors -> it is better to have low average support vector.

* [Tutorial with familar notation (Caltech)](https://www.youtube.com/watch?v=eHsErlPJWUU)
* [MIT tutorial](https://www.youtube.com/watch?v=_PwhiWxHK8o)

One class SVM: samples are from positive class only.

* [One class SVM tutorial](https://www.youtube.com/watch?v=rNGtj2iEw6g)

#### Application to DevOps

* [Mostly Gausian + Correlation for Context data](https://www.youtube.com/watch?v=5vrY4RbeWkM)

### Reference

* [Machine Learning cheat sheet](https://docs.microsoft.com/en-us/azure/machine-learning/studio/algorithm-cheat-sheet)
* [PDF mirror](/download/microsoft-machine-learning-algorithm-cheat-sheet-v6.pdf)
