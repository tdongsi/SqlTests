---
layout: post
title: "Tutorial: Dashboard for Business Analytics"
date: 2016-08-05 15:40:35 -0700
comments: true
categories: 
- Database
- Tutorial
- Facebook
---

Summary of "Business Dashboard Fundamentals" on Pluralsight.

<!--more-->

### General guidelines for dashboard

Ppl looks for different things in data. You have to find out what answers users look for in dashboard.

* Trend: bar, graph
* Aggregation: Average, Sum, Max, Min.


Above all else, show data.
Trying to improve data-pixel ratio: data pixels/non-data pixels.
How to enhance data-pixel ratio:

* Granularity: depends on the question you want to answer
	* monthly if you want to know monthly sales, daily if you want to know what happens last Tuesday.
	* category or sub-category: you can have category with drill down function.
* Annotation: similar to Granularity. Minimize it to enough to answer the question.

Tricks & Tips:

* Plots (over Bars): plot show the trends for different components over time.
* Sizing Bars: Preserve True Portions: starting Y from 0.
	* Sizing Bars: shows proportions, relative progression.
* Scatter Plots: show clusters, outliers.
* Radio displays: usually a bad idea. Waste of space, hard to discern between slices.


### Module 2: Common Charts

Basic data Presentation Methods - Chart Types

* Geo-Spatial - Maps: anything related to geographic distribution, i.e., when geography matters. e.g. real estates, oil industry.
* Correlation - Scatter Plots: two measurements (e.g., sales to profit).
* Hierarchical - Drill down Tree: data is hierarchical: Category -> Subcateogries.
* Categorical - Bar Charts: comparing categories (sales by region)
* Time Series - Line Charts: progression over time. (sales by month)
	* Avoid: Stacked Area Charts. If you have more than two lines, Area Charts do not give any information except for the bottom and the total.
* Distribution - Histograms: Trying to answer what is “normal”. e.g., home prices, salaries.

Others

* Box plot: distribution, percentiles, median in 1 chart.
* Bullet graph: actuals to target. Invented by Stephen Few.
	* Dark bar is actual, reference line is target. Color code bands are average, good, bad target range.
	* https://en.wikipedia.org/wiki/Bullet_graph
* Sparkline: Multiple line charts. Best used for monitoring dashboard.
* Heat map: Large combinations of dimensions. Color is everything here.


Charts to avoid

* Pie charts: angles make it hard to compare. Usually decorative, not informative. Space is wasted.
* Polar charts
* Stacked area charts:
	* Only tell the story of the bottom line and the total. Anything in between, you can’t really tell if they are growing or not.
	* Misleading/Confusing: is the top the total or another category?

### Module 3: Dashboard planning

Steps:

* User Request
* Prioritization
* Planning
* Design
* Development
* Delivery to User


### Module 4: Dashboard design 

Audience is King. Know your audience.

* Who is using it? 
* Are they technical or prefer dumbed down answers? Are they intimate with data?
* What is primary objective? What questions that they try to answer? What questions this dashboard MUST answer?
* What impact of the answer? How will they use metrics? (Role, what decisions they make)
* When will the dashboard is used? (Weekly? Daily?) Dashboard is exploratory or explanatory?
* What level of confidence in data sources?

Dashboard layout: F layout is the most natural for web/desktop viewing.


### Module 5 & 6: Tableau

Connecting to Data:
You can connect to Excel, text file (csv or tab), or HP Vertica.
You can specify data import like Excel or using Custom SQL.
After importing, Tableau may import all data into its own internal data engine (with compression, data reorganization easier for analytics).
It also divides data into dimensions and measures.
Dimensions are further categorized into: geographic (e.g., region, postal code), number, text, date (e.g., calendar, order_date).
Facts are usually numbers but it can be other categories: e.g., geographic for latitude/longitude measures.

Visualizing data:
Tableau has “Show Me” button that gives suggestions for different combinations of dimensions and fact data.
