# SqlTests
A way to add test queries and assertions into SQL scripts.

----
##Background
A few previous works in data mart projects use TestNG and JDBC for data testing. The test SQL queries, defined as Java strings in JUnit/TestNG test cases, are sent to the databases through their respective JDBC interface for execution. The results will be captured in Java tests, and verified by using various JUnit/TestNG assertions. For organizing test SQL queries, there 

using strings in a **properties** file for organizing SQL tests. These strings will be read by TestNG test cases, using key strings, before sending to database for executing with JDBC interface.

The problems of such approach is:

  * SQL test queries are really hard to read in properties file.
    * Each SQL test string must be in a single line. Adding line breaks, such as newline, for clarity is not possible since it will make the test case fail.
   * Unfortunately, it is very common that SQL test queries are long, with multiple JOIN statements, especially in data mart. Having line breaks, e.g. to separate join statements, WILL definitely help readability of test cases.

  * Tests are written in Java, making them inaccessible to developers.
   * Most data engineers (in Data Mart teams) are not familiar with Java and TestNG enough to look for and understand failures in test cases.
   * Most of test queries are expected to return zero row or 0 value. E.g., test query to find all duplicate records which is expected to return zero row. Even simple assertions such as those are encoded with TestNG's library methods.

In writing tests, prioritize **readability** when possible.

  * Readable tests are easier to write, automate, and maintain. Those tests can be easily shared with developers for debugging purposes, especially in SQL.
  * If you write a software, you have tests to validate it. If you write a test, how do you validate it? Only by making readable, you can verify and maintain the test.
  * Readable tests promote collaboration between developers and QEs.
   * If the tests are readable and accessible to developers, they can easily run the tests on their own, without much intervention of QEs.

Note that for testing data mart, JUnit/TestNG is still a powerful framework for defining tests. In many cases, 
