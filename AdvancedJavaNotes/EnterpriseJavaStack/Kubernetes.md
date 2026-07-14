# Kubernetes

## Overview
Kubernetes is an orchestration platform used to manage containerized applications.

## Key Concepts
- Pods
- Deployments
- Services
- ReplicaSets
- ConfigMaps
- Secrets

## Example
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: myapp
        image: myapp:latest
```

## Interview Questions
1. What is Kubernetes?
2. What is the difference between a Pod and a Deployment?
3. What is a Service in Kubernetes?
4. What is the difference between ReplicaSet and Deployment?
5. What is a ConfigMap?
