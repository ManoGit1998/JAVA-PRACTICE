# Spring Framework

## Overview
Spring Framework is one of the most widely used Java frameworks for building enterprise applications. It provides infrastructure support so developers can focus on business logic rather than low-level boilerplate such as object creation, dependency management, transaction handling, and configuration.

Spring is based on the principles of Dependency Injection (DI) and Inversion of Control (IoC). These concepts help make applications modular, testable, and easier to maintain.

## Why Spring is Used
Spring is used because it:
- reduces boilerplate code
- improves testability
- supports loose coupling
- promotes modular design
- provides support for transactions, security, and web development
- integrates easily with other frameworks

## Core Concepts of Spring

### 1. Inversion of Control (IoC)
IoC is a design principle where the control of object creation and management is transferred from the application code to a framework.

Instead of the class creating its own dependencies manually, Spring creates and injects them.

Example without Spring:
```java
class OrderService {
    private final PaymentService paymentService = new PaymentService();
}
```

This creates tight coupling because `OrderService` is directly responsible for creating `PaymentService`.

Example with Spring-style DI:
```java
class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Here, the dependency is passed from outside, which is more flexible.

### 2. Dependency Injection (DI)
Dependency Injection means providing an object’s dependencies from outside rather than letting the object create them itself.

There are three common types of DI:
- Constructor injection
- Setter injection
- Field injection

#### Constructor Injection
```java
class PaymentService {
    public void pay() {
        System.out.println("Payment processed");
    }
}

class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.pay();
    }
}
```

Constructor injection is preferred because it ensures required dependencies are available and makes the object immutable.

#### Setter Injection
```java
class OrderService {
    private PaymentService paymentService;

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

Setter injection is useful when optional dependencies are involved.

#### Field Injection
```java
class OrderService {
    @Autowired
    private PaymentService paymentService;
}
```

Field injection is simple but less preferred in modern Spring code because it makes testing and immutability harder.

## Spring Beans
A bean is an object managed by the Spring IoC container.

Spring creates and manages beans according to configuration. A bean can be a service, repository, controller, or component.

### Example Bean
```java
@Component
class UserService {
    public void print() {
        System.out.println("User service bean created");
    }
}
```

The `@Component` annotation tells Spring to create a bean for this class.

## Spring Container
The Spring container is the core part of Spring that creates, configures, and manages beans.

### Types of containers
- `BeanFactory`: basic container, lightweight
- `ApplicationContext`: advanced container with more features like event propagation, internationalization, and easier integration

### Difference between BeanFactory and ApplicationContext
- `BeanFactory` is lighter and provides basic DI support.
- `ApplicationContext` is more feature-rich and is commonly used in real applications.

## Spring Annotations
Spring provides annotations to simplify configuration.

### @Component
Marks a class as a Spring-managed component.

```java
@Component
class EmailService {
    public void sendEmail() {
        System.out.println("Email sent");
    }
}
```

### @Service
Used for service-layer classes.

```java
@Service
class OrderService {
    public void placeOrder() {
        System.out.println("Order placed");
    }
}
```

### @Repository
Used for data access layer classes.

```java
@Repository
class UserRepository {
    public void save() {
        System.out.println("User saved");
    }
}
```

### @Controller and @RestController
Used for web layer classes.

```java
@Controller
class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
```

`@RestController` is used when methods return JSON or plain text directly.

### @Autowired
Used for automatic dependency injection.

```java
@Service
class PaymentService {
    public void process() {
        System.out.println("Processing payment");
    }
}

@Service
class OrderService {
    @Autowired
    private PaymentService paymentService;

    public void placeOrder() {
        paymentService.process();
    }
}
```

## Bean Scopes
Bean scope defines the lifecycle and visibility of a bean.

### Common scopes
- Singleton: one instance per Spring container
- Prototype: a new instance each time it is requested
- Request: one instance per HTTP request
- Session: one instance per HTTP session

### Example of Singleton Scope
```java
@Component
class SingletonBean {
}
```

By default, Spring beans are singleton scoped.

### Prototype Scope
```java
@Component
@Scope("prototype")
class PrototypeBean {
}
```

Prototype beans create a new instance whenever requested.

## Spring Configuration
Spring can be configured using:
- XML configuration
- Java-based configuration
- Annotation-based configuration

### Java Configuration Example
```java
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }
}
```

### Annotation-based Configuration
```java
@Configuration
@ComponentScan("com.example")
public class AppConfig {
}
```

`@ComponentScan` tells Spring where to look for components.

## AOP in Spring
Aspect-Oriented Programming (AOP) allows cross-cutting concerns such as logging, security, and transaction management to be implemented separately.

### Example
```java
@Aspect
@Component
class LoggingAspect {

    @Before("execution(* com.example.*.*(..))")
    public void logBefore() {
        System.out.println("Method called");
    }
}
```

AOP helps avoid repeated code in multiple classes.

## Spring JDBC
Spring provides support for database access with JDBC templates.

```java
JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
String sql = "SELECT * FROM users";
jdbcTemplate.queryForList(sql);
```

This reduces boilerplate code compared to plain JDBC.

## Spring Transactions
Spring provides transaction management using annotations such as `@Transactional`.

```java
@Service
public class AccountService {

    @Transactional
    public void transferMoney() {
        // database operations
    }
}
```

Transactions ensure atomicity and consistency in database operations.

## Spring MVC
Spring MVC is used for building web applications.

### Controller Example
```java
@Controller
public class HomeController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring MVC";
    }
}
```

This returns a response directly to the client.

## Spring Boot vs Spring Framework
Spring Boot is built on top of the Spring Framework. It simplifies configuration and provides default settings.

### Difference
- Spring requires more configuration manually.
- Spring Boot provides auto-configuration and starter dependencies.

## Best Practices in Spring
- Prefer constructor injection over field injection.
- Use interfaces for loose coupling.
- Keep classes focused on a single responsibility.
- Use `@Service`, `@Repository`, and `@Controller` appropriately.
- Avoid creating business logic inside controllers.
- Use transactions only where needed.

## Interview Questions with Answers

### 1. What is Spring Framework?
Spring Framework is a Java framework used to build enterprise applications with features like DI, AOP, transaction management, and MVC.

### 2. What is Dependency Injection?
Dependency Injection is the process of supplying object dependencies from outside instead of creating them inside the class.

### 3. What is Inversion of Control?
IoC is the design principle where the framework manages object creation and lifecycle instead of the application manually doing it.

### 4. What is a Spring Bean?
A Spring bean is an object managed by the Spring container.

### 5. What is the difference between BeanFactory and ApplicationContext?
`BeanFactory` is a basic container while `ApplicationContext` is a more advanced container with extra features like event handling and internationalization.

### 6. What is the difference between singleton and prototype scope?
Singleton creates one shared bean instance per container; prototype creates a new instance each time it is requested.

### 7. What is the use of `@Autowired`?
It injects the required bean automatically into a field, constructor, or setter.

### 8. What is AOP?
AOP allows cross-cutting concerns such as logging and security to be applied across multiple classes in a modular way.

## Summary
Spring is a foundational framework for Java enterprise development. Its main strengths are:
- dependency injection
- modular architecture
- AOP support
- transaction management
- MVC and web support
- easy testing and maintainability

If you understand these concepts deeply, you will be well prepared for Spring-related interviews.
