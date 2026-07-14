/*
Problem Statement:
Create a parent class Animal and a child class Dog that inherits its behavior.

Explanation:
Inheritance allows one class to reuse properties and methods of another class.
*/
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.sound();
    }
}
