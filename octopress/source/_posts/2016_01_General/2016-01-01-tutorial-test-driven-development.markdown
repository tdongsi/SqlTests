---
layout: post
title: "Tutorial: Test-Driven Development"
date: 2016-01-01 22:57:00 -0700
comments: true
categories: 
- Tutorial
- Testing
- Python
- Java
- Javascript
---

<!-- Reference:
Evernote: "QE cheat sheet"
-->

Test-driven development has become more and more important. 
As pointed out in [this](/syllabus/), candidates with 3+ years of industry experience should be able to demonstrate testing experience.
You should expect testing questions come up, even when you interview for developer positions.

<!--more-->

### Type of tests

This is useful for the open questions of "How do you test X?" type.
X can be anything that are not related to software. 
It would be awesome if you can come up with a creative test case that interviewers won't think of.
However, you are usually expected to hit broadly and methodically different categories of tests, instead of keeping listing different test cases of a test category.
Knowing these kind of tests can also help you keep talking without running out of ideas.
 
* Unit testing
* Integration testing
* Functional testing
* Load testing
* Stress testing
* Performance testing
* Install/Uninstall testing
* UI testing
* Localization testing (language, market-ready)
* Security testing
* Acceptance testing
* Regression testing

Other general types:

* White-box testing
* Black-box testing
* Beta testing

Questions that I will ask myself if I am the interviewer. 

* Does the candidate ask good questions to understand the feature or he/she just simply making assumptions?  
* Is the candidate covering negative and boundary conditions? 
* Is the candidate trying to break the feature? Any interesting test cases you haven’t thought of?
* Is the candidate covering non-functional tests such as performance, scalability, security, etc.?

Make sure that you pass all those minimum expections: ask clarifying questions and highlight assumptions.

### Example of a testing question

For example, the interviewer will give you a question: "Given a string, reverse it word by word". 
You proceed to solve it and write it on the whiteboard. 
The follow-up question would be "How would you test it?".

(1) General

* "Hello World" => "World Hello"
* "Foo Bar Baz" => "Baz Bar Foo"
* What happens with unicode?
* What about tabs and newlines? Do newlines end up in the beginning or end?
* What about unicode whitespace?
* What about numbers? What about special characters like ! and #.

(2) Boundary

* Null string: null => what happens?
* Empty string: "" => ""
* Really long string => What happens?
* What about a one really really long word?
* What if you just give a string of spaces `"     "` => `"     "`?
* What if you give a really string of letters separated by spaces? `"a a a a a a a a a a a a a a a a a a aa a"`
* Does it handle Chinese well?
* What if there's multiple spaces? Are they respected? `"a a a b ob"`

(3) Exceptional

* What happens when you give a really long string (like from `/dev/random`)?
* How can the function run out of memory?
* What if the type is incorrect? Could happen in Python.
* null => depends on specification. Might not be possible in languages like Haskell.

Four and five are bonus stuff that I like to cover or have been asked of me in the past. 
They generally aren't the first three that I think of when I'm asked to test a function though.

(4) Performance - Running time

I would explain the space complexity I expect out of my solution. 
I would draw a simple graph of what it would probably look like. 
I would then provide a set of sample points that I would take to see the space complexity. 
Some sample points I would take are 0, 1, 5, 10, `100`, `1,000`, `10,000`, `100,000`, `1,000,000` until I hit some barrier and then draw more fine grain values in between. 
Basically, I'd increase the input exponentially and then increase granularity from there.

(5) Performancce - Space complexity

Very similar to (4), I would explain the space complexity I expect out of my solution. 
I would draw a simple graph of what it would probably look like. 
I would then provide a set of sample points that I would take to see the space complexity.
Personally, I would mention that many languages like Java allow you to run at a reduced heap size like 4 MB so that you can hit `OutOfMemory` exceptions very quickly with reasonably sized inputs, if the complexity isn't managed properly.
