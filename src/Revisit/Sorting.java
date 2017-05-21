/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revisit;

/**
 *
 * @author prateek.kesarwani
 */
public class Sorting {

    // 10, 5, 3, 2, 8
    // 5, 10, 3, 2, 8
    // 5, 10, 10 -> 3, 5, 10
    public static void insertionSort(int[] arr) {

        for (int i = 2; i < arr.length; i++) {

            int curr = arr[i];
            for (int j = i - 1; j >= 0; j--) {

                if (arr[j] > curr) {
                    arr[j + 1] = arr[j];
                    if (j == 0) {
                        arr[j] = curr;
                    }
                } else {
                    arr[j + 1] = curr;
                    break;
                }
            }
        }
    }

    //******** HEAP SORT ***********
    private static int left(int index) {
        return index * 2 + 1;
    }

    private static int right(int index) {
        return index * 2 + 2;
    }

    private static void maxHeapify(int[] arr, int curr, int last) {

        int maxIndex = curr;
        int left = left(curr);
        int right = right(curr);

        if (left <= last && arr[left] > arr[curr]) {
            maxIndex = left;
        }

        if (right <= last && arr[right] > arr[curr]) {
            maxIndex = right;
        }

        if (maxIndex != curr) {
            algos.Sort.swap(maxIndex, curr, arr);
            maxHeapify(arr, maxIndex, last);
        }
    }

    
    private static void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, arr.length - 1);
        }
    }

    // 4, 3, 2, 1
    public static void heapSort(int[] arr) {

        buildHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            algos.Sort.swap(0, i, arr);
            maxHeapify(arr, 0, i - 1);
        }

    }

    //******************************
}
