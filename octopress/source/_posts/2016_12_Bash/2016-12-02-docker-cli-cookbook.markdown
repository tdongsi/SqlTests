---
layout: post
title: "Docker CLI cookbook"
date: 2016-12-02 10:20:53 -0700
comments: true
categories: 
- Bash
---

Standard Docker CLI commands for everyday workflow.

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

``` plain Clean up
# One liner to stop / remove all of Docker containers:
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

# Remove Docker containers that exited
docker rm -v $(docker ps -aq -f status=exited)

# Remove dangling (untagged) images
docker rmi $(docker images -f "dangling=true" -q)
```

### `kubectl` command

`kubectl` is the CLI client for Kubernetes. 
It is very similar to `docker` CLI in many situations (see [this comparison](https://kubernetes.io/docs/reference/kubectl/docker-cli-to-kubectl/)).

``` plain kubectl commands
# List all pods
kubectl get pods -a
# Long format: including node
kubectl get pods -o wide

# List pod name only
kubectl get pods -o name -a | awk -F "/" '{print $2}'

# Delete pod based on some filter (e.g., "java-agent" in name)
kubectl get pods -o name -a | awk -F "/" '{print $2}' | grep java-agent >pods.txt
kubectl delete pod `< pods.txt`
# The back tick is based on
# https://stackoverflow.com/questions/4227994/command-line-arguments-from-a-file-content
```

### Reference

* [Example of modifying Docker image](http://tdongsi.github.io/blog/2017/01/25/docker-root-user-in-a-pod/)
* [docker vs kubectl](https://kubernetes.io/docs/reference/kubectl/docker-cli-to-kubectl/)
