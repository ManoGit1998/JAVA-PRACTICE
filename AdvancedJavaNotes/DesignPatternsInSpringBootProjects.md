# Design Patterns in Spring Boot Projects

This guide shows how common design patterns are used in real Spring Boot applications and how they fit into backend architecture.

---

## 1. Why Design Patterns Matter in Spring Boot
Spring Boot applications often grow into large systems with:
- controllers
- services
- repositories
- validations
- external API integrations
- notifications
- asynchronous processing

Design patterns help make such applications:
- easier to maintain
- easier to extend
- easier to test
- less tightly coupled

---

## 2. Spring Boot Project Structure Example
A typical Spring Boot project may look like this:
```text
com.example.app
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
└── util
```

In such projects, design patterns are applied across layers.

---

## 3. Singleton Pattern in Spring Boot
### Where it is used
- configuration classes
- logging utilities
- shared services
- application-wide settings

### Spring Boot Example
Spring itself manages beans as singletons by default.

```java
@Service
public class ConfigService {
    public String getAppName() {
        return "My Spring Boot App";
    }
}
```

### Why this is relevant
In Spring, beans are usually singleton-scoped by default, so one shared instance is created and reused.

---

## 4. Factory Pattern in Spring Boot
### Where it is used
- creating different payment strategies
- creating notification implementations
- creating different message senders

### Example: Notification Factory
```java
public interface NotificationService {
    void send(String message);
}

@Component
public class EmailNotificationService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

@Component
public class SmsNotificationService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

@Component
public class NotificationFactory {
    private final EmailNotificationService emailNotificationService;
    private final SmsNotificationService smsNotificationService;

    public NotificationFactory(EmailNotificationService emailNotificationService,
                               SmsNotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    public NotificationService getNotificationService(String type) {
        if (type.equalsIgnoreCase("email")) {
            return emailNotificationService;
        } else if (type.equalsIgnoreCase("sms")) {
            return smsNotificationService;
        }
        throw new IllegalArgumentException("Unsupported type");
    }
}
```

### Why it is useful
The controller or service does not need to know the exact implementation class. It simply asks the factory for the right service.

---

## 5. Builder Pattern in Spring Boot
### Where it is used
- building request DTOs
- building complex response objects
- building API query objects

### Example: User DTO Builder
```java
public class UserDto {
    private final String name;
    private final String email;
    private final Integer age;

    private UserDto(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
    }

    public static class Builder {
        private String name;
        private String email;
        private Integer age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
```

### Why it is useful
It keeps object creation readable and avoids a long constructor with many parameters.

---

## 6. Strategy Pattern in Spring Boot
### Where it is used
- payment methods
- shipping methods
- discount calculation
- notification channels

### Example: Payment Strategy
```java
public interface PaymentStrategy {
    void pay(double amount);
}

@Component
public class CardPaymentStrategy implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid using card: " + amount);
    }
}

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid using PayPal: " + amount);
    }
}

@Service
public class CheckoutService {
    private final PaymentStrategy paymentStrategy;

    public CheckoutService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

### Why it is useful
The checkout logic remains same, while the payment behavior can change at runtime.

---

## 7. Adapter Pattern in Spring Boot
### Where it is used
- integrating third-party libraries
- adapting external APIs
- wrapping old modules with new interfaces

### Example: External SMS Client Adapter
```java
public interface SmsSender {
    void sendSms(String number, String message);
}

public class ExternalSmsClient {
    public void send(String phone, String body) {
        System.out.println("Sending SMS to " + phone + " with body " + body);
    }
}

@Component
public class SmsSenderAdapter implements SmsSender {
    private final ExternalSmsClient externalSmsClient = new ExternalSmsClient();

    public void sendSms(String number, String message) {
        externalSmsClient.send(number, message);
    }
}
```

### Why it is useful
It allows the system to work with existing third-party code without major refactoring.

---

## 8. Decorator Pattern in Spring Boot
### Where it is used
- adding logging to services
- adding caching behavior
- adding authorization or validation wrappers

### Example: Logging Decorator
```java
public interface UserService {
    void createUser();
}

