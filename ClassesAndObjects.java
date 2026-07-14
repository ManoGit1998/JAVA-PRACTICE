/*
Problem Statement:
Create a class named Car and create an object to access its properties.

Explanation:
A class is a blueprint, and an object is an instance of a class.
*/
class Car {
    String brand;
    int year;

    void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }
}

public class ClassesAndObjects {
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.brand = "Toyota";
        c1.year = 2022;
        c1.displayInfo();
    }
}
