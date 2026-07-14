/*
Problem Statement:
Create a program that checks a number and prints whether it is positive, negative, or zero.

Explanation:
Conditional statements help Java choose different paths based on conditions.
*/
public class ConditionalStatements {
    public static void main(String[] args) {
        int number = -7;

        if (number > 0) {
            System.out.println("Positive number");
        } else if (number < 0) {
            System.out.println("Negative number");
        } else {
            System.out.println("Zero");
        }

        switch (number) {
            case 1:
                System.out.println("Number is 1");
                break;
            case -7:
                System.out.println("Number is -7");
                break;
            default:
                System.out.println("Some other number");
        }
    }
}
