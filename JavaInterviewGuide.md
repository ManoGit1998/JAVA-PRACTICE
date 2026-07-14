# Java Interview Preparation Guide

This guide is designed for interview preparation and for learning core Java in a structured way.

## 1. Java Basics

### What is Java?
Java is an object-oriented, platform-independent programming language. It is widely used for backend development, Android apps, enterprise applications, and large-scale systems.

### Important terms
- JDK: Java Development Kit, used to develop Java programs.
- JRE: Java Runtime Environment, used to run Java programs.
- JVM: Java Virtual Machine, responsible for executing Java bytecode.

### First Java program
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
```

### Interview points
- Java is platform-independent because bytecode runs on JVM.
- Every Java application needs a main method.
- Java is strongly typed.

---

## 2. Variables and Data Types

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

### Interview points
- Primitive types are not objects.
- Wrapper classes like Integer and Double are used for object-based operations.

---

## 3. Operators

### Types of operators
- Arithmetic: +, -, *, /, %
- Comparison: ==, !=, >, <, >=, <=
- Logical: &&, ||, !

```java
int a = 10;
int b = 5;
System.out.println(a + b);
System.out.println(a > b);
System.out.println((a > 5) && (b < 10));
```

### Interview points
- `==` compares values for primitives and references for objects.
- `&&` and `||` are short-circuit operators.

---

## 4. Control Statements

### If-else
```java
int marks = 75;
if (marks >= 80) {
    System.out.println("Grade A");
} else if (marks >= 60) {
    System.out.println("Grade B");
} else {
    System.out.println("Grade C");
}
```

### Switch
```java
int day = 2;
switch (day) {
    case 1 -> System.out.println("Monday");
    case 2 -> System.out.println("Tuesday");
    default -> System.out.println("Other day");
}
```

### Interview points
- `switch` works with int, char, String, and enum (modern Java).
- Use `break` carefully to avoid fallthrough.

---

## 5. Loops

### For loop
```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

### While loop
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

### Do-while loop
```java
do {
    System.out.println("Executed at least once");
} while (false);
```

### Interview points
- `for` is best when the number of iterations is known.
- `while` is best when the number of iterations depends on a condition.

---

## 6. Arrays

```java
int[] marks = {80, 90, 70};
System.out.println(marks[0]);
```

### Interview points
- Arrays have fixed size.
- Arrays start indexing from 0.
- Use collections if the size is dynamic.

---

## 7. Strings

```java
String name = "Manoj";
System.out.println(name.length());
System.out.println(name.toUpperCase());
System.out.println(name.concat(" Kumar"));
```

### Important points
- String is immutable in Java.
- For frequent modifications, use `StringBuilder`.

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" Interview");
System.out.println(sb);
```

### Interview points
- `String` creates immutable objects.
- `StringBuilder` is mutable and more efficient for concatenation.

---

## 8. Methods

```java
public class Methods {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(5, 7));
    }
}
```

### Interview points
- Methods help break code into reusable blocks.
- A method can return a value or be void.

### Method overloading
```java
class MathOps {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```

---

## 9. OOP Concepts

### Class and Object
```java
class Car {
    String brand;

    void drive() {
        System.out.println("Driving");
    }
}

public class Main {
    public static void main(String[] args) {
        Car c = new Car();
        c.brand = "Toyota";
        c.drive();
    }
}
```

### Encapsulation
Encapsulation means wrapping data and methods in a single unit and protecting data using access modifiers.

```java
class Student {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
```

### Inheritance
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

### Polymorphism
Polymorphism means one interface, many forms.

```java
Animal a = new Dog();
a.sound();
```

### Abstraction
Abstraction hides implementation details and shows only essential features.

```java
abstract class Shape {
    abstract void draw();
}
```

### Interview points
- Encapsulation = data hiding.
- Inheritance = reuse of code.
- Polymorphism = one reference can behave differently.
- Abstraction = define behavior without full implementation.

---

## 10. Constructor and this/super

```java
class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }
}
```

- `this` refers to current object.
- `super` refers to parent class.

---

## 11. Static, Final, and Access Modifiers

### Static
```java
class Demo {
    static int count = 0;
}
```

### Final
```java
final int MAX = 100;
```

### Access modifiers
- private: accessible within the class
- protected: accessible within package and subclasses
- public: accessible everywhere

### Interview points
- `static` members belong to the class, not the object.
- `final` can be used for constants and to prevent inheritance or overriding.

---

## 12. Exception Handling

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("This always executes");
}
```

### Interview points
- `try` contains risky code.
- `catch` handles exceptions.
- `finally` always executes.
- Use specific exceptions rather than generic ones.

---

## 13. Collections Framework

### List
```java
List<String> names = new ArrayList<>();
names.add("A");
names.add("B");
```

### Set
```java
Set<Integer> nums = new HashSet<>();
nums.add(1);
nums.add(2);
```

### Map
```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
System.out.println(ages.get("Alice"));
```

### Interview points
- List allows duplicates.
- Set does not allow duplicates.
- Map stores key-value pairs.

---

## 14. Multithreading

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}
```

```java
Thread t = new MyThread();
t.start();
```

### Interview points
- A thread is a lightweight process.
- `start()` creates a new thread.
- `run()` contains the task.

---

## 15. File Handling

```java
import java.io.*;

public class FileExample {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("demo.txt");
        writer.write("Hello File");
        writer.close();
    }
}
```

### Interview points
- File handling is used for reading and writing data.
- Use `try-with-resources` for better resource management.

---

## 16. Common Interview Questions

1. What is the difference between JDK, JRE, and JVM?
2. What is the difference between `==` and `.equals()`?
3. What is the difference between `String`, `StringBuilder`, and `StringBuffer`?
4. What is method overloading?
5. What is method overriding?
6. What is the difference between abstract class and interface?
7. What is the difference between `ArrayList` and `LinkedList`?
8. What is the difference between `HashMap` and `Hashtable`?
9. What is a checked exception?
10. What is the purpose of `finally` block?

---

## 17. Final Tips for Interview Preparation

- Practice coding by writing programs without looking at notes.
- Learn OOP concepts deeply.
- Be able to explain code clearly.
- Revise collections, exceptions, and multithreading frequently.
- Prepare answers for basic and advanced Java questions.
