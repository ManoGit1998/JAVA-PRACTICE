# Docker

## Overview
Docker is a platform used to package applications and all of their dependencies into containers so they can run consistently across different environments.

It helps solve the classic problem of “it works on my machine” by creating isolated, portable runtime environments.

## Why Docker Is Used
Docker is used because it provides:
- consistent environments across development, testing, and production
- fast deployment
- lightweight isolation compared to virtual machines
- easy scaling and portability
- simple dependency management

## Key Concepts

### 1. Image
A Docker image is a read-only template used to create containers.

It contains:
- the application code
- runtime environment
- libraries
- configuration

### 2. Container
A container is a runnable instance of an image.

It is lightweight and isolated, but it shares the host operating system kernel.

### 3. Dockerfile
A Dockerfile is a text file that contains instructions to build a Docker image.

Example:
```dockerfile
FROM openjdk:17
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 4. Docker Hub
Docker Hub is a public registry for storing and sharing Docker images.

### 5. Volume
A volume is used to persist or share data between containers and the host system.

## Docker vs Virtual Machine

### Virtual Machine
- heavier
- includes a full guest OS
- slower to start
- uses more resources

### Container
- lighter
- shares host OS kernel
- faster startup
- uses fewer resources

## Docker Workflow
1. Create a Dockerfile
2. Build an image
3. Run a container from the image
4. Push the image to a registry if needed
5. Pull and run it on another machine

## Example: Building and Running a Java App
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . /app
RUN javac Hello.java
CMD ["java", "Hello"]
```

## Common Docker Commands
```bash
docker build -t myapp .
docker run -p 8080:8080 myapp
docker ps
docker images
docker stop <container_id>
docker rm <container_id>
docker rmi <image_id>
```

## Docker Compose
Docker Compose allows you to define and run multi-container applications using a YAML file.

Example:
```yaml
version: '3'
services:
  app:
    build: .
    ports:
      - "8080:8080"
  db:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
```

## Docker Networking
Docker containers can communicate with each other over networks.

Common networking concepts:
- bridge network
- host network
- none network

## Docker Storage
Docker supports:
- bind mounts
- named volumes
- tmpfs mounts

## Best Practices
- keep images small
- use multi-stage builds
- avoid storing secrets in Dockerfile
- use `.dockerignore`
- run only one process per container where possible

## Interview Questions with Answers

### 1. What is Docker?
Docker is a platform used to build, ship, and run applications in containers.

### 2. What is the difference between a container and a virtual machine?
A container shares the host OS kernel and is lighter, while a VM has its own OS and is heavier.

### 3. What is a Dockerfile?
A Dockerfile is a script that defines how to build a Docker image.

### 4. What is Docker Compose?
Docker Compose is a tool for defining and running multi-container applications using a YAML file.

### 5. What is a volume in Docker?
A volume is used to persist or share data between a container and the host system.

### 6. Why is Docker useful in microservices?
Docker makes it easy to package each microservice and run it consistently across environments.

## Summary
Docker is essential in modern software development because it helps package applications in a portable and consistent way. It is especially useful for microservices, cloud deployment, and CI/CD pipelines.
