# Java 8+ Concepts for Interviews

## 1. Lambda Expressions
```java
Runnable r = () -> System.out.println("Hello");
new Thread(r).start();
```

## 2. Functional Interfaces
```java
@FunctionalInterface
interface Add {
    int add(int a, int b);
}
```

## 3. Stream API
```java
List<String> names = Arrays.asList("A", "B", "C");
names.stream().filter(n -> n.startsWith("A")).forEach(System.out::println);
```

## 4. Map, Filter, Reduce
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
int sum = numbers.stream().mapToInt(Integer::intValue).sum();
```

## 5. Optional
```java
Optional<String> optional = Optional.ofNullable(null);
System.out.println(optional.orElse("No value"));
```

## 6. Date and Time API
```java
LocalDate today = LocalDate.now();
System.out.println(today);
```

## Interview Points
- Streams are lazy and can be chained.
- `map` transforms data; `filter` selects data; `reduce` combines data.
- `Optional` helps avoid null checks.
