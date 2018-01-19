---
layout: post
title: "Questions: SQL basics"
date: 2018-08-02 01:39:37 -0700
comments: true
categories: 
- Facebook
- Questions
- SQL
---

This post is about basic DDL, `SELECT` questions (phone-screen level). 

<!--more-->

### Questions

``` plain Given Department schema for Questions 1-3
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

#### Question 2

``` plain Question 2
List the current projects and employees assigned to them.

Output:
+------------+-----------+--------------------------+
| first_name | last_name | title                    |
+------------+-----------+--------------------------+
| John       | Smith     | Update TPS Reports       |
| Ava        | Muffinson | Design 3 New Silly Walks |
| Cailin     | Ninson    | Build a cool site        |
| Mike       | Peterson  | Build a cool site        |
| Ian        | Peterson  | Build a cool site        |
+------------+-----------+--------------------------+
```

#### Question 3

``` plain Question 3
Who's the highest paid person per department?

Output:
+------------+-----------+-------------+--------+
| first_name | last_name | name        | salary |
+------------+-----------+-------------+--------+
| John       | Smith     | Reporting   |  20000 |
| Ian        | Peterson  | Engineering |  80000 |
| John       | Mills     | Marketing   |  50000 |
| Ava        | Muffinson | Silly Walks |  10000 |
+------------+-----------+-------------+--------+
```

#### Question 4

``` plain Given fact_sales table
create table fact_sales (
  dateid date not null,
  fruit varchar(10),
  sold int
);

insert into fact_sales
values(STR_TO_DATE('2015-01-01', '%Y-%m-%d'), 'Apple', 31); 
insert into fact_sales
values(STR_TO_DATE('2015-01-01', '%Y-%m-%d'), 'Orange', 19); 
insert into fact_sales
values(STR_TO_DATE('2015-01-02', '%Y-%m-%d'), 'Apple', 37); 
insert into fact_sales
values(STR_TO_DATE('2015-01-02', '%Y-%m-%d'), 'Orange', 26); 
insert into fact_sales
values(STR_TO_DATE('2015-01-03', '%Y-%m-%d'), 'Apple', 21); 
insert into fact_sales
values(STR_TO_DATE('2015-01-03', '%Y-%m-%d'), 'Orange', 23); 
insert into fact_sales
values(STR_TO_DATE('2015-01-04', '%Y-%m-%d'), 'Apple', 35); 
insert into fact_sales
values(STR_TO_DATE('2015-01-04', '%Y-%m-%d'), 'Orange', 27);

fact_sales
+------------+--------+------+
| dateid     | fruit  | sold |
+------------+--------+------+
| 2015-01-01 | Apple  |   31 |
| 2015-01-01 | Orange |   19 |
| 2015-01-02 | Apple  |   37 |
| 2015-01-02 | Orange |   26 |
| 2015-01-03 | Apple  |   21 |
| 2015-01-03 | Orange |   23 |
| 2015-01-04 | Apple  |   35 |
| 2015-01-04 | Orange |   27 |
+------------+--------+------+
```

``` plain Question 4
What is the difference between Apples and Oranges sold each day?

Output:
+------------+------+
| dateid     | sold |
+------------+------+
| 2015-01-01 |   12 |
| 2015-01-02 |   11 |
| 2015-01-03 |   -2 |
| 2015-01-04 |    8 |
+------------+------+
```

#### Question 5

``` plain Given schema
fct_request
+------------+------------+---------------+
| dateid     | sender_uid | recipient_uid |
+------------+------------+---------------+
| 2015-01-01 |          1 |             2 |
| 2015-01-01 |          1 |             4 |
| 2015-01-02 |          3 |             1 |
| 2015-01-03 |          4 |             5 |
+------------+------------+---------------+

fct_accept
+------------+--------------+------------+
| dateid     | accepter_uid | sender_uid |
+------------+--------------+------------+
| 2015-01-01 |            2 |          1 |
| 2015-01-02 |            4 |          1 |
| 2015-01-02 |            1 |          3 |
+------------+--------------+------------+

