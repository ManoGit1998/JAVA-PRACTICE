# Java 8+ and Advanced Features

## Lambda Expressions
Lambda expressions provide a shorter way to write anonymous functions.

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
nums.forEach(n -> System.out.println(n));
```

## Stream API
Streams help process collections in a functional way.

```java
List<Integer> even = nums.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
```

## Optional
Optional helps avoid null pointer exceptions.

```java
Optional<String> name = Optional.ofNullable(null);
System.out.println(name.orElse("Unknown"));
```

## Interview Questions
- What is a lambda expression?
- What is the difference between map and filter in streams?
- What is Optional used for?