@Component
public class DefaultUserService implements UserService {
    public void createUser() {
        System.out.println("User created");
    }
}

@Component
public class LoggingUserService implements UserService {
    private final DefaultUserService defaultUserService;

    public LoggingUserService(DefaultUserService defaultUserService) {
        this.defaultUserService = defaultUserService;
    }

    public void createUser() {
        System.out.println("Logging before create");
        defaultUserService.createUser();
        System.out.println("Logging after create");
    }
}
```

### Why it is useful
It allows you to add behavior without changing the original service logic.

---

## 9. Facade Pattern in Spring Boot
### Where it is used
- simplifying a complex service layer
- combining many external calls into one operation
- checkout service

### Example: OrderFacade
```java
@Service
public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;

    public OrderFacade(InventoryService inventoryService,
                       PaymentService paymentService,
                       ShippingService shippingService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
    }

    public void placeOrder() {
        inventoryService.checkStock();
        paymentService.processPayment();
        shippingService.shipOrder();
    }
}
```

### Why it is useful
The controller only needs to call one method and does not need to manage multiple services manually.

---

## 10. Observer Pattern in Spring Boot
### Where it is used
- event-driven systems
- notifications
- order status updates
- audit logging

### Example: Event Publisher and Listener
```java
@Component
public class OrderCreatedPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishOrderCreatedEvent(String message) {
        publisher.publishEvent(message);
    }
}

@Component
public class OrderCreatedListener {
    @EventListener
    public void handleOrderCreatedEvent(String message) {
        System.out.println("Event received: " + message);
    }
}
```

### Why it is useful
It enables loosely coupled event-based communication between components.

---

## 11. Command Pattern in Spring Boot
### Where it is used
- task execution frameworks
- workflow engines
- undo/redo systems
- command-based APIs

### Example: Command Interface
```java
public interface Command {
    void execute();
}

@Component
public class SendEmailCommand implements Command {
    public void execute() {
        System.out.println("Email sent");
    }
}
```

### Why it is useful
It allows actions to be wrapped as objects and executed later or dynamically.

---

## 12. Template Method Pattern in Spring Boot
### Where it is used
- workflow processing
- validation pipelines
- service execution steps

### Example: Payment Processing Template
```java
public abstract class PaymentWorkflow {
    public final void process() {
        validate();
        charge();
        confirm();
    }

    public void validate() {
        System.out.println("Validating payment");
    }

    public abstract void charge();

    public void confirm() {
        System.out.println("Confirming payment");
    }
}
```

### Why it is useful
It provides a common workflow while allowing the actual business behavior to vary.

---

## 13. Common Pattern Usage in Spring Boot Projects

| Scenario | Pattern |
|---|---|
| One shared application-wide bean | Singleton |
| Different notification channels | Factory |
| Building complex DTOs | Builder |
| Integrating external API | Adapter |
| Adding logging/cache behavior | Decorator |
| Simplifying multiple service calls | Facade |
| Switching payment/shipping strategies | Strategy |
| Event-driven communication | Observer |
| Workflow execution | Command / Template Method |

---

## 14. Interview Questions for Spring Boot + Design Patterns
1. How is Singleton used in Spring Boot?
2. What is the difference between Factory and Strategy patterns?
3. Where would you use Builder in a Spring Boot project?
4. How does Observer pattern fit in event-driven Spring Boot applications?
5. How is Facade useful in a large Spring Boot service?
6. What is the difference between Adapter and Decorator?

---

## 15. Final Takeaway
In Spring Boot projects, design patterns are used to make services cleaner and more maintainable. The most important patterns for real-world backend development are:
- Singleton
- Factory
- Builder
- Strategy
- Adapter
- Decorator
- Facade
- Observer
