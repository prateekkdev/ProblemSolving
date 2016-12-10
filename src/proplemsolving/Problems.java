/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proplemsolving;

import static algos.Sort.maxHeapify;
import static algos.Sort.minHeapify;
import static algos.Sort.swap;
import java.math.BigDecimal;
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
     *
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
         * rather than creating objects everytime), when handling with lot of
         * results. And printing in console after finish saved timeout(timeout
         * is 3s)
         */
        StringBuilder resultString = new StringBuilder();
        for (int index = 0; index < result.length; index++) {
            resultString.append(result[index] + " ");
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

    // Median of an array
    // Median means nth/2 for odd array and (nth+(n-1)th)/2 for even array
    public static int getMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[(n - 1) / 2] + arr[n / 2]) / 2;
        } else {
            return arr[n / 2];
        }
    }

    /**
     *
     * This is simple implementation. Complexity of this would be O(n)
     *
     * Median of two sorted arrays using merge(Both arrays are of same size)
     *
     * This function returns median of ar1[] and ar2[]. Assumptions in this
     * function: Both ar1[] and ar2[] are sorted arrays Both have n elements
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int findMedianArrayUsingMerge(int[] arr1, int[] arr2) {

        int count1 = 0, count2 = 0;

        // These are for nth and (n-1)th values, 
        // when resulting array would be of 2n size(first n + second n)
        int nPrevious = -1;
        int nCurrent = -1;

        // n is length here, so have to take care for index n in below loop.
        int n = arr1.length;

        while (count1 + count2 <= n) {

            /**
             * If count1 or count2 becomes n, would throw IndexOutOfBounds
             * Handle it beforehand in below two cases. It won't come for cases
             * post that.
             */
            // If all elements in array 1 are smaller than 2.
            if (count1 == n) {
                nPrevious = nCurrent;
                nCurrent = arr2[0];
                break;
            }

            // If all elements in array 2 are smaller than 1.
            if (count2 == n) {
                nPrevious = nCurrent;
                nCurrent = arr1[0];
                break;
            }

            // If first is smaller
            if (arr1[count1] <= arr2[count2]) {
                nPrevious = nCurrent;
                nCurrent = arr1[count1++];
            } else {
                nPrevious = nCurrent;
                nCurrent = arr2[count2++];
            }

        }

        // Only here we are taking are of median calculation.
        return (nCurrent + nPrevious) / 2;
    }

    /**
     *
     * This is Binary search based implementation. Complexity is O(log n)
     *
     * Median of two sorted arrays using median comparison
     *
     * A divide and conquer based efficient solution to find median of two
     * sorted arrays of same size.
     *
     * This function returns median of ar1[] and ar2[]. Assumptions in this
     * function: Both ar1[] and ar2[] are sorted arrays Both have n elements
     *
     * 17, 20, 25 = 20
     *
     * 15, 22, 24 = 22
     *
     * Median of above two = (20+22)/2 = 21
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int findMedianArrayUsingMedians(int[] arr1, int[] arr2) {
        return findMedianArrayUsingMedians(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
    }

    public static int getMedian(int[] arr, int start, int end) {
        // This would be length for numbers in array considered for median
        int length = end - start + 1;

        // This would be array length when start is 0 and end is arr.length
        int lastPosition = end + 1;
        if (length % 2 == 0) {
            return (arr[(lastPosition - 1) / 2] + arr[lastPosition / 2]) / 2;
        } else {
            return arr[lastPosition / 2];
        }
    }

    public static int findMedianArrayUsingMedians(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {

        // length1 would always be equal to length2, as binary search sort of thing for both arr1 and arr2 at the same time.
        int length = end1 - start1 + 1;
        if (length == 0) {
            return -1;
        } else if (length == 1) {
            return (arr1[start1] + arr2[start2]) / 2;
        } else if (length == 2) {
            return (Math.max(arr1[start1], arr2[start2]) + Math.min(arr1[end1], arr2[end2])) / 2;
        }

        int m1 = getMedian(arr1, start1, end1);
        int m2 = getMedian(arr2, start2, end2);

        // If medians are same
        if (m1 == m2) {
            return m1;
        }

        /**
         *
         *
         * Then our median lies between (m1 and end1) and (start2 and m2). Think
         * this way - m1 is smaller, need to increase(Take example)
         *
         * m1 = start1 + length/2; m2 = end2 - length/2 In both these below
         * conditions, take the safer side and take m1 from start1 + length/2
         * and m2 from end2 - length/2. So, we would have maximum set in case of
         * even odd issues and floor/ceiling arising from them. So if we would
         * take m2 from start2 + length/2, then might have missed one value if
         * length is odd. So, taking the maximum.
         */
        if (m1 < m2) {
            return findMedianArrayUsingMedians(arr1, start1 + length / 2, end1, arr2, start2, end2 - length / 2);
        }

        /**
         * Then our median lies between (start1 and m1) and (m2 and end2). Think
         * this way - m1 is larger, need to reduce(Take example)
         */
        if (m1 > m2) {
            return findMedianArrayUsingMedians(arr1, start1, start1 + length / 2, arr2, end2 - length / 2, end2);
        }

        // This would never be reached.
        return 0;
    }

    // Merge K sorted Arrays
    public static int[] mergeKSortedArrays() {
        return null;
    }

    // Maximum sum array
    public static int maxSum() {
        return 0;
    }

    // QuickSelect: Applications in median for unsorted array
    public static int QuickSelect(int[] arr) {
        return 0;
    }

    private static void checkFib() {

        long beforeTime = System.currentTimeMillis();
        System.out.println("" + fibModified(0, 1, 15));

        long afterTime = System.currentTimeMillis();

        System.out.println("Total Time: " + (afterTime - beforeTime) / 1000 + " secs");

    }

    private static long[] store = new long[10000];

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

    public static long fib(int count) {

        if (count <= 1) {
            return (long) count;
        } else {
            return (long) fib(count - 1) + (long) fib(count - 2);
        }
    }

    // HeapSelect(Could be done for smallest/largest using minHeapify)
    // Complexity O(n+klogn)
    public static int kthSmallest(int[] arr, int k) {
        if (k > arr.length || k < 1) {
            return -1;
        }

        // First make the heap
        for (int index = arr.length / 2; index >= 0; index--) {
            minHeapify(arr, index, arr.length - 1);
        }

        for (int index = arr.length - 1; index >= arr.length - k; index--) {
            swap(0, index, arr);
            minHeapify(arr, 0, index - 1);
        }

        return arr[arr.length - k];
    }

    // HeapSelect(Could be done for smallest/largest using minHeapify)
    // Complexity O(n+klogn)
    public static int kthLargest(int[] arr, int k) {
        if (k > arr.length || k < 1) {
            return -1;
        }

        // First make the heap
        // Complexity n
        for (int index = arr.length / 2; index >= 0; index--) {
            maxHeapify(arr, index, arr.length - 1);
        }

        // Complexity : klogn
        for (int index = arr.length - 1; index >= arr.length - k; index--) {
            swap(0, index, arr);
            maxHeapify(arr, 0, index - 1);
        }

        return arr[arr.length - k];
    }

    /**
     * Complexity is O(m+n)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] mergeTwoSortedArrays(int[] arr1, int[] arr2) {

        int[] arrResult = new int[arr1.length + arr2.length];

        int counter1 = 0;
        int counter2 = 0;
        int counterResult = 0;

        while (counter1 != arr1.length && counter2 != arr2.length) {
            if (arr1[counter1] <= arr2[counter2]) {
                arrResult[counterResult++] = arr1[counter1];
                counter1++;
            } else {
                arrResult[counterResult++] = arr2[counter2];
                counter2++;
            }
        }

        while (counter1 < arr1.length) {
            arrResult[counterResult++] = arr1[counter1];
            counter1++;
        }

        while (counter2 < arr2.length) {
            arrResult[counterResult++] = arr2[counter2];
            counter2++;
        }

        return arrResult;
    }

    /**
     * Array Rotation(Left rotation)
     */
    /**
     * Inplace Time Complexity: O(n) Space Complexity: O(1) This utilizes
     * property of Modulus/GCD of rotation with length, and total of rotation
     * jumps are made for each * number.
     * http://www.geeksforgeeks.org/array-rotation/
     */
    public static void rotation(int[] arr, int rotation) {

    }

    /**
     * Out of place, Time Complexity: O(n) Space Complexity: O(d)
     */
    public static void rotationOutOfPlace(int[] arr, int rotation) {

        int[] arrTemp = new int[arr.length];
        for (int index = 0; index < arr.length; index++) {
            arrTemp[index] = arr[index];
        }

        int rIndex = 0;
        for (int pIndex = rotation; pIndex < arr.length; pIndex++) {
            arr[rIndex++] = arrTemp[pIndex];
        }

        for (int pIndex = 0; rIndex < arr.length; rIndex++, pIndex++) {
            arr[rIndex] = arrTemp[pIndex];
        }
    }

    /**
     * Complexity is O(n^d) Better approach could be used for complexity of O(1)
     */
    public static void rotationNaive(int[] arr, int rotation) {

        for (int count = 1; count <= rotation; count++) {
            int first = arr[0];
            int index = 1;
            for (; index < arr.length; index++) {
                arr[index - 1] = arr[index];
            }
            arr[index - 1] = first;
        }
    }

    public static void printArr(int[] arr) {
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index] + " ");
        }
    }

    // 1, 2, 3, 4, 5, 6, 7, 8
    // For 3
    // 
    // _, _, 3, 1, 2, 6, 7, 5
    // For 3
    // 6, 7, 8, 1, 2, 3, 4, 5
    // For 1
    // 8, 1, 2, 3, 4, 5, 6, 7
    // For 2
    // 7, 8, 1, 2, 3, 4, 5, 6
    // For 3
    // 6, 7, 8, 1, 2, 3, 4, 5
    /**
     * http://www.geeksforgeeks.org/program-for-array-rotation-continued-reversal-algorithm/
     * TODO Need to implement this for swap using jumps
     */
    /*
    public static void jumpSwap(int[] arr, int jumpValue) {
        int previous = arr[startIndex];
        int currentIndex = jumpValue;
    }
     */
    /**
     * Complexity is O(n)
     *
     * @param arr
     * @param rotation
     */
    public static void rotateRight(int[] arr, int rotation) {
        // This is done, if rotation is larger than array, then take GCD/Modulus with length.
        rotation = rotation % arr.length;
        // printArray(arr);
        reverseArray(arr, 0, arr.length - rotation - 1);
        reverseArray(arr, arr.length - rotation, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
        // printArray(arr);
    }

    // Array Reversal
    public static void reverseArray(int[] arr, int start, int end) {
        for (; start < end; start++, end--) {
            swap1(arr, start, end);
        }
    }

    public static void printArray(int[] arr) {
        System.out.println();
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index] + ", ");
        }
        System.out.println();
    }

    public static void swap1(int[] arr, int pos1, int pos2) {
        arr[pos1] = arr[pos1] ^ arr[pos2];
        arr[pos2] = arr[pos1] ^ arr[pos2];
        arr[pos1] = arr[pos1] ^ arr[pos2];
    }
}
