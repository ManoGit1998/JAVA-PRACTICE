# Design Patterns in Java

Design patterns are reusable solutions to common software design problems. They help make code more maintainable, flexible, and easier to understand.

## Why Design Patterns Matter
Design patterns help developers:
- reduce code duplication
- improve code structure
- make systems easier to extend
- improve maintainability and readability
- solve recurring design issues in a standard way

---

## 1. Creational Patterns
Creational patterns deal with object creation mechanisms.

### 1.1 Singleton Pattern
The Singleton pattern ensures that a class has only one instance and provides a global access point to it.

#### Use Case
Use it when you want exactly one instance of a class, such as:
- logging utility
- configuration manager
- connection pool

#### Example
```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

#### Example Usage
```java
public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true
    }
}
```

#### Notes
- It restricts object creation to one instance.
- It is often used in logging and configuration classes.

---

### 1.2 Factory Pattern
The Factory pattern creates objects without exposing the instantiation logic to the client.

#### Use Case
Use it when the creation logic is complex or when you want to hide the actual class being instantiated.

#### Example
```java
interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Bark");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow");
    }
}

class AnimalFactory {
    public Animal getAnimal(String type) {
        if (type.equalsIgnoreCase("dog")) {
            return new Dog();
        } else if (type.equalsIgnoreCase("cat")) {
            return new Cat();
        }
        return null;
    }
}
```

#### Example Usage
```java
public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        Animal animal = factory.getAnimal("dog");
        animal.speak();
    }
}
```

#### Notes
- It helps reduce tight coupling.
- It is useful when object creation depends on runtime conditions.

---

### 1.3 Abstract Factory Pattern
The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes.

#### Use Case
Use it when a system should be independent of how its related objects are created.

#### Example
```java
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

class MacButton implements Button {
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}

class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

#### Notes
- It creates a set of related objects.
- Useful for UI frameworks and cross-platform systems.

---

### 1.4 Builder Pattern
The Builder pattern is used to construct complex objects step by step.

#### Use Case
Use it when an object has many optional parameters or requires complex initialization.

#### Example
```java
class User {
    private final String name;
    private final int age;
    private final String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

#### Example Usage
```java
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder()
                .name("Mano")
                .age(30)
                .email("mano@example.com")
                .build();

        System.out.println(user);
    }
}
```

#### Notes
- It helps avoid constructor telescoping.
- Commonly used in building HTTP requests, configuration objects, and complex entities.

---

## 2. Structural Patterns
Structural patterns deal with how classes and objects are composed.

### 2.1 Adapter Pattern
The Adapter pattern allows incompatible interfaces to work together.

#### Use Case
Use it when you want to reuse an existing class but its interface does not match the expected one.

#### Example
```java
interface MediaPlayer {
    void play(String audioType, String fileName);
}

class Mp3Player implements MediaPlayer {
    public void play(String audioType, String fileName) {
        System.out.println("Playing mp3: " + fileName);
    }
}

interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc: " + fileName);
    }

    public void playMp4(String fileName) {
        // not implemented
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        }
    }

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}
```

#### Notes
- Useful when integrating old and new systems.
- Common in legacy code integration.

---

### 2.2 Decorator Pattern
The Decorator pattern adds behavior to an object dynamically without altering its class.

#### Use Case
Use it when you want to add responsibilities to objects at runtime.

#### Example
```java
interface Coffee {
    String getDescription();
    double cost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple Coffee";
    }

    public double cost() {
        return 5.0;
    }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double cost() {
        return coffee.cost() + 1.5;
    }
}
```

#### Example Usage
```java
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.cost());
    }
}
```

#### Notes
- Useful for UI components, streams, and input/output wrappers.

---

### 2.3 Facade Pattern
The Facade pattern provides a simplified interface to a larger subsystem.

#### Use Case
Use it when you want a simple interface to complex underlying systems.

#### Example
```java
class CPU {
    public void start() {
        System.out.println("CPU started");
    }
}

class Memory {
    public void load() {
        System.out.println("Memory loaded");
    }
}

class HardDrive {
    public void read() {
        System.out.println("Hard drive read");
    }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}
```

#### Notes
- Common in large enterprise systems and libraries.

---

### 2.4 Proxy Pattern
The Proxy pattern provides a surrogate or placeholder for another object to control access.

#### Use Case
Use it for lazy loading, access control, or logging.

#### Example
```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
```

#### Notes
- Useful when object creation is expensive.

---

## 3. Behavioral Patterns
Behavioral patterns deal with communication between objects.

### 3.1 Strategy Pattern
The Strategy pattern defines a family of algorithms and makes them interchangeable.

#### Use Case
Use it when you want to choose an algorithm at runtime.

#### Example
```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

#### Notes
- Useful when multiple algorithms or behaviors are possible.

---

### 3.2 Observer Pattern
The Observer pattern allows one object to notify many others when its state changes.

#### Use Case
Use it for event handling and notifications.

#### Example
```java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

#### Notes
- Common in GUI applications and event-driven systems.

---

### 3.3 Command Pattern
The Command pattern encapsulates a request as an object.

#### Use Case
Use it in command queues, undo/redo operations, and task execution.

#### Example
```java
interface Command {
    void execute();
}

class Light {
    public void on() {
        System.out.println("Light ON");
    }

    public void off() {
        System.out.println("Light OFF");
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

#### Notes
- Useful when you want to parameterize objects with actions.

---

### 3.4 Template Method Pattern
The Template Method pattern defines the skeleton of an algorithm and lets subclasses override certain steps.

#### Use Case
Use it when multiple classes share the same algorithm but need different implementations of some steps.

#### Example
```java
abstract class DataProcessor {
    public final void process() {
        readData();
        processData();
        writeData();
    }

    public void readData() {
        System.out.println("Reading data");
    }

    public abstract void processData();

    public void writeData() {
        System.out.println("Writing data");
    }
}
```

#### Notes
- Common in frameworks and workflow systems.

---

## 4. Summary Table

| Pattern | Purpose | Common Use Case |
|---|---|---|
| Singleton | One instance only | Config, logging |
| Factory | Creates objects without exposing logic | Object creation based on type |
| Abstract Factory | Creates families of related objects | UI frameworks |
| Builder | Builds complex objects step by step | Complex object creation |
| Adapter | Makes incompatible interfaces work together | Legacy integration |
| Decorator | Adds behavior dynamically | UI / I/O wrappers |
| Facade | Simplifies a complex subsystem | Large systems |
| Proxy | Controls access to an object | Lazy loading |
| Strategy | Swaps algorithms at runtime | Payment methods |
| Observer | Notifies dependent objects | Event systems |
| Command | Encapsulates actions | Undo/redo |
| Template Method | Defines algorithm skeleton | Frameworks |

---

## 5. Interview Questions
1. What is a design pattern?
2. What is the difference between Factory and Abstract Factory?
3. When do you use the Singleton pattern?
4. What is the difference between Adapter and Decorator?
5. What is the purpose of the Observer pattern?
6. When would you use the Strategy pattern?

---

## 6. Final Notes
Design patterns are not mandatory for every project, but they are extremely useful when the software becomes complex. The most commonly used patterns in Java and enterprise applications are:
- Singleton
- Factory
- Builder
- Adapter
- Decorator
- Observer
- Strategy
- Template Method
