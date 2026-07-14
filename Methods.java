/*
Problem Statement:
Create a method that adds two numbers and returns the result.

Explanation:
Methods are reusable blocks of code that perform a specific task.
*/
public class Methods {
    public static int addNumbers(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int result = addNumbers(10, 20);
        System.out.println("Sum: " + result);
    }
}
