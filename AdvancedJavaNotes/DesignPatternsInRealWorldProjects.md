# Design Patterns in Real-World Java Projects

This guide shows how common design patterns are used in real-world software projects and how they are implemented in Java.

---

## 1. E-Commerce Application Example
A real-world e-commerce system usually has modules like:
- user management
- product catalog
- cart and checkout
- payment processing
- order management

Different patterns are used in different modules.

---

## 2. Singleton Pattern in Real World
### Where it is used
- logging service
- configuration class
- database connection manager

### Example: Configuration Manager
```java
class ConfigManager {
    private static ConfigManager instance;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getAppName() {
        return "E-Commerce App";
    }
}
```

### Why it is useful
It ensures that all parts of the application use the same configuration object.

---

## 3. Factory Pattern in Real World
### Where it is used
- creating payment methods
- creating notification services
- creating different types of vehicles or products

### Example: Payment Factory
```java
interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
}

class PayPalPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}

class PaymentFactory {
    public PaymentMethod getPaymentMethod(String type) {
        if (type.equalsIgnoreCase("credit")) {
            return new CreditCardPayment();
        } else if (type.equalsIgnoreCase("paypal")) {
            return new PayPalPayment();
        }
        throw new IllegalArgumentException("Unknown payment type");
    }
}
```

### Why it is useful
The checkout system can create different payment methods without tightly coupling to specific classes.

---

## 4. Builder Pattern in Real World
### Where it is used
- building HTTP requests
- building complex objects like orders, users, and API requests
- building database queries

### Example: Order Builder
```java
class Order {
    private final String customerName;
    private final String product;
    private final int quantity;
    private final double price;

    private Order(Builder builder) {
        this.customerName = builder.customerName;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    public static class Builder {
        private String customerName;
        private String product;
        private int quantity;
        private double price;

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder product(String product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
```

### Why it is useful
It makes object creation readable and avoids a huge constructor with many parameters.

---

## 5. Adapter Pattern in Real World
### Where it is used
- integrating old legacy code with new systems
- integrating third-party APIs
- adapting payment gateways

### Example: Legacy Payment Gateway Adapter
```java
interface NewPaymentGateway {
    void processPayment(double amount);
}

class LegacyPaymentSystem {
    public void makePayment(double amount) {
        System.out.println("Legacy payment of " + amount);
    }
}

class PaymentGatewayAdapter implements NewPaymentGateway {
    private LegacyPaymentSystem legacyPaymentSystem;

    public PaymentGatewayAdapter(LegacyPaymentSystem legacyPaymentSystem) {
        this.legacyPaymentSystem = legacyPaymentSystem;
    }

    public void processPayment(double amount) {
        legacyPaymentSystem.makePayment(amount);
    }
}
```

### Why it is useful
It allows old systems to fit into modern interfaces without rewriting everything.

---

## 6. Decorator Pattern in Real World
### Where it is used
- adding logging
- adding security checks
- adding caching
- wrapping services with extra behavior

### Example: Logging Decorator
```java
interface Service {
    void execute();
}

class BasicService implements Service {
    public void execute() {
        System.out.println("Basic service executed");
    }
}

class LoggingService implements Service {
    private Service service;

    public LoggingService(Service service) {
        this.service = service;
    }

    public void execute() {
        System.out.println("Logging before execution");
        service.execute();
        System.out.println("Logging after execution");
    }
}
```

### Why it is useful
It adds behavior dynamically without modifying the core service class.

---

## 7. Facade Pattern in Real World
### Where it is used
- simplifying complex modules
- external service wrappers
- e-commerce checkout service

### Example: Checkout Facade
```java
class InventoryService {
    public void checkStock() {
        System.out.println("Stock checked");
    }
}

class PaymentService {
    public void processPayment() {
        System.out.println("Payment processed");
    }
}

class ShippingService {
    public void shipOrder() {
        System.out.println("Order shipped");
    }
}

class CheckoutFacade {
    private InventoryService inventoryService;
    private PaymentService paymentService;
    private ShippingService shippingService;

    public CheckoutFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
    }

    public void checkout() {
        inventoryService.checkStock();
        paymentService.processPayment();
        shippingService.shipOrder();
    }
}
```

### Why it is useful
The client only needs one simple method to complete the whole checkout process.

---

## 8. Strategy Pattern in Real World
### Where it is used
- payment methods
- shipping options
- sorting algorithms
- discount calculation

### Example: Shipping Strategy
```java
interface ShippingStrategy {
    void ship();
}

class ExpressShipping implements ShippingStrategy {
    public void ship() {
        System.out.println("Shipping by Express");
    }
}

class StandardShipping implements ShippingStrategy {
    public void ship() {
        System.out.println("Shipping by Standard");
    }
}

class Order {
    private ShippingStrategy shippingStrategy;

    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public void placeOrder() {
        shippingStrategy.ship();
    }
}
```

### Why it is useful
The application can switch shipping behavior at runtime without changing the core order class.

---

## 9. Observer Pattern in Real World
### Where it is used
- email notifications
- stock market updates
- order status updates
- UI event handling

### Example: Order Status Observer
```java
import java.util.ArrayList;
import java.util.List;

interface OrderObserver {
    void update(String status);
}

class OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String status) {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}
```

### Why it is useful
Multiple systems can react when the order status changes.

---

## 10. Command Pattern in Real World
### Where it is used
- undo/redo
- task queues
- menu actions
- workflow execution

### Example: Remote Control Command
```java
interface Command {
    void execute();
}

class Light {
    public void on() {
        System.out.println("Light On");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}
```

### Why it is useful
It decouples the requester from the action performer.

---

## 11. Template Method Pattern in Real World
### Where it is used
- document processing systems
- framework workflows
- payment processing pipelines

### Example: Order Processing Template
```java
abstract class OrderProcessingTemplate {
    public final void processOrder() {
        validateOrder();
        calculateTotal();
        updateInventory();
        notifyCustomer();
    }

    public void validateOrder() {
        System.out.println("Validating order");
    }

    public abstract void calculateTotal();

    public void updateInventory() {
        System.out.println("Updating inventory");
    }

    public void notifyCustomer() {
        System.out.println("Notifying customer");
    }
}
```

### Why it is useful
It defines a reusable flow while allowing specific steps to vary.

---

## 12. Real-World Pattern Mapping

| Real-World Scenario | Design Pattern |
|---|---|
| Single configuration object globally used | Singleton |
| Different payment types | Factory |
| Creating complex orders with many fields | Builder |
| Integrating old payment gateway into new system | Adapter |
| Adding logging or caching to services | Decorator |
| Simplifying checkout process | Facade |
| Switching shipping strategy at runtime | Strategy |
| Sending notifications on order changes | Observer |
| Undo/redo actions | Command |
| Reusable order workflow with custom steps | Template Method |

---

## 13. Interview Tip
When asked about design patterns in interviews, explain:
1. what the pattern is
2. where it is used
3. why it is useful
4. a small code example

This structure helps you answer confidently and clearly.
