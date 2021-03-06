---
layout: post
title: "Network Question bank"
date: 2019-09-01 01:45:07 -0700
comments: true
categories: 
- Questions
- Network
---

Brush up on networking theory, including familiarity with major components of the IP suite.

<!--more-->

### Review Syllabus

See [main syllabus](/syllabus/).

### Questions

1. TCP/UDP and how they're used.
2. Difference between IPv4 & IPv6.
3. How DNS works.
4. HTTP and it's various versions.
5. Basic understanding of SSL/TLS.
6. Other application protocols (FTP, IMAP, SSH etc.)
7. What is REST and how it's used.
8. Three-way handshake in TCP.

On the practical side, make sure you're very comfortable with the networking stack of your language or framework of choice.

1. Make calls to an external web service.
2. Handle errors and different response codes.
3. Be able to serialize/deserialize data.
4. Know async concepts.
5. Accept user inputs and handle XSS attacks.
1. Web - how do you keep information in a web application - cookie vs session
2. What is GET and POST - differences
3. Difference between PUT and POST.

### Answers

#### Section A

(1) See more [here](http://www.howtogeek.com/190014/htg-explains-what-is-the-difference-between-tcp-and-udp/).
Both TCP and UDP are protocols used for sending packets over Internet.
Built on top of Internet Protocol (IP). The packets are sent to an IP address.

TCP: Transmission Control Protocol

1. Two ways: the remote system sends packets back to acknowledge it’s received your packets.
2. TCP guarantees the recipient will receive the packets in order by numbering them.
3. TCP is about reliability: if ACK is not received, the sender will send packets again.
4. Example usage: file download, web.

UDP: User Datagram Protocol

1. Just send the packets to receiver. No waiting for ACK.
2. No guarantee: if receivers don’t receive it, too bad.
3. No error checking for better performance.
4. Example usage: game, live video stream.

(8) 
Phase 1: Client sends SYN=M.
Phase 2: Server sends SYN=N, ACK=M+1.
Phase 3: Client sends ACK=N+1.
Now connection is established.

#### Section B

(3) See more [here](http://stackoverflow.com/questions/107390/whats-the-difference-between-a-post-and-a-put-http-request). 

PUT is idempotent: multiple PUT requests will have the same effect. POST is not.
To give examples of REST-style resources:
"POST /books" with a bunch of book information might create a new book, and respond with the new URL identifying that book: "/books/5".
"PUT /books/5" would have to either create a new book with the id of 5, or replace the existing book with ID 5.

In non-resource style, POST can be used for just about anything that has a side effect. One other difference is that PUT should be idempotent - multiple PUTs of the same data to the same URL should be fine, wheras multiple POSTs might create multiple objects or whatever it is your POST action does.

