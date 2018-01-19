---
layout: post
title: "Database Question bank"
date: 2018-08-03 01:41:54 -0700
comments: true
categories: 
- Questions
- Database
---

Questions about database in general. For data warehouse and dimensional modelling, check [this](/blog/2016/09/15/tutorial-dimensional-modelling/). 

<!--more-->

### Relational Database topics

Basics

* SELECTing columns from a table
* Aggregates Part 1: COUNT, SUM, MAX/MIN
* Aggregates Part 2: DISTINCT, GROUP BY, HAVING

Intermediate

* JOINs, ANSI-89 and ANSI-92 syntax
* UNION vs UNION ALL
* NULL handling: COALESCE & Native NULL handling
* Subqueries: IN, EXISTS, and inline views
* Subqueries: Correlated
* WITH syntax: Subquery Factoring/CTE
* Views

Advanced Topics

* Functions, Stored Procedures, Packages
* Pivoting data: CASE & PIVOT syntax
* Hierarchical Queries
* Cursors: Implicit and Explicit
* Triggers
* Dynamic SQL
* Materialized Views
* Query Optimization: Indexes
* Query Optimization: Explain Plans
* Query Optimization: Profiling
* Data Modelling: Normal Forms, 1 through 3
* Data Modelling: Primary & Foreign Keys
* Data Modelling: Table Constraints
* Data Modelling: Link/Corrollary Tables
* Full Text Searching
* XML
* Isolation Levels
* Entity Relationship Diagrams (ERDs), Logical and Physical
* Transactions: COMMIT, ROLLBACK, Error Handling

#### References

* http://stackoverflow.com/questions/2119859/questions-every-good-database-sql-developer-should-be-able-to-answer
* http://www.careercup.com/page?pid=database-interview-questions

### Questions

(1) Given these two databases:

``` plain Given databases
id name id name 
-- ---- -- ---- 
1 Pirate 1 Rutabaga 
2 Monkey 2 Pirate 
3 Ninja 3 Darth Vader 
4 Spaghetti 4 Ninja
```

Explain the following JOINs:

1. INNER JOIN
1. LEFT and RIGHT OUTER JOIN
1. FULL JOIN
1. CROSS JOIN

B set:

1. What is normalization and why is it important?
2. What are some situations where you would de-normalize data?
3. What is a transaction and why is it important?
4. What is referential integrity and why is it important?
5. What steps would to take to investigate reports of slow database performance?
6. What is an index and how does it help your database?
7. If someone were to make the claim that: "every SELECT always include DISTINCT"; how would you comment on the claim?
OLTP and OLAP points of view?


### Answer keys

#### Section A

(1) http://www.codinghorror.com/blog/2007/10/a-visual-explanation-of-sql-joins.html

#### Section B

(1) Normalizing: remove redundancy -> remove update and delete anomaly -> more efficient data storage and consistent data.

Summary from Introduction to Databases
To reduce redundancy. From redundancy, it will lead to update and delete anomaly.
E.g.: dim_company: region/country info will be repeated -> redundancy.
E.g.: North Korea and South Korea merged into Korea -> delete those regions will delete companies -> delete anomaly.

Popular normal forms:

BCNF: For each functional dependency A-> B, A is key.
Functional dependency: A -> B: same A leads to same B. E.g.: ID -> name.

4NF: Functional dependency + Multivalued dependency.
Multivalued dependency: A ->> B, C: each A lead to all combo (B x C). E.g.: ID -> region x language
4NF: For each multivalued dependency A ->> B, C, A is key. I.e.: (A, B, C) is decomposed to (A, B) and (A, C).

(2) When there is no data update or deletion. For example: data warehouse situations.
Analytical processing: joining multiple tables is not efficient. The SQL queries are hard to write.

(3)Why is transaction is important:

* Concurrency: Ensure consistent data read/write while providing concurrent data access.
* Failure-tolerance: Resilience to system failures.

A transaction is a sequence of SQL statements treated as a unit. The effect of a transaction is either full or none at all.
Transactions appear to run in isolation.

ACID: atomic, consistency, isolation, durability

(4) You have a foreign key, reference to another table.
When that key is deleted from the other table, referential integrity is compromised. 

(5) Check current state: hang processes, long running queries.
Optimize SQL queries.
Indexes.

(6) What is it: persistent data structure, stored in database.
Purpose: improve data lookup performance. 
Instead of scanning a whole relational table for a record, using the index, the location of a record can be returned almost immediately.
Implementation: Balanced trees (B tree, B+ tree), Hash Map (for equality condition only)

A database index is a data structure that improves the speed of data retrieval operations on a database table at the cost of additional writes and storage space to maintain the index data structure.
An index is a copy of select columns of data from a table that can be searched very efficiently that also includes a low-level disk block address or direct link to the complete row of data it was copied from.

(7) OLTP: Suppose your query is correct, and does not return any duplicates, then including DISTINCT simply forces the RDBMS to check your result (zero benefit, and a lot of additional processing).
Suppose your query is incorrect, and does return duplicates, then including DISTINCT simply hides the problem (again with additional processing). 
It would be better to spot the problem and fix your query. 
It'll run faster that way.