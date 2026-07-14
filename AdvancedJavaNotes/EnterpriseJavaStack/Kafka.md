# Kafka

## Overview
Apache Kafka is a distributed event streaming platform used to build real-time data pipelines, streaming applications, and messaging systems.

It is designed to handle high-throughput, fault-tolerant, and real-time data movement between systems.

## Why Kafka Is Used
Kafka is used because it provides:
- high throughput
- fault tolerance
- scalability
- real-time event processing
- decoupling between producers and consumers

## Core Concepts

### 1. Producer
A producer sends data to Kafka topics.

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

Producer<String, String> producer = new KafkaProducer<>(props);
producer.send(new ProducerRecord<>("orders-topic", "order-1", "created"));
producer.close();
```

### 2. Consumer
A consumer reads data from Kafka topics.

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "order-group");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList("orders-topic"));
```

### 3. Topic
A topic is a category or stream name to which messages are published.

Example:
```text
orders-topic
payments-topic
user-events-topic
```

### 4. Broker
A broker is a Kafka server that stores and serves data.

### 5. Partition
A partition is a unit of parallelism inside a topic. Each partition stores a subset of the topic’s data.

### 6. Offset
An offset is a unique position of a message within a partition.

## Kafka Architecture
Kafka follows a distributed architecture with:
- producers
- brokers
- consumers
- topics
- partitions
- replication

## Replication
Kafka replicates data across brokers to provide fault tolerance.

## Consumer Groups
Consumers can be grouped so that parallel processing happens across partitions.

Example:
- one consumer group can read from the same topic in parallel
- each partition is consumed by one consumer in a group

## Kafka Use Cases
- event streaming
- log aggregation
- real-time analytics
- messaging systems
- change data capture

## Kafka vs RabbitMQ

### Kafka
- high throughput
- good for event streaming and log processing
- pull-based consumption
- works well for large-scale real-time data

### RabbitMQ
- traditional message broker
- good for task queues and point-to-point messaging
- supports advanced routing

## Zookeeper vs KRaft
Older Kafka versions used ZooKeeper for coordination. Newer versions support KRaft for metadata management.

## Reliability Features
Kafka provides:
- replication
- acknowledgments
- retries
- idempotent producers

## Best Practices
- choose partition counts carefully
- use meaningful topic names
- monitor lag and throughput
- use consumer groups effectively
- avoid too many partitions for small workloads

## Interview Questions with Answers

### 1. What is Kafka?
Kafka is a distributed message streaming platform used for publishing and consuming streams of events.

### 2. What is a topic in Kafka?
A topic is a logical stream name to which producers publish messages and from which consumers read.

### 3. What is a partition?
A partition is a subset of a topic that enables parallelism and scalability.

### 4. What is the difference between a producer and a consumer?
A producer sends data to Kafka, while a consumer reads data from Kafka.

### 5. What is an offset?
An offset is the position of a record within a partition.

### 6. What is a consumer group?
A consumer group is a set of consumers that work together to consume a topic in parallel.

## Summary
Kafka is an essential tool for building real-time event-driven systems. It is widely used in microservices, analytics, logging, and streaming platforms.
