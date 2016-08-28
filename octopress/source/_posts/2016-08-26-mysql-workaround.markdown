---
layout: post
title: "Tutorial: MySQL workarounds"
date: 2016-08-26 01:42:23 -0700
comments: true
categories: 
- MySQL
- Facebook
- Tutorial
---

MySQL has traditionally lagged behind in support for the SQL standard. 
Unfortunately, from my experience, MySQL is often used as the sandbox for SQL code challenges and interviews. 
If you are used to work with Vertica SQL, writing SQL statements in MySQL can be challenging exercises, NOT necessarily in a good way, because many useful features are not supported.

<!--more-->

### WITH clause

Use [nested subqueries](http://tdongsi.github.io/blog/2016/08/17/analytic-functions-in-mysql/).

### Analytic functions `ROW_NUMBER`, `RANK`, and `DENSE_RANK`

Summary from [here](http://tdongsi.github.io/blog/2016/08/17/analytic-functions-in-mysql/).

``` sql ROW_NUMBER, RANK, and DENSE_RANK functions in MySQL
-- In Vertica
SELECT
ROW_NUMBER () OVER (PARTITION BY col_1, col_2 ORDER BY col_3 DESC) AS row_number,
RANK () OVER (PARTITION BY col_1, col_2 ORDER BY col_3 DESC) AS rank,
DENSE_RANK () OVER (PARTITION BY col_1, col_2 ORDER BY col_3 DESC) AS dense_rank,
t.*
FROM table_1 t

-- In MySQL
SELECT
@row_num:=IF(@prev_col_1=t.col_1 AND @prev_col_2=t.col_2, @row_num+1, 1) AS row_number,
@rank:=IF(@prev_col_1=t.col_1 AND @prev_col_2=t.col_2 AND @prev_col_3=col_3, @rank, @row_num) AS rank,
@dense:=IF(@prev_col_1=t.col_1 AND @prev_col_2=t.col_2, IF(@prev_col_3=col_3, @dense, @dense+1), 1) AS dense_rank,
@prev_col_1 = t.col_1,
@prev_col_2 = t.col_2,
@prev_col_3 = t.col_3,
t.*
FROM (SELECT * FROM table_1 ORDER BY col_1, col_2, col_3 DESC) t,
     (SELECT @row_num:=1, @dense:=1, @rank:=1, @prev_col_1:=NULL, @prev_col_2:=NULL, @prev_col_3:=NULL) var
```

In the following example, note that the outer SELECT is used to only expose only columns of interest while the main SQL code is enclosed in a subquery:

``` sql Solution in Vertica SQL
select Score,
DENSE_RANK() OVER (ORDER BY Score DESC) AS Rank
FROM Scores;
```

``` sql Solution in MySQL
SELECT Score, Rank FROM
( SELECT t.Score,
@dense:=IF(@prev_col2=t.Score, @dense, @dense+1) AS Rank,
@prev_col2:=t.Score
FROM (SELECT Score FROM Scores ORDER BY Score DESC) t,
(SELECT @dense:=0, @prev_col2:=NULL) var ) x
```