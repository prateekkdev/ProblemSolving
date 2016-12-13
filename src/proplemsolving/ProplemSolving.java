package proplemsolving;

import algos.Sort;
import type.PBinarySearchTree;
import type.integer.PLinkedList;
import Problems.DynamicProgramming;

/**
 *
 * @author prateek.kesarwani
 */
public class ProplemSolving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6};

        Sort.displayArr(arr);

        System.out.println("Median: " + findMedianUsingQuickSelect(arr));
    }

    public static double findMedianUsingQuickSelect(int[] arr) {
        int n = arr.length - 1;

        // Here % condition is reversed for odd/even as n is length - 1
        if (n % 2 == 0) {
            return quickSelect(arr, 0, n, n / 2);
        } else {
            return (quickSelect(arr, 0, n, n / 2) + quickSelect(arr, 0, n, n / 2 + 1)) / 2d;

        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(arr, start, end);
            Sort.displayArr(arr);
            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        // If no intermediate swap, then this would be returned after swapping with pivot.
        int smallerIndex = start;

        for (int index = start; index < end; index++) {

            // For descending order, just change condition to >
            if (arr[index] < pivot) {
                swap(index, smallerIndex, arr);

                // This is always one above the smaller element, 
                // so pivot would be swapped later and index returned.
                smallerIndex++;
            }
        }
        swap(smallerIndex, end, arr);
        return smallerIndex;
    }

    /**
     * This uses same partition function as used for QuickSort. Sort of works as
     * a binary search, where we don't have to recurse both halves.
     *
     * Time Complexity: Best and Avg: O(n), Worst: O(n^2)
     *
     * @param arr
     * @param start
     * @param end
     * @param kIndex
     * @return
     */
    public static int quickSelect(int[] arr, int start, int end, int kIndex) {

        // Here I am taking <= because even first smallest might be needed
        if (start <= end) {
            int partitionIndex = partition(arr, start, end);

            if (partitionIndex == kIndex) {
                return arr[partitionIndex];
            } else if (partitionIndex < kIndex) {
                return quickSelect(arr, partitionIndex + 1, end, kIndex);
            } else {
                return quickSelect(arr, start, partitionIndex - 1, kIndex);
            }
        }

        return -1;
    }

    public static void swap(int indexOriginal, int indexFinal, int[] swapArr) {
        int original = swapArr[indexOriginal];
        swapArr[indexOriginal] = swapArr[indexFinal];
        swapArr[indexFinal] = original;
    }

    public static void displayArr(int[] arr) {

        System.out.print("\nArr: [");
        for (int count = 0; count < arr.length; count++) {
            System.out.print(arr[count]);
            if (count != arr.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("]\n");
    }

    // 0, 1, 1, 2, 3, 5, 8, 13, 
    private static void checkFib() {

        long beforeTime = System.currentTimeMillis();
        System.out.println("" + DynamicProgramming.fibDynamic(19));

        long afterTime = System.currentTimeMillis();

        System.out.println("Total Time: " + (afterTime - beforeTime) / 1000 + " secs");

    }

    public static void checkSort() {
        int[] arr = new int[]{2, 1, 3, 1, 2};
        int count = Sort.splitAndMergeInversionCount(arr, 0, arr.length - 1);
        Sort.displayArr(arr);
        System.out.println(count);
    }

    public static void checkArr() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        // Problems.rotateRight(arr, 2);
        Problems.printArray(arr);
        Problems.leftRotation(arr, 1);
        Problems.printArray(arr);
    }

    private static void checkMedian() {
        // int median = Problems.findMedianArrayUsingMerge(new int[]{2, 28}, new int[]{5, 9});
        // int median = Problems.getMedian(new int[]{17, 20, 28, 50}, 0, 0);

        int median1 = Problems.findMedianArrayUsingMerge(new int[]{2, 3, 25, 40, 60, 61}, new int[]{5, 5, 10, 30, 45, 60});
        int median2 = Problems.findMedianArrayUsingMedians(new int[]{2, 3, 25, 40, 60, 61}, new int[]{5, 5, 10, 30, 45, 60});

        System.out.println("Using Merge: " + median1);
        System.out.println("Using Median: " + median2);

    }

    private static void checkLinkedList() {

        PLinkedList list = new PLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        // list.insert(50);

        list.print();

        list.reverseList();

        list.print();

    }

    private static void checkBST() {
        PBinarySearchTree tree = new PBinarySearchTree();
//        tree.insert(40);
//        tree.insert(20);
//        tree.insert(60);
//        tree.insert(50);
//        tree.insert(65);
//        tree.insert(52);
//        tree.insert(62);
//        tree.insert(68);
//        tree.insert(53);
//        tree.insert(54);
//        tree.insert(55);

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

        System.out.println("Is Sum Tree; " + (tree.isSumTree() ? "Yes" : "No"));

    }
}
