package proplemsolving;

/**
 *
 * @author prateek.kesarwani
 */
public class ProplemSolving {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] arr = new int[]{10, 5, 3, 6, 7};

        displayArr(arr);

        quickSort(arr);

        displayArr(arr);
    }

    /**
     ************* Sorting ***************
     *
     * Adaptive vs Non-Adaptive: Adaptive sort takes advantage of presortedness
     * in its account. Stable vs Unstable: Stable sort maintains the sequence of
     * similar elements. Inplace vs Not inplace: Inplace sort don't use any
     * extra space(Eg. Bubble Sort)
     *
     */
    public static void quickSort(int[] arr) {

        partition(arr, 0, arr.length - 1);

    }

    public static void partition(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = end;
        int smallerIndex = -1;

        // end-1 because end is pivot itself.
        for (int count = start; count < end - 1; count++) {

            if (arr[count] < arr[pivot]) {
                smallerIndex++;
                swap(count, pivot, arr);
            }

        }

        // Swap pivot with correct positon index, as some element is smaller than pivot
        smallerIndex++;
        if (arr[pivot] <= arr[smallerIndex]) {
            swap(smallerIndex, end, arr);
        }

        partition(arr, start, smallerIndex);
        partition(arr, smallerIndex + 2, end);

    }

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

    public static int kthLargest(int[] arr, int k) {
        if (k > arr.length || k < 1) {
            return -1;
        }

        // First make the heap
        for (int index = arr.length / 2; index >= 0; index--) {
            maxHeapify(arr, index, arr.length - 1);
        }

        for (int index = arr.length - 1; index >= arr.length - k; index--) {
            swap(0, index, arr);
            maxHeapify(arr, 0, index - 1);
        }

        return arr[arr.length - k];
    }

    public static void ascendingHeapSort(int[] arr) {

        // First make the heap
        for (int index = arr.length / 2; index >= 0; index--) {
            maxHeapify(arr, index, arr.length - 1);
        }

        for (int index = arr.length - 1; index > 0; index--) {
            swap(0, index, arr);
            maxHeapify(arr, 0, index - 1);
        }
    }

    /**
     * Max-Heapify
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

    public static void mergeSort(int[] arr) {

        splitAndMerge(arr, 0, arr.length - 1);

    }

    // 2, 4, 3, 5, 7
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
     * 7, 6, 5
     */
    public static void insertionSort(int[] arr) {

        for (int sortedIndex = 0; sortedIndex < arr.length; sortedIndex++) {
            for (int currIndex = sortedIndex; currIndex > 0; currIndex--) {
                if (arr[currIndex] < arr[currIndex - 1]) {
                    swap(currIndex, currIndex - 1, arr);
                } else {
                    break;
                }
            }
        }
    }

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
