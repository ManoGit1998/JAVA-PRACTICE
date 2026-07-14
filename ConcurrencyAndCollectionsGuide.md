# Concurrency and Collections for Java Interviews

## Collections Basics
- List: ordered, allows duplicates
- Set: unique elements
- Map: key-value pairs

### ArrayList vs LinkedList
```java
List<String> list1 = new ArrayList<>();
List<String> list2 = new LinkedList<>();
```

### HashMap vs ConcurrentHashMap
```java
Map<String, Integer> map = new HashMap<>();
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
```

## Thread Basics
```java
Thread t = new Thread(() -> System.out.println("Running"));
t.start();
```

## Synchronization
```java
class SharedResource {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

## Executor Framework
```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Task"));
executor.shutdown();
```

## Interview Points
- Prefer `ConcurrentHashMap` for multi-threaded environments.
- Use `ExecutorService` instead of manually creating threads.
- Understand race conditions and synchronization.
