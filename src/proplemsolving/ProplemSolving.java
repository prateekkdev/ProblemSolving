package proplemsolving;

import algos.Sort;
import type.PBinarySearchTree;
import type.integer.PLinkedList;
import Problems.DynamicProgramming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author prateek.kesarwani
 */
public class ProplemSolving {

    public static void findLongestParenthesicSubstring() throws Exception {
        //Scanner
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // Store last matched index
        long length = str.length(), mLength = 0;
        int last = -1;
        if (length == 0 || length == 1) {
            System.out.println(0);
            return;
        }

        // Storing index of '(' in stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (stack.isEmpty()) {
                // Stack empty, we found a valid combo, update last.
                last = i;
            } else {
                stack.pop();
                // Complete valid combo found.
                if (stack.isEmpty()) {
                    mLength = Math.max(mLength, i - last);
                } else {
                    mLength = Math.max(mLength, i - stack.peek());
                }
            }
        }
        System.out.println(mLength);
    }

    // Working in O(n) time.
    public static void keepIDelJ(String[] arr, int i, int j) {

        int iStart = 1, jStart = 1;

        for (int count = 0; count < arr.length; count++) {
            if (iStart++ <= i) {

                // Checks to print or skip comma
                if (count == 0) {
                    System.out.print(arr[count]);
                } else {
                    System.out.print("," + arr[count]);
                }
            } else if (jStart++ <= j) {
                if (jStart > j) {
                    iStart = 1;
                    jStart = 1;
                }
            }
        }
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) throws Exception {
//
//        //Scanner
//        Scanner s = new Scanner(System.in);
//        String str = s.next();
//
//        // Store last matched index
//        int length = str.length(), mLength = 0;
//        int last = -1;
//        if (length == 0 || length == 1) {
//            System.out.println(0);
//        }
//
//        // Storing index of '(' in stack
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < length; i++) {
//            if (str.charAt(i) == '(') {
//                stack.push(i);
//            } else if (stack.isEmpty()) {
//                // Stack empty, we found a valid combo, update last.
//                last = i;
//            } else {
//                stack.pop();
//                // Complete valid combo found.
//                if (stack.isEmpty()) {
//                    mLength = Math.max(mLength, i - last);
//                } else {
//                    mLength = Math.max(mLength, i - stack.peek());
//                }
//            }
//        }
//        System.out.println(mLength);
//    }
    public static void findParen(String str) {

        int count = 0;
        for (int curr = 0; curr < str.length(); curr++) {

            char c = str.charAt(curr);

//            if (c =  == '(') {
//                count++;
//            }
        }

    }

    private static int getLongestLenByStack(String s) {
        // Store last matched index
        int length = s.length(), mLength = 0;
        int last = -1;
        if (length == 0 || length == 1) {
            return 0;
        }

        // Storing index of '(' in stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (stack.isEmpty()) {
                // Stack empty, we found a valid combo, update last.
                last = i;
            } else {
                stack.pop();
                // Complete valid combo found.
                if (stack.isEmpty()) {
                    mLength = Math.max(mLength, i - last);
                } else {
                    mLength = Math.max(mLength, i - stack.peek());
                }
            }
        }
        return mLength;
    }

    public static void playSnakeGame() {

        Scanner scanner = new Scanner(System.in);

        int totalTestCases = scanner.nextInt();

        for (int count = 0; count < totalTestCases; count++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            // We are working with 0 based.
            int x = scanner.nextInt() - 1;

            // We are working with 0 based.
            int y = scanner.nextInt() - 1;
            int l = scanner.nextInt();

            // Could have done with String itself, but for better understanding.
            String direction = scanner.next();

            SnakeGame snakeGame = new SnakeGame(N, M, new BoardPosition(x, y), l, getDirectionFromString(direction));
        }
    }

    // Just a utility function to get direction enum, for better understanding.
    private static Direction[] getDirectionFromString(String direction) {

        Direction[] arr = new Direction[direction.length()];

        for (int index = 0; index < direction.length(); index++) {
            char d = direction.charAt(index);
            arr[index] = d == 'L' ? Direction.LEFT : d == 'R' ? Direction.RIGHT : d == 'U' ? Direction.UP : Direction.DOWN;
        }
        return arr;

    }
}

