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

/*

/*
    
    // you can write to stdout for debugging purposes, e.g.
// console.log('this is a debug message');

// Returns true if and only if A can be sorted with at most one swap.
function solution(A) {
  for (var i = 1; i < A.length; ++i) {
    // Look for an inverted adjacent pair.
    if (A[i-1] <= A[i]) {
      continue;
    }
    var x = A[i-1],
        left = i-1;
    // If x is one of a sequence of identical elements, take the leftmost.
    while (left-1 >= 0 && A[left-1] == x) {
      --left;
    }
    // Scan past the inverted pair for the earliest element no smaller than x.
    for (++i; i < A.length; ++i) {
      if (A[i] >= x) {
        break;  // If we never break here, i will be equal to A.length.
      }
    }
    // Let y be the element before the earliest element no smaller than x.
    var right = i-1,
        y = A[right];  
    // Swap x and y.
    A[left] = y;
    A[right] = x;
    // Is the array sorted now?
    for (i = (left == 0 ? 1 : left); i < A.length; ++i) {
      if (A[i-1] > A[i]) {
        return false;
      }
    }
    return true;  // One swap was enough to sort the array.
  }
  return true;  // The array is already sorted.
}
    

 */
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

        // TODO This doesn't seem right, should be for event lenght: return (arr[start + length/2 - 1] + arr[start + length/2]) / 2;
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
     * Array Rotations
     */
    /**
     * Out of place,
     *
     * Here first elements are copied to temp arr.
     *
     * Then they are copied back from rotation to length, and then from 0 to
     * rotation
     *
     * Time Complexity: O(n) Space Complexity: O(d)
     */
    public static void leftRotationOutOfPlace(int[] arr, int rotation) {

        // After full cycle, we will come back to same location
        rotation = rotation % arr.length;

        int[] arrTemp = new int[arr.length];
        for (int index = 0; index < arr.length; index++) {
            arrTemp[index] = arr[index];
        }

        int resultIndex = 0;
        for (int index = rotation; index < arr.length; index++) {
            arr[resultIndex++] = arrTemp[index];
        }

        for (int index = 0; resultIndex < arr.length; resultIndex++, index++) {
            arr[resultIndex] = arrTemp[index];
        }
    }

    /**
     *
     * Here, basic one movement at a time is done for n rotation. So, complexity
     * is O(n^d)
     *
     * Better approach could be used for complexity of O(n)
     */
    public static void leftRotationNaive(int[] arr, int rotation) {

        // After full cycle, we will come back to same location
        rotation = rotation % arr.length;

        for (int count = 1; count <= rotation; count++) {
            int first = arr[0];
            int index = 1;
            for (; index < arr.length; index++) {
                arr[index - 1] = arr[index];
            }
            arr[index - 1] = first;
        }
    }

    /**
     * This utilizes Jumping rotation times starting from last index. And the
     * cycle is repeated for GCD times.
     *
     * This is an extension of method 2. Instead of moving one by one, divide
     * the array in different sets where number of sets is equal to GCD of n and
     * d and move the elements within sets.
     *
     * How does the GCD decide the number of cycles needed to rotate the array?
     * Because the inner loop increments in steps of d, and stops when it gets
     * back to the starting point, i.e. a total span which is some multiple of
     * n. That multiple is LCM(n, d). Thus the number of elements in that cycle
     * is LCM(n, d) / d. The total number of such cycles is n / (LCM(n, d) / d),
     * which is equal to GCD(n, d).
     *
     * Why is it that once we finish a cycle, we start the new cycle from the
     * next element i.e. can't the next element be already a part of a processed
     * cycle? No. The inner loop increments in steps of d, which is a multiple
     * of GCD(n, d). Thus by the time we start the i-th cycle, for a hit we'd
     * need (k*GCD + z) % n == i (for 0 <= z < i). This leads to (k*GCD) % n ==
     * (i - z). This clearly has no solutions.
     *
     * Time complexity: O(n)
     *
     * Auxiliary Space: O(1)
     *
     * @param arr
     * @param rotation
     * @return
     */
    public static int[] leftRotation(int[] arr, int rotation) {

        // After full cycle, we will come back to same location
        rotation = rotation % arr.length;

        int gcd = getGCD(rotation, arr.length);
        for (int count = 0; count < gcd; count++) {
            int prevIndex = arr.length - 1 - count;
            int prevValue = arr[prevIndex];
            int currIndex = getJumpIndexLeftRotation(arr, prevIndex, rotation);
            while (currIndex != prevIndex) {
                int currValue = arr[currIndex];
                arr[currIndex] = prevValue;
                prevValue = currValue;
                currIndex = getJumpIndexLeftRotation(arr, currIndex, rotation);
            }
            arr[currIndex] = prevValue;
        }
        return arr;
    }

    /**
     * Find greatest common divisor between rotation and size
     *
     * @param rotation
     * @param size
     * @return
     */
    public static int getGCD(int rotation, int size) {
        if (size == 0) {
            return rotation;
        } else {
            return getGCD(size, rotation % size);
        }
    }

    /**
     * Get the index for next jump. Index might be beyond 0 and cycled to back
     * of array.
     *
     * @param arr
     * @param original
     * @param rotation
     * @return
     */
    public static int getJumpIndexLeftRotation(int[] arr, int original, int rotation) {
        if (original >= rotation) {
            return original - rotation;
        } else {
            return arr.length - (rotation - original);
        }
    }

    public static void printArr(int[] arr) {
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index] + " ");
        }
    }

    /**
     * Complexity is O(n)
     *
     * @param arr
     * @param rotation
     */
    public static void rotateRight(int[] arr, int rotation) {
        // This is done, if rotation is larger than array, then take modulus with length.
        rotation = rotation % arr.length;
        // printArray(arr);
        reverseArray(arr, 0, arr.length - 1 - rotation);
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
