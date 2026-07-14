# Collections and Concurrency

## Collections
- List: ordered, duplicates allowed
- Set: unique elements only
- Map: key-value pairs

### Example
```java
List<String> names = new ArrayList<>();
Set<Integer> numbers = new HashSet<>();
Map<String, Integer> ages = new HashMap<>();
```

## Concurrency
- Thread is the smallest unit of execution.
- `synchronized` prevents race conditions.
- `ExecutorService` is used to manage threads efficiently.

```java
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("Task"));
executor.shutdown();
```

## Interview Questions
- What is the difference between HashMap and ConcurrentHashMap?
- What is the difference between synchronized and volatile?
- What is the difference between wait() and sleep()?
