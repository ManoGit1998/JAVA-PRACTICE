# Kubernetes

## Overview
Kubernetes is an open-source container orchestration platform used to deploy, scale, and manage containerized applications automatically.

It is widely used in cloud-native systems and helps manage complex environments where many containers need to run reliably.

## Why Kubernetes Is Used
Kubernetes is used because it provides:
- automated deployment and scaling
- self-healing capabilities
- rolling updates and rollbacks
- load balancing
- service discovery
- resource management

## Key Concepts

### 1. Pod
A Pod is the smallest deployable unit in Kubernetes. It usually contains one or more tightly coupled containers.

Example:
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
spec:
  containers:
    - name: myapp
      image: myapp:latest
```

### 2. Deployment
A Deployment manages the creation and updates of Pods.

It is used for declarative updates and scaling.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
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

### 3. Service
A Service provides a stable network endpoint to access a set of Pods.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  selector:
    app: myapp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```

### 4. ReplicaSet
A ReplicaSet ensures a specified number of Pod replicas are running.

Deployments usually manage ReplicaSets internally.

### 5. ConfigMap
A ConfigMap stores non-sensitive configuration data.

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  APP_MODE: production
```

### 6. Secret
A Secret stores sensitive data such as passwords or tokens.

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
stringData:
  DB_PASSWORD: mypassword
```

## Kubernetes Architecture
Kubernetes has a master/worker architecture:
- Master node manages the cluster
- Worker nodes run the application containers

### Main components
- kube-apiserver
- kube-scheduler
- kube-controller-manager
- kubelet
- kube-proxy

## Scaling in Kubernetes
Kubernetes can scale applications horizontally.

Example:
```bash
kubectl scale deployment myapp-deployment --replicas=3
```

## Rolling Updates and Rollbacks
Kubernetes can update deployments gradually without downtime.

```bash
kubectl rollout status deployment/myapp-deployment
kubectl rollout undo deployment/myapp-deployment
```

## Self-Healing
If a Pod fails, Kubernetes restarts it automatically.

## Namespaces
Namespaces help divide resources in a cluster.

```bash
kubectl get namespaces
```

## Ingress
Ingress exposes HTTP and HTTPS routes from outside the cluster to services inside the cluster.

## Helm
Helm is a package manager for Kubernetes that helps deploy applications using charts.

## Best Practices
- use Deployments instead of Pods directly for most workloads
- use readiness and liveness probes
- keep configuration in ConfigMaps and Secrets
- use resource limits and requests
- use namespaces for separation

## Interview Questions with Answers

### 1. What is Kubernetes?
Kubernetes is a container orchestration platform used to automate deployment, scaling, and management of containerized applications.

### 2. What is the difference between a Pod and a Deployment?
A Pod is the basic unit that runs containers, while a Deployment manages the lifecycle and scaling of Pods.

### 3. What is a Service in Kubernetes?
A Service exposes a stable network endpoint to access Pods.

### 4. What is the difference between ReplicaSet and Deployment?
A Deployment manages ReplicaSets and provides rolling updates, while ReplicaSet ensures a specific number of pod replicas.

### 5. What is a ConfigMap?
A ConfigMap stores non-sensitive configuration data for applications.

### 6. What is a Secret?
A Secret stores sensitive data like passwords and API keys.

## Summary
Kubernetes is essential for managing modern cloud-native applications. It provides automation, scalability, resilience, and easy management of containers in production environments.
