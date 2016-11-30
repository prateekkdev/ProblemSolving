package proplemsolving;

import type.PBinarySearchTree;
import static algos.Sort.maxHeapify;
import static algos.Sort.minHeapify;
import static algos.Sort.swap;
import java.math.BigDecimal;

/**
 *
 * @author prateek.kesarwani
 */
public class ProplemSolving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        checkLinkedList();
    }

    private static void checkLinkedList() {
        type.integer.PLinkedList list = new type.integer.PLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(50);

        list.traverse();

        // list.reverseListPrint();
        list.reverseListRecursive();
        
        list.traverse();

    }

    private static void checkBST() {
        PBinarySearchTree tree = new PBinarySearchTree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(50);
        tree.insert(65);
        tree.insert(52);
        tree.insert(62);
        tree.insert(68);
        tree.insert(53);
        tree.insert(54);
        tree.insert(55);

        tree.traverseInorder();
        System.out.println();

        tree.traverseLevelorder();
        System.out.println();

        int lca = tree.lowestCommonAncester(30, 30);
        System.out.println("LCA: " + lca);

        System.out.println("Tree Height: " + tree.treeHeight());

        System.out.println("Node Depth: " + tree.depth(30));

        System.out.println("Tree Level: " + tree.level(30));

        System.out.println("Diamter: " + tree.diameter());

        System.out.println("Common Ancestor: " + tree.lowestCommonAncestorBinaryTree(10, 40));
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

    // Merge K sorted Arrays
    /*
    public static int[] mergeKSortedArrays(int[]...) {

        
        
        return null;
    }
     */
}
