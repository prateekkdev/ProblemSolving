/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type.generic;

import java.util.ArrayList;

/**
 * Properties
 *
 * 1. Indexed access, 2. Dynamic size
 *
 * @author prateek.kesarwani
 *
 * Initialize the array with size 8, then keep on doubling with when gets full.
 *
 */
public class PArrayList<T> {

    private static final int INITIAL_SIZE = 8;

    private T[] arr;

    public PArrayList() {
        arr = (T[]) new Object[INITIAL_SIZE];
    }

    public void add(T data) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        
    }

}
