/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proplemsolving;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/countingsort1 Given a list of integers,
 * can you count and output the number of times each value appears? Only for
 * integer range of 0-100
 *
 * @author prateek.kesarwani
 */
public class Problems {

    public static void countIntegers() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arrInput = new int[100];
        for (int count = 0; count < n; count++) {
            arrInput[in.nextInt()]++;
        }

        for (int count : arrInput) {
            System.out.print(count + " ");
        }
    }

    /**
     * https://www.hackerrank.com/challenges/countingsort2
     *
     * Given an unsorted list of integers, output the integers in order. Hint:
     * You can use your previous code that counted the items to print out the
     * actual values in order.
     */
    public static void countingSort() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arrInput = new int[100];
        for (int count = 0; count < n; count++) {
            arrInput[in.nextInt()]++;
        }

        for (int index = 0; index < arrInput.length; index++) {
            for (int count = arrInput[index]; count > 0; count--) {
                System.out.print(index + " ");
            }
        }
    }

    /**
     * https://www.hackerrank.com/challenges/countingsort3
     *
     * You will be given a list that contains both integers and strings. In this
     * challenge you just care about the integers. For every value from , can
     * you output , the number of elements that are less than or equal to ?
     */
    public static void countingIncremental() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arrInput = new int[100];
        for (int count = 0; count < n; count++) {
            arrInput[in.nextInt()]++;
            in.next();
        }

        int total = 0;
        for (int index = 0; index < arrInput.length; index++) {
            total += arrInput[index];
            System.out.print(total + " ");
        }
    }

    private static class StringSeq {

        int key;
        String value;

        public StringSeq(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
    
    /**
     * https://www.hackerrank.com/challenges/countingsort4
     * @param args 
     */

    public static void countingFull(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arrCount = new int[100];

        StringSeq[] originalArr = new StringSeq[n];

        for (int count = 0; count < n; count++) {

            int key = in.nextInt();
            String value = in.next();
            if (count < n / 2) {
                value = "-";
            }

            StringSeq seq = new StringSeq(key, value);
            originalArr[count] = seq;

            // Increment helper array
            arrCount[key]++;
        }

        int[] arrTotal = new int[100];

        int total = 0;
        for (int index = 0; index < arrTotal.length; index++) {
            total += arrCount[index];
            arrTotal[index] = total;
        }

        String[] result = new String[n];

        for (int index = 0; index < result.length; index++) {

            int originalKey = originalArr[index].getKey();

            result[arrTotal[originalKey] - arrCount[originalKey]] = originalArr[index].getValue();
            arrCount[originalKey] = arrCount[originalKey] - 1;
        }

        /**
        * Using StringBuilder saved a lot of time(as mutable, so can append, 
        * rather than creating objects everytime), when handling with lot of results. 
        * And printing in console after finish saved timeout(timeout is 3s)
        */
        StringBuilder resultString = new StringBuilder();
        for(int index = 0; index < result.length; index++) {
            resultString.append(result[index]+ " ");
        }
        System.out.print(resultString);
    }

    /**
     * https://www.hackerrank.com/challenges/chocolate-feast
     *
     */
// Input Format 
// t - No of trips
// n - Total money
// c - Price of each chocolate
// m - Wrappers needed per chocolate 
// Constraints
// 1 <= t <= 1000, 
// 2 <= n <= 10^5, 
// 1 <= c <= n, 
// 2 <= m <= n
    public static void chocolateFeastScanner() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();

            int totalChocolates = chocolateFeast(n, c, m);
            System.out.println(totalChocolates);
        }
    }

    // 45, 2, 3
    public static int chocolateFeast(int n, int c, int m) {

        int currentChocolates = n / c;

        int totalChocolates = currentChocolates;

        while (currentChocolates >= m) {
            totalChocolates = totalChocolates + currentChocolates / m;
            currentChocolates = currentChocolates / m + currentChocolates % m;
        }

        return totalChocolates;
    }

    public static void compareTheTripLetScanner(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
    }

    public static void compareTheTriplet(int a0, int a1, int a2, int b0, int b1, int b2) {
        int alice = 0;
        int bob = 0;

        if (a0 > b0) {
            alice++;
        } else if (a0 < b0) {
            bob++;
        }

        if (a1 > b1) {
            alice++;
        } else if (a1 < b1) {
            bob++;
        }

        if (a2 > b2) {
            alice++;
        } else if (a2 < b2) {
            bob++;
        }
    }
}
