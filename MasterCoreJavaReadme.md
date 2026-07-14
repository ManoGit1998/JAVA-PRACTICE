# Master Core Java Readme

This is a complete, sequential guide for Core Java preparation from beginner to interview-ready level.

---

## A. What is Java?
Java is a high-level, object-oriented, platform-independent programming language. It is widely used for backend development, enterprise applications, Android apps, and web services.

### Key points
- Java is compiled into bytecode.
- Bytecode runs on the JVM.
- Java is platform-independent because of the JVM.

---

## B. Java Environment Setup
To write and run Java programs, you need:
- JDK: Java Development Kit
- JRE: Java Runtime Environment
- JVM: Java Virtual Machine

### Simple meaning
- JDK = tools to develop Java programs
- JRE = environment to run Java programs
- JVM = engine that executes Java bytecode

---

## C. First Java Program
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

### Important points
- Class name should match the file name.
- `main` method is the starting point of execution.

---

## D. Variables and Data Types
Variables store data.

### Primitive data types
- int: whole numbers
- double: decimal numbers
- char: single character
- boolean: true/false

```java
int age = 25;
double salary = 45000.50;
char grade = 'A';
boolean isStudent = true;
```

### Interview note
Primitive types are basic built-in data types.

---

## E. Operators
Operators perform operations on variables.

### Types
- Arithmetic: +, -, *, /, %
- Relational: ==, !=, >, <
- Logical: &&, ||, !

```java
int a = 10;
int b = 5;
System.out.println(a + b);
System.out.println(a > b);
System.out.println((a > 3) && (b < 10));
```

---

## F. Conditional Statements
Used to make decisions.

### if-else
```java
int marks = 70;
if (marks >= 80) {
    System.out.println("Grade A");
} else {
    System.out.println("Grade B");
}
```

### switch
```java
int day = 2;
switch (day) {
    case 1 -> System.out.println("Monday");
    case 2 -> System.out.println("Tuesday");
    default -> System.out.println("Other day");
}
```

---

## G. Loops
Loops repeat code multiple times.

### for loop
```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

### while loop
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

### do-while loop
```java
do {
    System.out.println("Hello");
} while (false);
```

---

## H. Arrays
Arrays store multiple values of the same type.

```java
int[] marks = {80, 90, 70};
System.out.println(marks[0]);
```

### Important points
- Index starts from 0.
- Arrays have fixed size.

---

## I. Strings
Strings store text.

```java
String name = "Mano";
System.out.println(name.length());
System.out.println(name.toUpperCase());
```

### Key idea
String is immutable in Java.

---

## J. Methods
Methods are reusable blocks of code.

```java
public static int add(int a, int b) {
    return a + b;
}
```

### Method overloading
Same method name with different parameters.

```java
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
```

---

## K. Classes and Objects
A class is a blueprint. An object is an instance of a class.

```java
class Car {
    String brand;
}

Car c = new Car();
c.brand = "Toyota";
```

---

## L. Constructors
Constructors are used to initialize objects.

```java
class Student {
    Student() {
        System.out.println("Object created");
    }
}
```

---

## M. this and super
- `this` refers to the current object.
- `super` refers to the parent class.

---

## N. OOP Concepts
### Encapsulation
Wrapping data and methods together and hiding data.

### Inheritance
One class acquires the properties of another class.

### Polymorphism
One interface can behave differently.

### Abstraction
Hiding implementation details and showing necessary features.

---

## O. Access Modifiers
- private: accessible only within class
- default: within package
- protected: within package and subclasses
- public: everywhere

---

## P. Static and Final
- `static`: belongs to the class
- `final`: cannot be changed or overridden

```java
class Demo {
    static int count = 0;
    final int MAX = 100;
}
```

---

## Q. Exception Handling
Used to handle errors safely.

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Cleanup");
}
```

### Key keywords
- try
- catch
- finally
- throw
- throws

---

## R. Collections Framework
Collections are used to store and manage groups of objects.

### List
Ordered, duplicates allowed.

### Set
Unique elements only.

### Map
Key-value pairs.

```java
List<String> names = new ArrayList<>();
Set<Integer> nums = new HashSet<>();
Map<String, Integer> ages = new HashMap<>();
```

---

## S. Common Collection Classes
- ArrayList
- LinkedList
- HashSet
- HashMap
- TreeMap
- ConcurrentHashMap

### Interview note
Choose based on performance and use case.

---

## T. Comparable vs Comparator
- Comparable: natural ordering
- Comparator: custom ordering

---

## U. Equals and HashCode
Used when objects need to be compared.

### Important rule
If you override `equals()`, override `hashCode()` too.

---

## V. Multithreading
A thread is the smallest unit of execution.

```java
Thread t = new Thread(() -> System.out.println("Thread running"));
t.start();
```

### Important concepts
- `start()`
- `run()`
- synchronization
- deadlock
- ExecutorService

---

## W. Java 8+ Features
- Lambda expressions
- Functional interfaces
- Streams API
- Optional
- Date and Time API

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
nums.forEach(n -> System.out.println(n));
```

---

## X. Stream API
Streams are used to process collections in a functional style.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> even = numbers.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
```

---

## Y. File Handling
Used to read and write files.

```java
import java.io.*;

FileWriter writer = new FileWriter("demo.txt");
writer.write("Hello");
writer.close();
```

---

## Z. Interview Preparation Tips
- Practice coding without looking at notes.
- Explain concepts clearly.
- Revise OOP, collections, exceptions, and multithreading.
- Be ready to answer both theory and coding questions.

### Common interview topics
- Java basics
- OOP concepts
- Strings and collections
- Exception handling
- Multithreading
- Java 8 features
- JVM memory concepts

---

## Final Study Order
1. Java basics
2. Variables and operators
3. Control statements and loops
4. Arrays and strings
5. Methods
6. OOP concepts
7. Exceptions
8. Collections
9. Multithreading
10. Java 8+ features
11. Interview practice

This sequence will help you prepare core Java in a structured way.
