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

The usual workflow for modifying a Docker image is as follows:

``` plain Docker commands in workspace
# Build from a Docker file
$ docker build -t docker.registry.net/tdongsi/jenkins-nodev4-agent:2.85 .

# Test run a Jenkins slave docker
$ docker run -d --restart=always --entrypoint="java" docker.registry.net/tdongsi/jenkins-nodev4-agent:5 \
-jar /usr/share/jenkins/slave.jar -jnlpUrl http://10.252.78.115/computer/slave/slave-agent.jnlp

$ docker ps -a
$ docker exec -it <ID> bash

# Copy an installer to Docker image
$ docker cp jdk-8u131-linux-x64.tar.gz bad2dab451bf:/home/jenkins
$ docker exec -it bad2dab451bf bash
jenkins@aqueduct-agent-1c87a4933da26c:~$ ls
jdk-8u131-linux-x64.tar.gz  maven  support  workspace

# Stop and restart
$ docker stop <ID>
$ docker rm <ID>

$ docker commit --author tdongsi --message "Commit message" bad2dab451bf \ 
docker.registry.net/tdongsi/jenkins-nodev4-agent:7

$ docker push docker.registry.net/tdongsi/jenkins-nodev4-agent:7
```

### Reference

* [Example of modifying Docker image](http://tdongsi.github.io/blog/2017/01/25/docker-root-user-in-a-pod/)