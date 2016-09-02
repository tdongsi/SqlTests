---
layout: post
title: "Tutorial: Dimensional Modelling"
date: 2016-09-15 15:38:20 -0700
comments: true
categories: 
- Tutorial
- Database
- TODO
- Facebook
---

From Kimball group reader.

<!--more-->

Design items:
You need to do data profiling to keep data processed to min. One way to profile data changes is to use CDC column.
Check CDC columns: e.g. last_update_ts. If CDC columns are not available, work with production system DBA to add one.
Otherwise, check application log file/Message traffic.

Item 1.5
Divide data into dimension and fact tables: 90% of the time the division is obvious.

	* Dimensions: static entities in the environment
	* 
		* Text descriptions are obvious data going into dimension

	* Facts: numeric observations/measurements.
	* 
		* Unpredictable, numeric numbers are the usual suspects.



Grain of fact table = a measurement in physical, real-world.

Design steps:

	* Determine the single physical event you want to record -> fact table. Other details will follow in dimension tables.
	* 
		* What event is a single row in fact table representing? E.g. for fact_sale_event, the grain is literally the beep of the scanner.

	* Strive to make facts additive.
	* 
		* E.g.: Sale event can go into fact table as (price, unit), but the information (sale amount, unit) contains the same information but better since sale amount (aka extended price) = price * unit.

	* Some data can be in both. The goal is ease of use, not methodology correctness.
	* 
		* E.g.: Coverage amount of insurance policies can be in both dim_coverage and fact_sale_event. 



Item 1.6
Bus matrix to communicate/manage dimension tables.

TODO: Table of bus matrix

Item 1.8 Slow Changing Dimensions
Type 0: Constant. Ignore changes.
Type 1: Simple overwrite (best used for error correction).
Type 2: Create another row and save history.
The standard implementation is: surrogate key (PK), durable ID, … attributes …, effective_start_date, effective_end_date, change_reason, current_flag.

Type 3: Create another column for alternate info.

Item 1.10. Fact tables
Data warehouse is built on fact tables expressed at the lowest possible grain.
Higher grain aggregated tables such as category sales by district.
Three kinds of fact tables:

	1. Transaction Grain: corresponds to a measurement taken at a single instant.
		1. Unpredictably sparse or dense.
		2. Can be enormous. Cannot guarantee all possible foreign keys represented.
		3. E.g.: fact_qbo_subscription_event

	2. Periodic Snapshot Grain: corresponds to a predefined span of time.
		1. Predictably dense.
		2. Can be large even there is no activity.
		3. E.g.: Account balance for an account at some time.

	3. Accumulating Snapshot Grain.
		1. Fact entries are overwritten and udpated.
		2. E.g.: Order processing



Surrogate Keys (integer key, assigned in sequence) are recommended for Fact tables.
In Vertica, CREATE SEQUENCE.

Item 4.1
Too smart interviewers make it harder to extract requirements from business:

	* Long-winded questions
	* Even worse, some questions box the interviewee into a corner because of some bias. And the interviewees do not know how to get out.
	* 
		* Just ask and listen. Let them guide you step by step.



Item 5.1-5.3: Compare normalized modeling (3NF) vs dimensional modeling (DM) 
Why dimensional modeling over normalized modeling?

	1. Normalized modeling is intended for transactional databases, making update and delete efficient. It’s not needed in BI/DW.
	2. Normalized modeling for a complex business process will result in a very large ER diagram (similar to US cities-freeway maps). Business users cannot simply use that diagram to query what they need to know.
		1. The result ER is usually overwhelming and cannot be viewed in its entirety.
		2. E.g.: How to drive from SJ to NY? Maybe going to Sacramento through 580, then to Salt Lake City, and then what? Similarly, you need to know which table to join.
		3. In the same analogy, it’s actually worse to join the tables since the tables are not static, they are moving cities.



Dimensional Modeling: top-down design process.

	* each fact table represents a business process.
	* Support two operations: browse and multi-table joins.
	* It is important to keep the dimension tables flat, without being normalized into snowflake structure.

### Questions

http://learndatamodeling.com/blog/data-modeling-interview-questions/

