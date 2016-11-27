package proplemsolving;

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
        checkBST();
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

    /**
     * Count sort is an integer sorting algorithm which works on keys between a
     * given range. It is best suited for small integers set as it results into
     * O(n+k), n being the numbers in the set and k it's range(from 0 till max
     * value) and takes O(k) additional space. So, if k is O(n), CountSort
     * becomes linear sorting, which is better than comparison based sorting
     * algorithms that have O(nlogn) time complexity.
     *
     * @param arr
     * @param lower
     * @param upper
     */
    public static void countingSort(int[] arr, int range) {

        int[] rangeArray = new int[range];

        // Now increment the index in range array, when we encounter that index in original array.
        for (int index = 0; index < arr.length; index++) {
            rangeArray[arr[index]] = rangeArray[arr[index]] + 1;
        }

        int originalCount = 0;
        for (int index = 0; index < rangeArray.length; index++) {
            for (int repeatCount = 1; repeatCount <= rangeArray[index]; repeatCount++) {
                arr[originalCount++] = index;
            }
        }
    }

    /**
     * For sorting array with values ranging from low to high(Both inclusive)
     * This reduces the complexity a little, by considering only range, rather
     * than 0 to max value.
     *
     * Worst Case: O(n+k), here k is range
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void countingSort(int[] arr, int low, int high) {

        int range = high - low + 1;

        int[] rangeArray = new int[range];

        // Now increment the index in range array, when we encounter that index in original array.
        for (int index = 0; index < arr.length; index++) {
            rangeArray[arr[index] - low] = rangeArray[arr[index] - low] + 1;
        }

        int originalCount = 0;
        for (int index = 0; index < rangeArray.length; index++) {
            for (int repeatCount = 1; repeatCount <= rangeArray[index]; repeatCount++) {
                arr[originalCount++] = index + low;
            }
        }
    }

    /**
     ************* Sorting ***************
     *
     * Adaptive vs Non-Adaptive: Adaptive sort takes advantage of presortedness
     * in its account. Stable vs Unstable: Stable sort maintains the sequence of
     * similar elements. Inplace vs Not inplace: Inplace sort don't use any
     * extra space(Eg. Bubble Sort)
     *
     * QuickSort, MergeSort, HeapSort, etc are comparison based sorting
     * algorithms. There best complexity can't be more than O(nLogn)
     *
     */
    /**
     *
     * Quick sort is based on the divide-and-conquer approach based on the idea
     * of choosing one element as a pivot element and partitioning the array
     * around it such that: Left side of pivot contains all the elements that
     * are less than the pivot element. Right side contains all elements greater
     * than the pivot. It reduces the space complexity and removes the use of
     * the auxiliary array that is used in merge sort. Selecting a random pivot
     * in an array results in an improved time complexity in most of the cases.
     *
     * @param arr
     * @param start
     * @param end
     *
     * Use randpartition() instead of partition() function in quicksort()
     * function to reduce the time complexity of this algorithm. Complexity The
     * worst case time complexity of this algorithm is O(N2), but as this is
     * randomized algorithm, its time complexity fluctuates between O(N2) and
     * O(NlogN) and mostly it comes out to be O(nlogn)
     */
    public static void quickSort(int[] arr, int start, int end) {

        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {

        int pivot = end;
        int smallerIndex = start - 1;

        // end-1 because end is pivot itself.
        for (int count = start; count <= end - 1; count++) {

            if (arr[count] <= arr[pivot]) {
                smallerIndex++;
                swap(smallerIndex, count, arr);
            }
        }

        // Swap pivot with correct positon index
        // If no element is smaller, then swap with first index
        // If all the elements are small(all swaps occured in above loop above), now swap with self
        swap(smallerIndex + 1, end, arr);

        return smallerIndex + 1;
    }

    public static void quickSort(int[] arr) {
        partitionRecursive(arr, 0, arr.length - 1);
    }

    public static void partitionRecursive(int[] arr, int start, int end) {

        if (start < end) {

            int pivot = end;
            int smallerIndex = start - 1;

            // end-1 because end is pivot itself.
            for (int count = start; count <= end - 1; count++) {

                if (arr[count] <= arr[pivot]) {
                    smallerIndex++;
                    swap(count, smallerIndex, arr);
                }
            }

            swap(smallerIndex + 1, end, arr);

            partitionRecursive(arr, start, smallerIndex);
            partitionRecursive(arr, smallerIndex + 2, end);
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

    public static void descendingHeapSort(int[] arr) {

        // First make the heap
        for (int index = arr.length / 2; index >= 0; index--) {
            minHeapify(arr, index, arr.length - 1);
        }

        for (int index = arr.length - 1; index > 0; index--) {
            swap(0, index, arr);
            minHeapify(arr, 0, index - 1);
        }
    }

    public static void minHeapify(int[] arr, int rootIndex, int lastIndex) {

        int smallest = rootIndex;
        int leftChild = getLeftChild(arr, rootIndex, lastIndex);
        int rightChild = getRightChild(arr, rootIndex, lastIndex);

        if (leftChild != -1 && arr[smallest] > arr[leftChild]) {
            smallest = leftChild;
        }

        if (rightChild != -1 && arr[smallest] > arr[rightChild]) {
            smallest = rightChild;
        }

        if (smallest != rootIndex) {
            swap(smallest, rootIndex, arr);
            minHeapify(arr, smallest, lastIndex);
        }
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
     *
     * Heaps can be used in sorting an array. In max-heaps, maximum element will
     * always be at the root. Heap Sort uses this property of heap to sort the
     * array.
     *
     * max_heapify has complexity O(logN), build_maxheap has complexity O(N) We
     * run max_heapify N−1 times in heap_sort function, therefore complexity of
     * heap_sort function is O(NlogN).
     *
     * @param arr
     */
    public static void ascendingHeapSort(int[] arr) {

        // Build Max Heap(Could be done taking aux array as well, where each insertion has complexity of log n, and n insertions have complexity 
        // First make the heap
        // Complexity of n(The main idea is that in the build_heap algorithm the actual heapify cost is not O(log n)for all elements.)
        /**
         * Good explanation here
         * http://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity
         * NOTICE: that after step one, 1/2 of the elements (n/2) are already in
         * the heap, and we didn't even need to call heapify once. Also, notice
         * that only a single element, the root, actually incurs the full log(n)
         * complexity. Essentially, we are executing on the entire array,
         * there's your O(n), and then doing one round of O(log n) and O(n) +
         * O(log n) = O(n)
         */
        for (int index = arr.length / 2; index >= 0; index--) {
            maxHeapify(arr, index, arr.length - 1);
        }

        // Complexity of nlogn
        for (int index = arr.length - 1; index > 0; index--) {
            swap(0, index, arr);
            maxHeapify(arr, 0, index - 1);
        }
    }

    /**
     * Max-Heapify
     *
     * max_heapify has complexity O(logn)
     *
     * @param arr
     * @param sortedIndex
     */
    public static void maxHeapify(int[] arr, int rootIndex, int lastIndex) {

        int largest = rootIndex;
        int leftChild = getLeftChild(arr, rootIndex, lastIndex);
        int rightChild = getRightChild(arr, rootIndex, lastIndex);

        if (leftChild != -1 && arr[largest] < arr[leftChild]) {
            largest = leftChild;
        }

        if (rightChild != -1 && arr[largest] < arr[rightChild]) {
            largest = rightChild;
        }

        if (largest != rootIndex) {
            swap(largest, rootIndex, arr);
            maxHeapify(arr, largest, lastIndex);
        }
    }

    public static int getLeftChild(int[] arr, int rootIndex, int lastIndex) {
        int leftChildIndex = 2 * rootIndex + 1;

        if (leftChildIndex <= lastIndex) {
            return leftChildIndex;
        } else {
            return -1;
        }
    }

    public static int getRightChild(int[] arr, int rootIndex, int lastIndex) {
        int rightChildIndex = 2 * rootIndex + 2;

        if (rightChildIndex <= lastIndex) {
            return rightChildIndex;
        } else {
            return -1;
        }
    }

    /**
     * Merge sort is a divide-and-conquer algorithm based on the idea of
     * breaking down a list into several sub-lists until each sublist consists
     * of a single element and merging those sublists in a manner that results
     * into a sorted list.
     *
     * The list of size N is divided into a max of logN parts, and the merging
     * of all sublists into a single list takes O(N) time, the worst case run
     * time of this algorithm is O(NLogN)
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        splitAndMerge(arr, 0, arr.length - 1);
    }

    public static void splitAndMerge(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            splitAndMerge(arr, left, mid);
            splitAndMerge(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {

        int[] leftArr = new int[mid - left + 1];
        for (int index = 0; index < leftArr.length; index++) {
            leftArr[index] = arr[index + left];
        }

        int[] rightArr = new int[right - mid];
        for (int index = 0; index < rightArr.length; index++) {
            rightArr[index] = arr[mid + index + 1];
        }

        int leftCount = 0, rightCount = 0, mainCount = left;
        while (leftCount < leftArr.length && rightCount < rightArr.length) {
            if (leftArr[leftCount] <= rightArr[rightCount]) {
                arr[mainCount++] = leftArr[leftCount++];
            } else {
                arr[mainCount++] = rightArr[rightCount++];
            }
        }

        while (leftCount < leftArr.length) {
            arr[mainCount++] = leftArr[leftCount++];
        }

        while (rightCount < rightArr.length) {
            arr[mainCount++] = rightArr[rightCount++];
        }
    }

    /**
     * Bubble Sort(Sinking Sort)
     *
     * Swapping two adjacent elements repeatedly. In first iteration we have
     * largest number at last
     *
     * Best Case O(n), Average Case O(n^2), Worst Case O(n^2)
     *
     * Could be improved a little by having highest number and lowest number
     * cycle iteratively. That is could have 2 separate loops inside each pass
     * which tracks min and max index.
     */
    public static void bubbleSort(int[] arr) {
        for (int sortedIndex = arr.length - 1; sortedIndex > 0; sortedIndex--) {

            // To improve best case to O(n), else everything would be n^2. 
            // So for already sorted, complexity would be O(n), only one iteration would occur.
            boolean isSorted = true;

            for (int currIndex = 0; currIndex < sortedIndex; currIndex++) {
                if (arr[currIndex] > arr[currIndex + 1]) {
                    swap(currIndex, currIndex + 1, arr);
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    /**
     * Insertion Sort
     *
     * The array is searched sequentially and unsorted items are moved and
     * inserted into sorted sub-list (in the same array).
     *
     * Its average and worst case complexity are of Ο(n^2), Best is O(n)
     *
     */
    public static void insertionSort(int[] arr) {

        // Don't need to start from 0, (0 is already sorted for itself)
        for (int sortedIndex = 1; sortedIndex < arr.length; sortedIndex++) {
            for (int currIndex = sortedIndex; currIndex > 0; currIndex--) {
                if (arr[currIndex] < arr[currIndex - 1]) {
                    swap(currIndex, currIndex - 1, arr);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * This is done without swapping immediately, So, complexity is little
     * better
     *
     * @param arr
     */
    public static void insertionSort1(int[] arr) {

        for (int upperIndex = 1; upperIndex < arr.length; upperIndex++) {
            int moveIndex = upperIndex;
            int element = arr[upperIndex];

            for (; moveIndex > 0; moveIndex--) {
                if (arr[moveIndex - 1] > element) {
                    arr[moveIndex] = arr[moveIndex - 1];
                } else {
                    break;
                }
            }
            arr[moveIndex] = element;
            printArray(arr);
        }
    }

    /**
     * The Selection sort algorithm is based on the idea of finding the minimum
     * or maximum element in an unsorted array and then putting it in its
     * correct position in a sorted array.
     *
     * Complexity: O(n^2)
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        for (int sortedIndex = 0; sortedIndex < arr.length; sortedIndex++) {

            int minIndex = sortedIndex;

            for (int currIndex = sortedIndex; currIndex < arr.length; currIndex++) {
                if (arr[minIndex] > arr[currIndex]) {
                    minIndex = currIndex;
                }
            }
            swap(sortedIndex, minIndex, arr);
        }
    }

    private static void printArray(int[] ar) {
        for (int n : ar) {
            System.out.print(n + " ");
        }
        System.out.println("");
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

    public static void swap(int indexOriginal, int indexFinal, int[] swapArr) {
        int original = swapArr[indexOriginal];
        swapArr[indexOriginal] = swapArr[indexFinal];
        swapArr[indexFinal] = original;
    }
}
