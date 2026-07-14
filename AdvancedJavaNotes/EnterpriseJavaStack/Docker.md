# Docker

## Overview
Docker is a platform used to package applications and their dependencies into containers.

## Key Concepts
- Images
- Containers
- Dockerfile
- Docker Hub
- Volumes

## Example Dockerfile
```dockerfile
FROM openjdk:17
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Common Commands
```bash
docker build -t myapp .
docker run -p 8080:8080 myapp
docker ps
docker images
```

## Interview Questions
1. What is Docker?
2. What is the difference between a container and a virtual machine?
3. What is a Dockerfile?
4. What is the purpose of Docker Compose?
5. What is a volume in Docker?
