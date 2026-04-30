package ADACodesFiles;

import java.util.Scanner;

public class Practical1 {

    // --- FACTORIAL ---
    // Iterative
    public static long factorialIterative(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // Recursive
    public static long factorialRecursive(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorialRecursive(n - 1);
    }

    // --- FIBONACCI ---
    // Iterative
    public static void fibonacciIterative(int n) {
        int a = 0, b = 1, c;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
        System.out.println();
    }

    // Recursive
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number for Factorial: ");
        int num1 = sc.nextInt();

        System.out.println("Factorial (Iterative): " + factorialIterative(num1));
        System.out.println("Factorial (Recursive): " + factorialRecursive(num1));

        System.out.print("\nEnter number of terms for Fibonacci: ");
        int num2 = sc.nextInt();

        System.out.print("Fibonacci (Iterative): ");
        fibonacciIterative(num2);

        System.out.print("Fibonacci (Recursive): ");
        for (int i = 0; i < num2; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();
        sc.close();
    }
}