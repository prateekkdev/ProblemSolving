
import Revisit.Sorting;
import RxLearn.RxProblem1;
import algos.Sort;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Main {

    /**
     *
     *
     * ============================ Flattenlist 15 minutes *
     * ============================
     *
     *
     * Devise a function that accepts an arbitrarily-nested array with elements
     * of arbitrary types, and returns a flattened version of it. Do not solve
     * the task using a built-in function that can accomplish the whole task on
     * its own.
     *
     * Example: ["This is a string", 1, 2, [3], [4, [5, 6]], [[7]], 8, "[10,
     * 11]"] -> ["This is a string", 1, 2, 3, 4, 5, 6, 7, 8, "[10, 11]"]
     */
    public static void main(String args[]) throws Exception {
        
        // int[] arr = new int[]{10, 5, 3, 2};
        int[] arr = new int[]{4, 3, 2, 8, 1};
        
        Sort.displayArr(arr);
        
        Sort.ascendingHeapSort(arr);
        //Sorting.heapSort(arr);
        
        Sort.displayArr(arr);

        // RxProblem1 problem = new RxProblem1();
    }
    
    public static int suffixMatching(String str, int index) {
        
        int suffix = 0;
        char[] strArr = str.toCharArray();
        char[] suffixArr = str.substring(0, index).toCharArray();
        int last = strArr.length - 1;

        /*
        for (int count = 0; ) {
            return count;
        }
         */
        return -1;
    }
    
    public static void printNumbers(String str) {
        
        if (str == null || str.equals("")) {
            return;
        }
        
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
        
        if (numStr != null) {
            System.out.println(numStr);
        }
    }
    
    public static void checkFlatList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("This is a string");
        list.add(1);
        list.add(2);
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(3);
        
        list.add(list2);
        
        ArrayList<Object> list3 = new ArrayList<>();
        list3.add(4);
        ArrayList<Object> list4 = new ArrayList<>();
        list4.add(5);
        list4.add(6);
        list4.add(list3);
        
        list.add(list4);

        // Test
        ArrayList<Object> result2 = new ArrayList<>();
        getFlatList(list, result2);
        
        System.out.println("");
        
    }

    /**
     * Asked in Toptal Technical Interview
     *
     * @param arr
     * @param result
     */
    public static void getFlatList(ArrayList<Object> arr, ArrayList<Object> result) {
        
        for (Object obj : arr) {
            if (obj instanceof ArrayList) {
                getFlatList((ArrayList<Object>) obj, result);
            } else {
                result.add(obj);
            }
        }
        
    }
    
    static ArrayList<Object> result = new ArrayList<>();
    
    public static void getFlatList(ArrayList<Object> arr) {
        
        for (Object obj : arr) {
            if (obj instanceof ArrayList) {
                getFlatList((ArrayList<Object>) obj);
            } else {
                result.add(obj);
            }
        }

        // return result;
    }
}