Rules:
1. You can only send a friend request to a person one time.
2. You cannot send a friend request to someone who has already sent you a friend request.
```

``` plain Question 5
What percent of friend requests are accepted?
Data might be not perfect.
```

#### Question 6

Create DDL (table and foreign keys) for several tables in a provided ERD. 
ERD contains at least one many to many relationship. 
For example: Player(personID, school) and Team(teamID, name).
Player and Team will have a many to many relationship.

#### Question 7

This question is a bit advanced.

You have a table where you have `date`, `user_id`, `song_id` and `count`. 
It shows at the end of each day how many times in her history a user has listened to a given song. 
So count is cumulative sum. 
You have to update this on a daily basis based on a second table that records in real time when a user listens to a given song. 
Basically, at the end of each day, you go to this second table and pull a count of each user/song combination and then add this count to the first table that has the lifetime count.
If it is the first time a user has listened to a given song, you won't have this pair in the lifetime table, so you have to create the pair there and then add the count of the last day.

For simplicity, assume the tables are fact_event: (date, user_id, song_id) and snapshot: (date, user_id, song_id, count).

### Answers

#### Question 1

The sandbox uses MySQL which does not support WITH clause.
See more MySQL workaround in [here](/blog/2016/08/28/mysql-workaround/).

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

#### Question 2

``` sql Answer 2
select e.first_name, e.last_name, p.title
from employees_projects ep
join employees e on ep.employee_id = e.id
join projects p on ep.project_id = p.id;
```

#### Question 3

Again, the sandbox is MySQL and `dense_rank` is not available. 

``` sql Answer 3
select e.first_name, e.last_name, d.name, e.salary
FROM employees e
JOIN departments d on e.department_id = d.id
WHERE (e.department_id, e.salary) in
(
select department_id, max(salary) AS salary
from employees
group by department_id
)
```

#### Question 4

You can self-join the table to get the following answer.

``` sql Naive answer
select orange.dateid, apple.sold - orange.sold
from fact_sales orange
join fact_sales apple on orange.dateid = apple.dateid
and orange.fruit = 'Orange' and apple.fruit = 'Apple';
```

However, recall that in dimensional modeling, fact tables usually have huge number of rows.
Therefore, the expected answer should avoid joining such a huge table.

``` sql Expected answer
select dateid, sum(mod_sold)
from (
select dateid, case when fruit = 'Apple'  then sold else -sold end as mod_sold
from fact_sales
) t
group by dateid;
```

#### Question 5

``` sql Answer 5
select count(requested), sum(accepted)
FROM (
select r.sender_uid as requested, CASE when a.sender_uid is null then 0 else 1 end as accepted
from fct_request r
left join fct_accept a on r.sender_uid = a.sender_uid and r.recipient_uid = a.accepter_uid
) t
```

#### Question 6

You should create a `teamPlayer` table with foreign keys to `Team` and `Player` tables as primary key.
You are expected to write the following DDL statements.

``` sql DDL for teamPlayer
CREATE TABLE teamPlayer
(
playerID INT NOT NULL, 
teamID INT NOT NULL,
PRIMARY KEY(playerID, teamID)
);

alter table teamPlayer
add constraint 
  fk_teamPlayer__Player foreign key(playerID) references Player(personID);

alter table teamPlayer
add constraint 
  fk_teamPlayer__Team foreign key(teamID) references Team(teamID);
``` 

#### Question 7

**Scenario 1**: Overwrite the snapshot. Date column in snapshot is the last modified date.

```
create temporary table temp
AS
(select user_id, song_id, count(*)
from fact_event
where date > (select max(date) from snapshot)
group by user_id, song_id
);

UPDATE snapshot as s
SET date = current_date, s.count = s.count + t.count
FROM temp t
WHERE s.user_id = t.user_id AND s.song_id = t.song_id;

--- (MySQL: UPDATE might be different)

INSERT snapshot (date, user_id, song_id, count)
SELECT current_date, user_id, song_id, count
from temp
where (user_id, song_id) not in (select user_id, song_id from snapshot) x;
```

**Scenario 2**: Append new snapshot for each date.

``` sql
create temporary table temp
AS
(select user_id, song_id, count(*)
from fact_event
where date > (select max(date) from snapshot)
group by user_id, song_id
);

insert into snapshot (date, user_id, song_id, count)
select current_date, t.user_id, t.song_id, t.count + coalesce(s.count, 0)
from temp t
join snapshot s on t.user_id = s.user_id and t.song_id = s.song_id and s.date = current_date-1;
```
