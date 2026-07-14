# Advanced Core Java Interview Guide

This guide is meant for someone with around 4 years of experience and focuses on practical interview-level Java knowledge.

## 1. Java 8+ Features

### Lambda Expressions
Lambda expressions provide a concise way to represent anonymous functions.

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
nums.forEach(n -> System.out.println(n));
```

### Functional Interfaces
A functional interface has exactly one abstract method.

```java
interface Greeting {
    void sayHello(String name);
}
```

### Streams API
Streams are used for processing collections in a declarative style.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> even = numbers.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
```

### Optional
Optional is used to avoid NullPointerException.

```java
Optional<String> name = Optional.of("Mano");
System.out.println(name.orElse("Unknown"));
```

### Interview points
- Lambda expressions simplify anonymous inner classes.
- Streams are great for filtering, mapping, and reducing collections.
- Optional helps represent the absence of a value safely.

---

## 2. Collections Deep Dive

### ArrayList vs LinkedList
- ArrayList is better for fast random access.
- LinkedList is better for frequent insertions and deletions.

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");
```

### HashMap vs TreeMap vs LinkedHashMap
- HashMap: no order guarantee.
- LinkedHashMap: preserves insertion order.
- TreeMap: sorted by natural ordering or comparator.

### HashSet
HashSet stores unique values.

```java
Set<Integer> set = new HashSet<>();
set.add(1);
set.add(2);
set.add(1);
System.out.println(set.size());
```

### ConcurrentHashMap
Used in multithreaded applications.

```java
Map<String, Integer> map = new ConcurrentHashMap<>();
```

### Comparable vs Comparator
- Comparable defines natural ordering.
- Comparator defines custom ordering.

```java
class Student implements Comparable<Student> {
    int age;

    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age);
    }
}
```

### Interview points
- Understand when to choose List, Set, and Map.
- Be aware of fail-fast vs fail-safe iterators.
- Know the difference between ArrayList and Vector.

---

## 3. Multithreading and Concurrency

### Thread Creation
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
```

```java
Thread t = new MyThread();
t.start();
```

### Runnable Interface
```java
Runnable task = () -> System.out.println("Task running");
Thread thread = new Thread(task);
thread.start();
```

### ExecutorService
```java
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("Task 1"));
executor.submit(() -> System.out.println("Task 2"));
executor.shutdown();
```

### Synchronization
```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }
}
```

### Volatile and AtomicInteger
- Volatile ensures visibility of changes across threads.
- AtomicInteger provides atomic operations.

### Deadlock
A deadlock happens when two or more threads wait forever for each other.

### Interview points
- `start()` creates a new thread; `run()` does not.
- Use `ExecutorService` for better thread management.
- Be ready to explain synchronization and race conditions.

---

## 4. JVM Internals and Memory

### Heap and Stack
- Stack stores method calls and local variables.
- Heap stores objects.

### Garbage Collection
Garbage Collector removes unused objects automatically.

### Common JVM terms
- Young Generation
- Old Generation
- Metaspace
- Minor GC / Major GC

### Interview points
- Understand memory leak concepts.
- Know the difference between strong, soft, weak, and phantom references.

---

## 5. Equals, HashCode, and Immutability

```java
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
```

### Interview points
- If you override `equals()`, override `hashCode()` too.
- Immutable objects are safer in concurrent programming.

---

## 6. Exception Handling in Depth

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Arithmetic issue");
} finally {
    System.out.println("Always executed");
}
```

### Interview points
- Use specific exceptions.
- Avoid catching generic `Exception` unless necessary.
- `finally` is useful for cleanup.

---

## 7. Important Interview Questions for 4+ Years Experience

1. What is the difference between `HashMap` and `ConcurrentHashMap`?
2. What is the difference between `Comparable` and `Comparator`?
3. What is the difference between `synchronized` and `volatile`?
4. What is the difference between `wait()` and `sleep()`?
5. What is the purpose of `Stream` in Java 8?
6. What is the difference between `filter` and `map` in streams?
7. What is a deadlock?
8. What are the different memory areas in JVM?
9. What is the difference between `equals()` and `hashCode()`?
10. What is the difference between `ArrayList` and `Vector`?

---

## 8. Practical Tips for Interview Preparation

- Practice coding without help.
- Be able to explain trade-offs clearly.
- Revise collections and concurrency deeply.
- Be ready to discuss real-world scenarios.
