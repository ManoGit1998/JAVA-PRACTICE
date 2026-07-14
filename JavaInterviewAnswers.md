# Java Interview Answers Guide

This file gives short, interview-style answers you can use while practicing.

## 1. What is Java?
Java is a high-level, object-oriented, platform-independent programming language. It is widely used for building enterprise applications, backend services, and Android applications.

## 2. What is the difference between JDK, JRE, and JVM?
- JDK is used for development.
- JRE is used to run Java programs.
- JVM executes the bytecode and makes Java platform-independent.

## 3. What is the main method?
The main method is the entry point of a Java application. Execution starts from it.

## 4. What is the difference between `==` and `.equals()`?
`==` compares primitive values or references, while `.equals()` compares the actual content of objects.

## 5. What is the difference between `String`, `StringBuilder`, and `StringBuffer`?
- `String` is immutable.
- `StringBuilder` is mutable and not thread-safe.
- `StringBuffer` is mutable and thread-safe.

## 6. What is OOP?
OOP means writing programs using objects and classes to model real-world entities.

## 7. What are the four pillars of OOP?
The four pillars are encapsulation, inheritance, polymorphism, and abstraction.

## 8. What is encapsulation?
Encapsulation means wrapping data and methods together and restricting direct access using access modifiers.

## 9. What is inheritance?
Inheritance allows a class to acquire properties and methods from another class.

## 10. What is polymorphism?
Polymorphism means one interface can behave differently depending on the object implementing it.

## 11. What is abstraction?
Abstraction hides the implementation details and shows only the essential features.

## 12. What is the difference between abstract class and interface?
An abstract class can have both abstract and concrete methods, while an interface mainly defines a contract. A class can implement multiple interfaces but extend only one class.

## 13. What is method overloading?
Method overloading means having multiple methods with the same name but different parameters.

## 14. What is method overriding?
Method overriding happens when a subclass provides a specific implementation of a method already defined in the parent class.

## 15. What is a constructor?
A constructor is a special method used to initialize an object when it is created.

## 16. What is the difference between `throw` and `throws`?
`throw` is used to explicitly throw an exception, while `throws` declares that a method may throw an exception.

## 17. What is exception handling?
Exception handling is a mechanism to manage runtime errors and prevent the program from crashing.

## 18. What is the purpose of `finally`?
The `finally` block executes regardless of whether an exception occurs or not, and is often used for cleanup.

## 19. What is the difference between checked and unchecked exceptions?
Checked exceptions are checked at compile time, while unchecked exceptions occur at runtime.

## 20. What is a thread?
A thread is the smallest unit of execution in a Java program.

## 21. What is the difference between `start()` and `run()`?
`start()` creates a new thread, while `run()` executes the task in the current thread.

## 22. What is synchronization?
Synchronization ensures that only one thread can access a shared resource at a time.

## 23. What is the difference between `HashMap` and `Hashtable`?
`HashMap` is not synchronized and allows null values, while `Hashtable` is synchronized and does not allow null values.

## 24. What is the difference between `ArrayList` and `LinkedList`?
`ArrayList` is better for fast random access, while `LinkedList` is better for frequent insertions and deletions.

## 25. What is the difference between `Comparable` and `Comparator`?
`Comparable` provides natural ordering, while `Comparator` provides custom ordering.

## 26. What is the Stream API?
The Stream API is used to process collections in a functional style, making code concise and readable.

## 27. What is a lambda expression?
A lambda expression is a concise way to represent an anonymous function.

## 28. What is `Optional`?
`Optional` is used to represent a value that may be absent and helps avoid null pointer exceptions.

## 29. What is garbage collection?
Garbage collection is the process by which the JVM automatically frees memory occupied by unreferenced objects.

## 30. What is the difference between stack and heap memory?
Stack memory is used for method calls and local variables, while heap memory is used for objects.

## Interview Tip
When answering, give a short definition first, then add one example or one real-world use case.
