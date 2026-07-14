# Kafka

## Overview
Apache Kafka is a distributed event streaming platform used for building real-time data pipelines and messaging systems.

## Key Concepts
- Producer
- Consumer
- Topic
- Broker
- Partition
- Offset

## Example
```java
Producer<String, String> producer = new KafkaProducer<>(props);
producer.send(new ProducerRecord<>("topic-name", "key", "value"));
```

## Use Cases
- Messaging
- Event streaming
- Log aggregation
- Real-time analytics

## Interview Questions
1. What is Kafka?
2. What is a topic in Kafka?
3. What is the difference between a producer and a consumer?
4. What is a partition?
5. What is the difference between Kafka and RabbitMQ?
