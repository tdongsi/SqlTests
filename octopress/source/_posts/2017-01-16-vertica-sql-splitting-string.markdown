---
layout: post
title: "Vertica SQL: Splitting string"
date: 2017-01-16 11:27:12 -0800
comments: true
categories: 
- SQL
- Vertica
---

It is not so straight-forward for splitting strings in SQL, including but not limited to comma-separated strings.

<!--more-->

In Python and Java, splitting delimited strings is straight forward.

``` plain
// Python
"EMS_Tax,PSCare,Tools_Team,,workflow".split()

// Java: using Guava's Splitter

```

As shown in [another blog post](http://tdongsi.github.io/blog/2016/08/17/analytic-functions-in-mysql/), not all SQL dialects are equal.
Different database systems have different ways of doing so in SQL, as shown in the following links (1, 2)

http://stackoverflow.com/questions/2647/how-do-i-split-a-string-so-i-can-access-item-x

http://stackoverflow.com/questions/10581772/how-to-split-a-comma-separated-value-to-columns


This post will throws another into that mess.

``` sql Spitting comma-separated strings
SELECT
label_key,
SPLIT_PART(labels, ',', row_num) AS Label
FROM
(SELECT ROW_NUMBER() OVER () AS row_num FROM tables) row_nums
JOIN label_map i
WHERE SPLIT_PART(labels, ',', row_num) <> '';
```

### Original

``` sql Example data
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (415862,'EMS_Tax,YE2016,getwell,locals_manual,noncritical,ye_taxopsmh');
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (436631,'BBC_Reviewed,SPS_Bugs,Priority_Backlog,need_followup');
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (443409,'EMS_Tax,PSCare,Tools_Team,,workflow');
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (490209,null);
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (490210,'');
```

``` sql String splitting code
SELECT
jmap.dm_jira_key,
SPLIT_PART(labels, ',', row_num) AS Label
FROM
(SELECT ROW_NUMBER() OVER () AS row_num FROM tables) row_nums
CROSS JOIN stg_all_jira_issues_vw i
INNER JOIN helper_jira_key jmap ON i.jira_id = jmap.jira_id AND i.source_system_key = jmap.source_system_key
WHERE SPLIT_PART(labels, ',', row_num) <> '';
```