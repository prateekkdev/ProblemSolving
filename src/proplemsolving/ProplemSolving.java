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

        int[] arr = new int[]{1, 5, 7, 1, 5, 6};

        displayArr(arr);

        // insertionSort(arr);
        merge(arr, 0, 3, 5);

        displayArr(arr);
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

    /**
     ************* Sorting ***************
     *
     * Adaptive vs Non-Adaptive: Adaptive sort takes advantage of presortedness
     * in its account. Stable vs Unstable: Stable sort maintains the sequence of
     * similar elements. Inplace vs Not inplace: Inplace sort don't use any
     * extra space(Eg. Bubble Sort)
     *
     */
    public static void swap(int indexOriginal, int indexFinal, int[] swapArr) {
        int original = swapArr[indexOriginal];
        swapArr[indexOriginal] = swapArr[indexFinal];
        swapArr[indexFinal] = original;
    }

    public static void merge(int[] arr, int left, int mid, int right) {

        int[] leftArr = new int[mid - left];
        for (int index = 0; index < leftArr.length; index++) {
            leftArr[index] = arr[index + left];
        }

        int[] rightArr = new int[right - mid + 1];
        for (int index = 0; index < rightArr.length; index++) {
            rightArr[index] = arr[mid + index];
        }

        int leftCount = 0, rightCount = 0, mainCount = left;
        while (leftCount < leftArr.length && rightCount < rightArr.length) {
            if (leftArr[leftCount] <= rightArr[rightCount]) {
                arr[mainCount++] = leftArr[leftCount++];
            } else if (leftArr[leftCount] > rightArr[rightCount]) {
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
     * Max-heapify
     *
     * @param arr
     * @param sortedIndex
     */
    public static void heapify(int[] arr, int sortedIndex) {

    }

    public static void heapSort(int[] arr) {

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
}
