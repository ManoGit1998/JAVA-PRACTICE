# Spring Boot

## Overview
Spring Boot is an extension of the Spring Framework designed to make Java application development faster, easier, and production-ready with minimal configuration.

It removes most of the manual setup required in traditional Spring applications. With Spring Boot, developers can create standalone applications with embedded servers, auto-configuration, and opinionated defaults.

## Why Spring Boot is Used
Spring Boot is popular because it:
- reduces boilerplate configuration
- provides auto-configuration
- supports embedded servers such as Tomcat and Jetty
- makes it easier to create REST APIs
- provides starter dependencies for common features
- simplifies deployment and testing

## Key Features of Spring Boot

### 1. Auto-Configuration
Spring Boot automatically configures application components based on the dependencies present in the classpath.

Example:
- If `spring-web` is present, Spring Boot auto-configures a web application.
- If a database dependency is present, it can configure data source beans automatically.

### 2. Embedded Server
Spring Boot applications can run with an embedded server such as Tomcat, Jetty, or Undertow. There is no need to deploy the application to an external servlet container.

### 3. Starter Dependencies
Starter dependencies are prepackaged dependency sets that help you add common functionality easily.

Examples:
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-test`

### 4. Production-Ready Features
Spring Boot includes features such as:
- health endpoints
- metrics
- application monitoring
- externalized configuration

## Basic Spring Boot Application
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### What `@SpringBootApplication` does
It combines three important annotations:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

## Spring Boot Architecture
A typical Spring Boot application has layers such as:
- Controller layer
- Service layer
- Repository layer
- Entity layer

Example structure:
```text
com.example
  ├── controller
  ├── service
  ├── repository
  ├── entity
  └── Application.java
```

## REST API Development in Spring Boot
Spring Boot is commonly used to build REST APIs.

### Example Controller
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot";
    }
}
```

### Explanation
- `@RestController` tells Spring that this class handles HTTP requests and returns data directly.
- `@GetMapping("/hello")` maps the URL `/hello` to the `hello()` method.

## Common Spring Boot Annotations

### @RestController
Used for REST API controllers.

```java
@RestController
public class UserController {
}
```

### @Controller
Used for MVC web applications where views are rendered.

```java
@Controller
public class HomeController {
}
```

### @RequestMapping
Maps a request URL to a controller method.

```java
@RequestMapping("/users")
public class UserController {
}
```

### @GetMapping
Maps HTTP GET requests.

```java
@GetMapping("/users")
public String getUsers() {
    return "Users";
}
```

### @PostMapping
Maps HTTP POST requests.

```java
@PostMapping("/users")
public String createUser() {
    return "User created";
}
```

### @RequestBody
Used to bind JSON request body to a Java object.

```java
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "User created";
}
```

### @PathVariable
Used to extract values from the URL.

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable int id) {
    return "User id: " + id;
}
```

### @RequestParam
Used to extract query parameters.

```java
@GetMapping("/users")
public String getUserById(@RequestParam int id) {
    return "User id: " + id;
}
```

## Spring Boot Application Properties
Spring Boot allows configuration through `application.properties` or `application.yml`.

### Example application.properties
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=password
```

### Example application.yml
```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: password
```

## Dependency Injection in Spring Boot
Spring Boot uses Spring’s dependency injection mechanism.

```java
@Service
public class UserService {
    public String getMessage() {
        return "Hello";
    }
}

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/message")
    public String message() {
        return userService.getMessage();
    }
}
```

### Why constructor injection is preferred
- better testability
- immutable dependencies
- easier to manage

## Spring Boot with JPA and Database
Spring Boot can connect to databases using Spring Data JPA.

### Example Entity
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
```

### Example Repository
```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

### Example Service
```java
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

## Exception Handling in Spring Boot
Spring Boot allows centralized exception handling using `@ControllerAdvice`.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

## Validation in Spring Boot
Validation helps ensure incoming request data is correct.

```java
public class User {
    @NotNull
    private String name;
}
```

Use `@Valid` in the controller to trigger validation.

```java
@PostMapping("/users")
public String createUser(@Valid @RequestBody User user) {
    return "User created";
}
```

## Spring Boot Testing
Spring Boot provides strong support for testing.

### Example Unit Test
```java
@Test
void testHello() {
    assertEquals("Hello", "Hello");
}
```

### Example Controller Test
```java
@WebMvcTest(HelloController.class)
class HelloControllerTest {
}
```

## Spring Boot Actuator
Actuator provides production-ready endpoints such as health and metrics.

```properties
management.endpoints.web.exposure.include=health,info
```

## Spring Boot vs Spring Framework
### Spring Framework
- requires manual configuration
- more boilerplate
- used when full control is needed

### Spring Boot
- faster development
- auto-configuration
- embedded server
- starter dependencies
- easier deployment

## Interview Questions with Answers

### 1. What is Spring Boot?
Spring Boot is a framework built on top of Spring that simplifies application setup and configuration using auto-configuration and starter dependencies.

### 2. What is the difference between Spring and Spring Boot?
Spring requires more configuration manually, while Spring Boot provides defaults and automatic configuration.

### 3. What is auto-configuration?
Auto-configuration automatically configures beans based on the libraries and dependencies present in the classpath.

### 4. What are starter dependencies?
Starter dependencies are curated dependency bundles that include everything needed for a specific feature such as web or data access.

### 5. What is the use of `@SpringBootApplication`?
It marks the main class and enables auto-configuration, component scanning, and configuration support.

### 6. What is the difference between `@Controller` and `@RestController`?
`@Controller` is used for MVC views, while `@RestController` is used for REST APIs that return data directly.

### 7. What is `@GetMapping`?
It maps HTTP GET requests to a controller method.

### 8. What is `@RequestBody`?
It binds the HTTP request body to a Java object.

## Summary
Spring Boot is a modern, developer-friendly extension of Spring that makes building production-ready applications much easier. It is heavily used for:
- REST APIs
- microservices
- database-backed applications
- enterprise services

If you understand Spring Boot’s core concepts, annotations, dependency injection, REST APIs, and application properties, you will be ready for most Spring Boot interviews.
