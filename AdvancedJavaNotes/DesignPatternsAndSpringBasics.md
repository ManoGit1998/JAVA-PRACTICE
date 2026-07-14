# Design Patterns and Spring Basics

## Design Patterns
Common design patterns include:
- Singleton
- Factory
- Builder
- Adapter

### Singleton Example
```java
class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

## Spring Basics
Spring is used for building enterprise Java applications.

### Important concepts
- Dependency Injection
- Inversion of Control
- Spring Boot
- REST Controllers

## Interview Questions
- What is Dependency Injection?
- What is the difference between Spring and Spring Boot?
- What is a Singleton pattern?
