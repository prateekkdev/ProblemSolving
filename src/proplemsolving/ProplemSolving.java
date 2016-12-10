package proplemsolving;

import algos.Sort;
import type.PBinarySearchTree;
import type.integer.PLinkedList;

/**
 *
 * @author prateek.kesarwani
 */
public class ProplemSolving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        checkSort();
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
        Problems.reverseArray(arr, 3, 5);
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
