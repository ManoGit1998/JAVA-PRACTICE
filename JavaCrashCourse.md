# Java Crash Course

This is a short, high-impact revision guide for preparing for Java interviews quickly.

## 1. Java Basics
- Java is object-oriented and platform-independent.
- It compiles into bytecode and runs on the JVM.
- JDK is for development, JRE is for running programs, and JVM executes the code.

## 2. First Program
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

## 3. Variables and Data Types
```java
int age = 25;
double salary = 50000.50;
char grade = 'A';
boolean isActive = true;
```

### Common types
- int: whole number
- double: decimal
- char: single character
- boolean: true/false

## 4. Operators
- Arithmetic: +, -, *, /, %
- Comparison: ==, !=, >, <
- Logical: &&, ||, !

## 5. Control Statements
```java
if (age > 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

```java
switch (day) {
    case 1 -> System.out.println("Monday");
    default -> System.out.println("Other day");
}
```

## 6. Loops
```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

## 7. Arrays and Strings
```java
int[] marks = {90, 80, 70};
String name = "Java";
```

### Important points
- Arrays have fixed size.
- Strings are immutable.

## 8. Methods
```java
public static int add(int a, int b) {
    return a + b;
}
```

### Method overloading
Same method name with different parameters.

## 9. OOP Concepts
### Class and Object
```java
class Car {
    String brand;
}
```

### Encapsulation
Hide data using private variables and public methods.

### Inheritance
Child class reuses properties of parent class.

### Polymorphism
One reference can behave differently based on the object.

### Abstraction
Hide implementation details and show only essential behavior.

## 10. Constructor
```java
class Student {
    Student() {
        System.out.println("Student created");
    }
}
```

## 11. Exception Handling
```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Cleanup");
}
```

## 12. Collections
```java
List<String> names = new ArrayList<>();
Set<Integer> nums = new HashSet<>();
Map<String, Integer> ages = new HashMap<>();
```

### Quick notes
- List: ordered, duplicates allowed
- Set: unique values only
- Map: key-value pairs

## 13. Multithreading
```java
Thread t = new Thread(() -> System.out.println("Running"));
t.start();
```

### Important concepts
- `start()` starts a new thread.
- `synchronized` prevents race conditions.

## 14. Java 8+ Features
- Lambda expressions
- Stream API
- Optional

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
nums.forEach(n -> System.out.println(n));
```

## 15. Common Interview Questions
- Difference between `==` and `.equals()`
- Difference between `String` and `StringBuilder`
- Difference between abstract class and interface
- Difference between `HashMap` and `Hashtable`
- Difference between `Comparable` and `Comparator`
- Difference between `start()` and `run()`

## 16. Final Tip
Practice writing code from memory and explain each concept in simple words.
