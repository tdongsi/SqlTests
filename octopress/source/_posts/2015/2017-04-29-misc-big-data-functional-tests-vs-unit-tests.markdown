---
layout: post
title: "(Pt. 5) Big Data: Functional tests vs. Unit tests"
date: 2005-12-02 23:47:20 -0700
comments: true
categories: 
- Testing
- SqlRunner
---

In the context of Big Data projects, the differences between functional tests and unit tests can be summarized as follows:

|       | Functional tests      | Unit tests |
| ----------- | ------------ | ------------ |
| Data         | Production-like data | Mock (synthetic) data |
| Environment  | Pre-production. All tables deployed at once. | Local VM. Regular setup/teardown. |
| Coverage     | Passive: Coverage depends on diverse real data. | Active: Mock data created to force corner cases. |
| Example usage | Snapshot testing | Incrementa data update testing |

<br>

It should be noted that functional and unit tests are complementary to each other.
Certain aspects of ETLs can be better verified as functional tests while others of the same ETLs should be verified as unit tests.
For example, as discussed in [this post](/blog/2016/04/10/sql-unit-incremental-data-update/), unit tests are better suited for testing incremental data update in ETL scripts.

On the other hand, for example, an ETL that performs some kind of classification, such as categorizing user types based on some clickstream patterns, should be tested in functional tests.
If there are more than 20 categories, it could become a daunting task to generate and maintain synthetic data for each of those categories.
Furthermore, synthetic data generation requires careful consideration and proper execution to have adequate coverage. 
Otherwise, the synthetic data might not be as diverse as production data and we end up with less corner cases than production data.
Instead, in this particular case, we could use production-like data directly and write test queries in functional tests to check for corner cases for each category.

<!-- Analogy:
Functional tests:
- In Web App, running the web application on webserver, running automated Selenium WebDriver tests to verify the web application from browser.
- In Big Data, running the DDL/DML/ETL scripts to populate the dimension and fact tables in schema, running automated SQL test queries to verify logics between tables.

Unit tests:
- In Web App, using mocking framework to mock out database, test behavior of a class/method using synthetic inputs, especially for corner cases.
- In Big Data, using a local VM, test behavior of a column modified by an ETL script using synthetic data, especially for corner cases.
-->