enum Direction {
    LEFT, RIGHT, UP, DOWN;
}

/**
 *
 * @author prateek.kesarwani
 */
class BoardPosition {

    private int x;
    private int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

/**
 *
 * @author prateek.kesarwani
 */
class SnakeGame {

    int boardSizeX;
    int boardSizeY;
    int snakeLength;
    BoardPosition snakeHead;

    Queue<BoardPosition> snakePath;

    public SnakeGame(int boardSizeX, int boardSizeY, BoardPosition tail, int snakeLength, Direction[] directions) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.snakeLength = snakeLength;

        // This snakeHead is before L - 1 steps.
        this.snakeHead = new BoardPosition(tail.getX(), tail.getY());

        snakePath = new LinkedList<>();

        // Now we are adding snake path for L - 1 steps(ie length)
        buildSnakePath(directions);

        // Pass direction for last movement.
        moveUntilCollision(directions[directions.length - 1]);
    }

    private void buildSnakePath(Direction[] directions) {

        BoardPosition pos = null;
        for (Direction direction : directions) {
            pos = getNextPosition(direction);
            snakePath.add(pos);
        }

        // Head becomes the front of queue(ie top position)
        snakeHead = pos;
    }

    private BoardPosition getNextPosition(Direction direction) {
        int x = snakeHead.getX();
        int y = snakeHead.getY();

        switch (direction) {
            case LEFT:
                x = x - 1;
                break;
            case RIGHT:
                x = x + 1;
                break;
            case UP:
                y = y + 1;
                break;
            case DOWN:
                y = y - 1;
                break;
        }
        return new BoardPosition(x, y);
    }

    private boolean isWallCollision(BoardPosition pos) {
        if (pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() <= boardSizeX && pos.getY() <= boardSizeY) {
            return false;
        }
        return true;
    }

    private boolean isSelfCollision(BoardPosition pos) {
        if (snakePath.contains(pos)) {
            return true;
        } else {
            return false;
        }
    }

    private void moveUntilCollision(Direction lastDirection) {

        for (int countMoves = 0; true; countMoves++) {

            BoardPosition next = getNextPosition(lastDirection);

            if (isWallCollision(next)) {
                // Print Wall
                System.out.println("WALL " + countMoves);
                return;
            }

            snakePath.remove();

            if (isSelfCollision(next)) {
                // Print Body
                System.out.println("BODY " + countMoves);
                return;
            }

            // If no collision, then move snake and continue
            snakePath.add(next);
        }
    }

    public static void printNumbers(String str) {
        StringBuilder numStr = null;
        for (int index = 0; index < str.length(); index++) {

            char current = str.charAt(index);
            if (current >= '0' && current <= '9') {
                if (numStr == null) {
                    numStr = new StringBuilder();
                }
                numStr.append(current);
            } else if (numStr != null) {
                System.out.println(numStr);
                numStr = null;
            }
        }
    }

    public static void playSnakeGame() {

        Scanner scanner = new Scanner(System.in);

        int totalTestCases = scanner.nextInt();

        for (int count = 0; count < totalTestCases; count++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            // We are working with 0 based.
            int x = scanner.nextInt() - 1;

            // We are working with 0 based.
            int y = scanner.nextInt() - 1;
            int l = scanner.nextInt();

            // Could have done with String itself, but for better understanding.
            String direction = scanner.next();

            SnakeGame snakeGame = new SnakeGame(N, M, new BoardPosition(x, y), l, getDirectionFromString(direction));
        }
    }

    // Just a utility function to get direction enum, for better understanding.
    private static Direction[] getDirectionFromString(String direction) {

        Direction[] arr = new Direction[direction.length()];

        for (int index = 0; index < direction.length(); index++) {
            char d = direction.charAt(index);
            arr[index] = d == 'L' ? Direction.LEFT : d == 'R' ? Direction.RIGHT : d == 'U' ? Direction.UP : Direction.DOWN;
        }
        return arr;
    }

