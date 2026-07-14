/*
Problem Statement:
Write a program that uses arithmetic, comparison, and logical operators.

Explanation:
Operators help perform calculations and make decisions in Java.
*/
public class Operators {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;

        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Remainder: " + (a % b));
        System.out.println("Equal? " + (a == b));
        System.out.println("Greater? " + (a > b));
        System.out.println("Logical AND: " + ((a > 5) && (b < 5)));
    }
}
