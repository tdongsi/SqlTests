---
layout: post
title: "Tutorial: Estimation theory"
date: 2016-11-01 23:40:12 -0700
comments: true
categories: 
- Tutorial
- Math
---

Math in Estimation theory.
<!--more-->

### Basic statistics

Two vectors **x** and **z** is joinly Guassian when the combined vector **y** = [x z] is also Gaussian. 
Jointly Gaussian implies marginally Guassian, conditionally Gaussian. The reverse is not true.

[Simpson’s paradox](https://en.wikipedia.org/wiki/Simpson%27s_paradox): e.g. Warriors is first in 2P% and 3P% in NBA season 2016, but not first in FG%. 
The first team in FG% is Spurs, who took lots of 2s.

### Basic Kalman Filter

TODO

#### Kalman Filter

#### Extended Kalman Filter

### EKF-SLAM formulation

TODO

### Mingyang's thesis

Contributions:

1. Analysis of EKF-SLAM and MSCKF. New estimator (MSCKF 2.0) with correct observability.
2. Hybrid estimator that picks either EKF-SLAM formulation or MSCKF 2.0, depending on length of feature tracks.
3. Online calibration of the spatial and temporal relationship between visual and inertial sensors.
4. Sensor models for rolling shutter cameras and low-cost inertial sensors.
    1. IMU axis misalignment, scale factors, and g-sensitivity affects inertial sensors.
    2. Image distortions from rolling shutter cameras.

 Why? IMU and cameras are already found in several commercial resource-constrained devices (e.g., mobile phones and AR devices).

#### Contribution 1: EKF-SLAM and MSCKF -> MSCKF 2.0

EKF-SLAM formulation: current pose + feature positions.
Since we are not doing mapping, only currently visible features are kept -> computational cost is bounded.

MSCKF: a sliding window of poses.
Measurements are used to impose the constraints on these poses.
If a new feature is found, add a new pose to the state vector and augment covariance matrix accordingly.
Each feature is tracked until it goes out of field of view, then all of its observations are processed at once.
A pose is only removed when all features associated with that pose have been processed.

Consistency and accuracy of estimators are correlated.
A recursive estimator is consistent if the estimation errors are zero-mean and have covariance matrix as reported by the estimator.

Why difference? Assumptions:

* In EKF: IMU state and feature positions are jointly Gaussian. With non-linear measurement models, this is a strong assumption. 
    * To improve it, need to pick another feature parameterization to make the measurement model closer to linear.
* In MSCKF, there is no feature positions. No assumptions on feature positions are required.
* MSCKF delay linearization: only process each feature when all of its measurement are available -> better estimates -> better Jacobians -> better updates.
* In EKF-SLAM, using fewer observations: e.g., in standard XYZ parameterizaton, it can lead to wildly inaccurate estimates.

#### Contribution 2: Hybrid estimator, pick one

* N: number of features.
* m: feature length: max number of observations per feature.

Then, the compuational costs of the two estimators are

* MSCKF: O(N) and O(m^3).
* EKF-SLAM: O(N^3) and O(m).

MSCKF is faster because of general distribution of features: because of feature detection algorithms, majority of features are detected close to the camera, where it will goes out of the FOV quickly (large N, small m).
For example, in Cheddar Gorge data, many features are close to the car/camera, while a few are really far away.

Depending on the length of feature tracks in current environment, use one.
Given “many” measurements, nothing is gained by initializing features  observed fewer than m times.
So, if the features is observed less than m times, use MSCKF. Otherwise, put it in the state vector and use EKF-SLAM.
m (sliding window size) is to determined empirically: plotting and see the low points.

#### Contribution 3: Online camera-to-IMU calibration

Detailed identifiability analysis of these parameters.
Time offset between the two measurements.
The degenerate cases are known and rare cases. 

Some degenerate cases are: (Recovery?) 

* Going in a straight line
* Constant acceleration with no rotation
* Constant velocity (no acceleration) with rotation about gravity vector only.


#### Contribution 4: Models for low-cost sensors

Measurement models for rolling shutter camera.
