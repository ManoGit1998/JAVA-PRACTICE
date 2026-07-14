# Microservices

## Overview
Microservices is an architectural style where a large application is divided into small, independently deployable services. Each service focuses on one business capability and can be developed, deployed, and scaled independently.

This approach is commonly used in modern enterprise systems because it enables faster delivery, better scalability, and more flexibility than a monolithic application.

## Why Microservices Are Used
Microservices are used because they help teams:
- deploy services independently
- scale specific services without scaling the whole application
- use different technologies for different services when needed
- improve fault isolation
- release features faster

## Monolithic vs Microservices

### Monolithic Architecture
A monolithic application is built as one single deployable unit.

Advantages:
- simpler to develop initially
- easier to deploy for small applications
- simpler debugging in small systems

Disadvantages:
- hard to scale specific features
- one bug can affect the whole app
- slower release cycles as the system grows

### Microservices Architecture
A microservices architecture breaks the app into many smaller services.

Advantages:
- independent deployment
- independent scaling
- better fault isolation
- easier team ownership

Disadvantages:
- more complex distributed systems
- more network communication
- harder testing and monitoring
- challenges with data consistency

## Core Principles of Microservices

### 1. Single Responsibility
Each service should focus on one business capability.

Example:
- User Service
- Order Service
- Payment Service
- Inventory Service

### 2. Independent Deployment
Each service can be deployed without affecting other services.

### 3. Loose Coupling
Services should interact through well-defined interfaces rather than sharing code directly.

### 4. Autonomous Services
Each service should own its own data and business logic where possible.

## Typical Microservices Communication
Microservices communicate using network-based protocols such as:
- REST APIs
- gRPC
- messaging systems such as Kafka
- asynchronous event-driven communication

### Synchronous Communication
Use REST or gRPC when a service needs an immediate response.

Example:
```text
Client -> Order Service -> Payment Service
```

### Asynchronous Communication
Use messaging systems like Kafka when services need to react to events without waiting for a direct response.

Example:
```text
Order Service publishes an OrderCreated event
Payment Service consumes the event
```

## Service Discovery
In a microservices system, services are often dynamically deployed and scaled. Service discovery helps services find one another.

### Common tools
- Netflix Eureka
- Consul
- Kubernetes Service discovery

## API Gateway
An API Gateway is a single entry point for clients to access multiple services.

### Responsibilities of API Gateway
- routing requests
- authentication and authorization
- rate limiting
- request aggregation
- logging and monitoring

## Circuit Breaker
A circuit breaker prevents repeated calls to a failing service and allows the system to recover gracefully.

### Example pattern
If a service keeps failing, the circuit breaker opens and stops further requests temporarily.

Common tools:
- Resilience4j
- Hystrix

## Load Balancing
Load balancing distributes traffic across multiple instances of a service.

## Distributed Configuration
Microservices often need configuration that can be managed centrally.

Common tools:
- Spring Cloud Config
- Consul
- Vault

## Database Design in Microservices
Each service usually owns its own database to reduce coupling.

### Approaches
- Database per service
- Shared database (not recommended for well-isolated services)

### Challenges
- transaction management across services
- data consistency
- eventual consistency

## Event-Driven Microservices
In event-driven architecture, services communicate through events.

Example:
```text
Order placed -> OrderCreated event -> Inventory updates -> Payment processing
```

This is useful for better scalability and loose coupling.

## Security in Microservices
Security is important because many services are exposed over the network.

Common concerns:
- authentication
- authorization
- token-based security
- service-to-service communication security

Common tools:
- OAuth2
- JWT
- Spring Security

## Monitoring and Logging
Microservices require centralized monitoring and logging because failures can happen across many services.

Common tools:
- Prometheus
- Grafana
- ELK Stack
- Zipkin
- Sleuth

## Observability
Observability means understanding the state of the system by collecting metrics, logs, and traces.

### Three pillars
- logs
- metrics
- traces

## Design Patterns in Microservices
Common patterns include:
- API Gateway
- Service Discovery
- Circuit Breaker
- Bulkhead
- Saga Pattern
- CQRS

### Saga Pattern
A saga manages distributed transactions across multiple services by coordinating a sequence of local transactions.

## Microservices Best Practices
- keep services small
- use clear boundaries
- avoid too much network chatter
- use asynchronous communication where appropriate
- ensure proper monitoring
- use centralized logging
- implement retries and circuit breakers

## Interview Questions with Answers

### 1. What is a microservice?
A microservice is a small, independently deployable service that focuses on one business capability.

### 2. What is the difference between monolithic and microservices architecture?
A monolithic application is built as one unit, whereas microservices split the system into multiple independent services.

### 3. What is API Gateway?
An API Gateway is a single entry point that routes incoming requests to the appropriate backend services.

### 4. What is service discovery?
Service discovery helps services find each other dynamically in a distributed environment.

### 5. What is a circuit breaker?
A circuit breaker prevents repeated calls to a failing service and allows the system to recover gracefully.

### 6. What are the challenges of microservices?
Challenges include distributed system complexity, service communication, monitoring, data consistency, and deployment overhead.

## Summary
Microservices are a modern architecture for building scalable and flexible applications. They offer many benefits, but they also introduce complexity in communication, monitoring, and deployment. A strong understanding of service boundaries, APIs, resilience, and observability is essential for interviews.
