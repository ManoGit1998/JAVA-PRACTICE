/*
Problem Statement:
Handle an error when dividing by zero.

Explanation:
Exception handling helps catch runtime errors and prevent program crash.
*/
public class ExceptionHandling {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 0;
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }
}
