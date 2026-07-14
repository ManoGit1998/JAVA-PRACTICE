# E-Commerce Design Pattern Example

This file shows how multiple design patterns can work together in a real-world e-commerce application.

---

## 1. Project Scenario
Imagine an e-commerce system with:
- User registration
- Product catalog
- Cart management
- Payment processing
- Order placement
- Notifications

A real project usually uses several design patterns together to keep the system flexible and maintainable.

---

## 2. System Flow
```text
User -> Controller -> Service -> Repository -> Database
               |                |
               |                +--> Payment Strategy
               +--> Notification Factory
               +--> Order Builder
```

This flow shows how different patterns can be used in one application.

---

## 3. Singleton Pattern – Configuration Manager
```java
class ConfigManager {
    private static ConfigManager instance;

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getStoreName() {
        return "MyShop";
    }
}
```

### Where it is used
Used for application-wide settings like store name, currency, or API keys.

---

## 4. Factory Pattern – Notification Factory
```java
interface NotificationService {
    void send(String message);
}

class EmailNotification implements NotificationService {
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SmsNotification implements NotificationService {
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class NotificationFactory {
    public NotificationService getNotification(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailNotification();
        } else if (type.equalsIgnoreCase("sms")) {
            return new SmsNotification();
        }
        throw new IllegalArgumentException("Unknown notification type");
    }
}
```

### Where it is used
Used when the system needs to send notifications through different channels.

---

## 5. Builder Pattern – Order Builder
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

### Where it is used
Used when creating complex orders with many optional values.

---

## 6. Strategy Pattern – Payment Strategy
```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " by PayPal");
    }
}

class CheckoutService {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

### Where it is used
Used when the system allows different payment methods like card, PayPal, or wallet.

---

## 7. Adapter Pattern – Legacy Payment Integration
```java
interface NewPaymentGateway {
    void processPayment(double amount);
}

class LegacyPaymentSystem {
    public void makePayment(double amount) {
        System.out.println("Legacy payment completed: " + amount);
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

### Where it is used
Used when an old payment system needs to work with the new checkout module.

---

## 8. Facade Pattern – Checkout Facade
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

### Where it is used
Used to simplify the full checkout process for the controller or client.

---

## 9. Observer Pattern – Order Status Notification
```java
import java.util.ArrayList;
import java.util.List;

interface OrderObserver {
    void update(String message);
}

class OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }
}
```

### Where it is used
Used when the order status should notify multiple subscribers such as email, SMS, or dashboard systems.

---

## 10. How They Work Together in One Project
A real e-commerce service can combine these patterns like this:
- Singleton for configuration
- Factory for notification channels
- Builder for creating orders
- Strategy for payment methods
- Adapter for connecting legacy payment systems
- Facade for checkout process
- Observer for order status events

### Example Usage
```java
public class Main {
    public static void main(String[] args) {
        ConfigManager config = ConfigManager.getInstance();
        System.out.println(config.getStoreName());

        NotificationFactory factory = new NotificationFactory();
        NotificationService notification = factory.getNotification("email");
        notification.send("Order placed successfully");

        Order order = new Order.Builder()
                .customerName("Mano")
                .product("Laptop")
                .quantity(1)
                .price(1000)
                .build();

        CheckoutService checkoutService = new CheckoutService();
        checkoutService.setPaymentStrategy(new PayPalPayment());
        checkoutService.checkout(1000);
    }
}
```

---

## 11. Why This Matters in Interviews
Interviewers often ask:
- Which design pattern would you use in an e-commerce system?
- How would you design a payment module?
- How would you make a checkout system scalable and flexible?

A good answer is:
- use Strategy for payment methods
- use Factory for notification channels
- use Builder for complex order creation
- use Facade for simplifying checkout flow

---

## 12. Final Summary
In real-world software projects, design patterns are not used alone. They are combined to solve different parts of the system.

The most useful patterns in e-commerce systems are:
- Singleton
- Factory
- Builder
- Strategy
- Adapter
- Facade
- Observer
