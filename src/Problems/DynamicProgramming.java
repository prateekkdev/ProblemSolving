/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problems;

import java.math.BigDecimal;

/**
 * Dynamic programming: is a method for solving complex problems by breaking
 * them down into simpler subproblems. It is applicable to problems exhibiting
 * the properties of overlapping subproblems[1] and optimal substructure
 * (described below). When applicable, the method takes far less time than naive
 * methods that don't take advantage of the subproblem overlap (like depth-first
 * search).
 *
 *
 * @author prateek.kesarwani
 */
public class DynamicProgramming {

    // tn = tn-1 + tn-2
    // fib with defined t1 and t2
    public static long fib(int t1, int t2, int n) {
        if (n == 1) {
            return t1;
        } else if (n == 2) {
            return t2;
        }
        return fib(t1, t2, n - 1) + fib(t1, t2, n - 2);
    }

    /**
     * Naive Fibonacci
     *
     * @param count
     * @return
     */
    public static long fib(int count) {

        if (count <= 1) {
            return (long) count;
        } else {
            return (long) fib(count - 1) + (long) fib(count - 2);
        }
    }

    /**
     * https://www.hackerrank.com/challenges/fibonacci-modified
     *
     * @param t1
     * @param t2
     * @param n
     * @return
     */
    // tn = tn-2 + (tn-1)^2
    public static BigDecimal fibModified(int t1, int t2, int n) {
        if (n == 1) {
            return new BigDecimal(t1);
        } else if (n == 2) {
            return new BigDecimal(t2);
        }
        return fibModified(t1, t2, n - 2).add(fibModified(t1, t2, n - 1).multiply(fibModified(t1, t2, n - 1)));
    }

    private static long[] store = new long[1000];

    static {
        store[0] = 0;
        store[1] = 1;
    }

    // Limit is thousand, else will throw IndexOutofbounds
    public static long fibDynamic(int count) {

        if (count <= 1 || store[count] != 0) {
            return store[count];
        } else {
            long num1 = fibDynamic(count - 1);
            long num2 = fibDynamic(count - 2);
            long value = num1 + num2;
            store[count] = value;
            return value;
        }
    }

    /**
     * First element: 0, Second element: 1, Third Element: 1, .... 0, 1, 1, 2,
     * 3, 5, 8, 13,
     *
     * This is the simplest method.
     *
     * No need of maintaining Memoization table / Dynamic programming or naive
     * recursive methodology
     *
     * @param n
     * @return
     */
    public static long fibSimplest(int n) {

        long last = 0;
        long secondLast = 1;
        long current = -1;

        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }

        for (int count = 3; count <= n; count++) {
            current = last + secondLast;
            last = secondLast;
            secondLast = current;
        }
        return current;
    }

}
