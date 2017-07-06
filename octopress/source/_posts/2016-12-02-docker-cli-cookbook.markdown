---
layout: post
title: "Docker CLI cookbook"
date: 2016-12-02 10:20:53 -0700
comments: true
categories: 
- Bash
---

Standard Docker commands.

<!--more-->

### Standard workflow

``` plain Docker commands in workspace

mymac:W_3703511 tdongsi$ pwd
/Users/tdongsi/docker/W_3703511

docker build -t docker.registry.net/tdongsi/jenkins-nodev4-agent:2.85 .
docker run -d --restart=always --entrypoint="java" docker.registry.net/tdongsi/jenkins-nodev4-agent:2.85 -jar /usr/share/jenkins/slave.jar -jnlpUrl http://10.252.78.115/computer/slave/slave-agent.jnlp
docker ps
docker exec -it <ID> bash
docker commit
docker push

```

### Reference

* [Example of modifying Docker image](http://tdongsi.github.io/blog/2017/01/25/docker-root-user-in-a-pod/)