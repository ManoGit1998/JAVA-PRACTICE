/*
Problem Statement:
Store and print a list of student marks in an array.

Explanation:
Arrays store multiple values of the same type in a single variable.
*/
public class Arrays {
    public static void main(String[] args) {
        int[] marks = {78, 85, 90, 88, 92};

        System.out.println("Marks:");
        for (int mark : marks) {
            System.out.println(mark);
        }

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Matrix element: " + matrix[1][2]);
    }
}
