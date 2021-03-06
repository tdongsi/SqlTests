---
layout: post
title: "System Design questions"
date: 2017-07-01 02:13:01 -0700
comments: true
categories: 
- Tutorial
- SystemDesign
- Questions
---

How to practice for System Design questions and some design questions.

<!--more-->

### Readings

For Web Services, read "Architecting in AWS": recognize scalability problems that AWS services trying to address and replace, for example, "AWS Load Balancer" with generic load balancer.

Read these to know the broad topics that are expected.

1. [Quora question](https://www.quora.com/What-system-design-distributed-systems-+-scalability-topics-should-I-study-in-order-to-adequately-prepared-for-a-Google-Software-Engineer-interview)
2. [The Technical Design Interview - A Guide to Success](https://www.linkedin.com/pulse/technical-design-interview-guide-success-joey-addona)
3. [Test Design & Architecture Interview - Tips to success](https://www.linkedin.com/pulse/test-design-architecture-interview-tips-success-kane-ho)

### Questions

1. Design a simple file system using OO programming. Just folder and files.
1. How to design a load balancer?
2. How to design Facebook News Feed?

### Answers

(1) Design a simple file system using OO programming. Just folder and files.

Use Composite pattern.

``` java
class FileNode {
  String name;
}

class File extends FileNode {
  long size;
}

class Folder extends FileNode {
  Collection<FileNode> children;
}
```

(2) How to design a load balancer?

Simple: hash and assign random. What are pros and cons?

(3) Design Facebook News Feed.
From [here](https://www.reddit.com/r/cscareerquestions/comments/4ytbz3/design_facebook_news_feed_my_solution_to_the/):

First some numbers to get the scale of the problem:

* number of users: 10^9
* number of users during a peak hour (upper bound): 10^8
* number of posts during a peak hour: 10^6
* number of other activities during a peak hour (likes, comments, saves): 10^10
* almost all users have less than 10^3 friends

The News Feed is constructed mainly based on the activity of user's important Facebook friends. 
An important friend is a user who is my friend and I have interacted with him/her at least somewhat during recent months. 
Interaction might include liking his/her comment, commenting on their post, chatting together, being marked on the same photo, etc. 
We assume the backend maintains the list of important friends. 
It might be updated perhaps every 60 minutes.
This ordering might be quite fuzzy. 
Random perturbations of this ordering might lead to users being able to rediscover friends who they have mostly stopped interacting with. 
The primary purpose of distinguishing important friends is to make the feed more interesting. 
Another advantage is reduction of hotspots: there might be people with >10^4 friends, but we assume that every Facebook user has at most 10^2 important friends.

**Minimum Viable Product**: The Facebook News Feed of each user is a merge of the recent posts made by all his/her important friends sorted by the score of the post. 
The score of the post is ideally proportional to how interesting the post is to our user. 
The score depends on: how old the post is (older posts are less interesting), how many likes the post received, how many likes the post received from user's important friends or friends, ...

We primarily care about delivering an interesting News Feed. On the other hand, we don't really care about being able to produce an infinitely long News Feed. If our infrastructure implies that the feed is limited to 10^00 items and the user cannot scroll further. There are very few legitimate uses for having infinitely scrollable feed.

Overview of the infrastructure:

* Firewall
* Load balancer
* Front-end web servers
* Memcache servers or Redis servers or something similar
* Distributed database servers

Let's see what happens when the user requests his News Feed:

* The request is specified by: the id of user whose feed we're displaying and the number N of requested posts.
* The load balancer redirects the request to one of the web servers. It also decrypts the request. Within the datacenter, we only use unencrypted connections. To do the above, the load balancer keeps statistics of the numbers of requests each server is handling. Possibly, the load balancer might decide to start up a new server or schedule a shutdown of a server to save power. The web server checks if the user is authenticated. This is done by retrieving user's session data from a Google BigTable. If not logged in, s/he is redirected to the login page. If N is too large and not coming from a verified computer (like Facebook's API server), we reject the request and log information about a suspicious request.
* The web server queries memcache for the list of important friends of the user in question.
* Almost always, memcache will have this list ready in memory for all users who are currently logged in (after login, we immediately prefetch this data into memcache). The peak number of users is 10^8, the number of important users at most 10^2, each user is represented by an 8 byte identifier. This is an upper bound of 10^11 bytes, which is 100 GB of memory. Even with significant overhead, room for growth and a safety factor for situations when an unexpectedly large number of users logs in at the same time (e.g., when presidential election results are announced), this can still be stored in operating memory of a single server. A distributed memcache implementation is not going to have a problem here. For each important friend, the web server sends a request for this friend's Activity List. Activity List is a list of his posts, likes, comments, uploaded photos, instances of being marked on a photo, etc. Each item contains a timestamp (32 bits), item type (post, comment, share, ...), id of the item (e.g., the id of the post or comment), the destination id (for example, the id of the post on which the comment was made) and privacy setting (1 byte). This is 22 bytes in total. Only identifiers are stored. The data are populated at the end of the computation.
* These lists are stored in a distributed database hidden behind caching servers. There is 10^9 users, each has at most 10^3 items in their Activity List. This is 22 * 10^12 = 22 TB of memory. ** It is certainly possible to store this in a distributed file system. **A distributed memcache on 128 servers each with 250 GB RAM would also handle this. Therefore, we can assume all these lists are almost always in memory.
* Each item in each of these lists is assigned Relevancy Value. This depends on: The importance of the friend from whose Activity List the item is. How recent the item is. The number of likes and shares the item has (this only makes sense in the case of posts or photos). The number of likes from the user's other important friends. Surely, a like from 3 of my friends is more important than 10 likes from random strangers. We can access this information since we have retrieved the Activity Lists of every single important friend of the user. Since these lists include their likes and the ids of liked posts, we can specifically compute the numbers from the retrieved data. The user's prefered content type. Some users might like photos more than text. We remember this for each user and adjust the weight accordingly. Private posts that should not be visible to the user are removed at this point.
* We sort each of these lists using the Relevancy Value and merge them.
* We send this list to a content service. This service replaces all identifiers with the corresponding content (text, image links, names of users instead of user ids, ...)
* The web server uses a template to convert this into HTML.

There are two questions to ponder:

* When the News Feed is requested again in the future, do we recalculate it from scratch? We could store the sequence calculated in one of the last steps in cache and only compute the beginning of the feed (that is: we would only compute what is new). This might make almost-infinite scrolling possible in certain cases. However, we would need to handle some corner cases. For example, the list is only approximately ordered according to the timestamp.
* The above described the read path. It remains to analyze the write path: what happens when a user submits a content, likes something, etc. Well, we simply add this to his Activity List. In the case of posts, images, ... we also store it on a content service server.