    public static int solution(int[] A) {

        // Quicksort array in O(nlogn) time
        java.util.Arrays.sort(A);

        int min = Integer.MAX_VALUE;
        // Now find the minimum in O(n) time
        for (int index = 0; index < A.length - 1; index++) {

            int diff = Math.abs(A[index] - A[index + 1]);

            if (diff < min) {
                min = diff;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return -2;
        } else if (min > 100000000) {
            return -1;
        }

        return min;
    }

    public static boolean solutionA(int[] A) {

        for (int i = 1; i < A.length; i++) {
            // Look for an inverted adjacent pair.
            if (A[i - 1] <= A[i]) {
                continue;
            }
            int x = A[i - 1],
                    left = i - 1;
            // If x is one of a sequence of identical elements, take the leftmost.
            while (left - 1 >= 0 && A[left - 1] == x) {
                --left;
            }
            // Scan past the inverted pair for the earliest element no smaller than x.
            for (++i; i < A.length; ++i) {
                if (A[i] >= x) {
                    break;  // If we never break here, i will be equal to A.length.
                }
            }
            // Let y be the element before the earliest element no smaller than x.
            int right = i - 1,
                    y = A[right];
            // Swap x and y.
            A[left] = y;
            A[right] = x;
            // Is the array sorted now?
            for (i = (left == 0 ? 1 : left); i < A.length; ++i) {
                if (A[i - 1] > A[i]) {
                    return false;
                }
            }
            return true;  // One swap was enough to sort the array.
        }
        return true;  // The array is already sorted.

    }

    public static boolean sortByOneSwap(int arr[], int n) {
        boolean isSwapped = false;

        // Travers the given array from rightmost side
        for (int i = n - 1; i > 0; i--) {
            // Check if arr[i] is not in order
            if (arr[i] < arr[i - 1]) {
                // Find the other element to be
                // swapped with arr[i]
                int j = i - 1;
                while (j >= 0 && arr[i] < arr[j]) {
                    j--;
                }

                isSwapped = true;

                // Swap the pair
                swap(i, j + 1, arr);
                break;
            }
        }

        // Check if making one swap sorted the whole array
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }

        return isSwapped;
    }

    public static void swap(int indexOriginal, int indexFinal, int[] swapArr) {
        int original = swapArr[indexOriginal];
        swapArr[indexOriginal] = swapArr[indexFinal];
        swapArr[indexFinal] = original;
    }

    public static int solution1(int[] A) {

        if (A.length == 0) {
            return -1;
        } else if (A.length == 1) {
            return 0;
        }

        long[] arrLeft = new long[A.length];
        arrLeft[0] = A[0];
        for (int index = 1; index < arrLeft.length; index++) {
            arrLeft[index] = arrLeft[index - 1] + A[index];
        }

        long[] arrRight = new long[A.length];
        arrRight[A.length - 1] = A[A.length - 1];
        for (int index = arrRight.length - 2; index >= 0; index--) {
            arrRight[index] = arrRight[index + 1] + A[index];
        }

        for (int index = 0; index < arrLeft.length; index++) {
            if (arrLeft[index] == arrRight[index]) {
                return index;
            }
        }

        return -1;
    }

    public static void checkForBST() {
        PBinarySearchTree.Node node = buildBinaryTree();

        PBinarySearchTree binarySearchTree = new PBinarySearchTree();
        binarySearchTree.root = node;

        // binarySearchTree.boundaryTraversalModular();
        binarySearchTree.traverseInorder();
        System.out.println();
        binarySearchTree.traverseInorderIterative();
    }

    public static PBinarySearchTree.Node buildBinaryTree() {
        PBinarySearchTree.Node node = new PBinarySearchTree.Node(5);
        node.left = new PBinarySearchTree.Node(10);
        node.left.left = new PBinarySearchTree.Node(40);
        node.left.left.left = new PBinarySearchTree.Node(11);
        node.left.right = new PBinarySearchTree.Node(25);
        node.left.right.left = new PBinarySearchTree.Node(15);
        node.left.right.left.right = new PBinarySearchTree.Node(39);

        node.right = new PBinarySearchTree.Node(20);
        node.right.left = new PBinarySearchTree.Node(80);
        node.right.left.right = new PBinarySearchTree.Node(32);
        node.right.right = new PBinarySearchTree.Node(90);
        node.right.right.right = new PBinarySearchTree.Node(28);
        node.right.right.right.left = new PBinarySearchTree.Node(23);

        return node;
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

    public static void swap1(int indexOriginal, int indexFinal, int[] swapArr) {
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

        list.reverseListRecursive();

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
