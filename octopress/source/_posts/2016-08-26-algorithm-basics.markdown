---
layout: post
title: "SQL basics"
date: 2016-08-26 01:39:37 -0700
comments: true
categories: 
- Facebook
---

### Section 1

``` plain Given Department schema
employees                             projects
+---------------+---------+           +---------------+---------+
| id            | int     |<----+  +->| id            | int     |
| first_name    | varchar |     |  |  | title         | varchar |
| last_name     | varchar |     |  |  | start_date    | date    |
| salary        | int     |     |  |  | end_date      | date    |
| department_id | int     |--+  |  |  | budget        | int     |
+---------------+---------+  |  |  |  +---------------+---------+
                             |  |  |
departments                  |  |  |  employees_projects
+---------------+---------+  |  |  |  +---------------+---------+
| id            | int     |<-+  |  +--| project_id    | int     |
| name          | varchar |     +-----| employee_id   | int     |
+---------------+---------+           +---------------+---------+
```

#### Question 1

``` plain Question 1
Write a query to list the departments that have a total combined salary greater than $40,000.

Expected Output:
+-------------+----------------+
| name        | combined_salary|
+-------------+----------------+
| Engineering |        130000  |
| Marketing   |         50000  |
+-------------+----------------+
```

The sandbox uses MySQL which does not support WITH clause.
See more MySQL workaround in [here](http://tdongsi.github.io/blog/2016/08/17/analytic-functions-in-mysql/).

``` sql Answer 1
WITH dept_total AS (
select department_id, sum(salary) as total
from employees
group by department_id
having total > 40000
)
select d.name, t.total
from dept_total t
join deparments d on t.department_id = d.id
```

``` sql MySQL Answer 1
select d.name, t.total
from (
  select department_id, sum(salary) as total
from employees
group by department_id
having total > 40000
  ) t
  join departments d ON t.department_id = d.id
```
