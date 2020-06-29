package com.sdk.studydemo.test;

public class SumTest {

    public static void main(String[] args) {
        System.out.println(fib2(40));

    }

    /**
     * 0 1 1 2 3 5 8 13
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 2) + fib(n - 1);
    }

    public static int fib2(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

}
