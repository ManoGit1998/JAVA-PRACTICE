/*
Problem Statement:
Print numbers from 1 to 5 using loops.

Explanation:
Loops repeat a block of code multiple times. They are useful for iteration.
*/
public class Loops {
    public static void main(String[] args) {
        System.out.println("For loop:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }

        System.out.println("\nWhile loop:");
        int j = 1;
        while (j <= 5) {
            System.out.print(j + " ");
            j++;
        }

        System.out.println("\nDo-while loop:");
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while (k <= 5);
    }
}
