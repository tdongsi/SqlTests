---
layout: post
title: "Vertica SQL: Splitting string"
date: 2017-01-16 11:27:12 -0800
comments: true
categories: 
- SQL
- Vertica
---

``` sql Example data
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (415862,'EMS_Tax,YE2016,getwell,locals_manual,noncritical1,ye_taxopsmh');
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (436631,'CCB_Reviewed,PSP_Bugs,PSP_Priority_Backlog,need_followup');
INSERT INTO helper_jira_key (dm_jira_key,labels) VALUES (443409,'EMS_Tax,PXCare,Tools_Team,,workflow');
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