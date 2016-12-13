/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problems;

/**
 *
 * @author prateek.kesarwani
 */
public class Bits {

    /**
     * Find integer occuring only once, in array where integer occurs twice.
     * This could be extended out to an integer occuring odd no. of times in
     * array of integers occuring even no. of time.
     *
     * Please se the video once as well, to grasp the concept.
     * https://www.hackerrank.com/challenges/ctci-lonely-integer
     *
     * This is resolved using property of XOR operator, where which when applied
     * on two values operator oring exclusively(so if both 0 then 0, both 1 then
     * 0, but both different then 1).
     *
     * Truth Table of XOR
     *
     * - 0 1
     *
     * 0 0 1
     *
     * 1 1 0
     *
     * @param a
     * @return
     */
    public static int lonelyInteger(int[] a) {
        int result = 0;
        for (int index = 0; index < a.length; index++) {
            result ^= a[index];
        }
        return result;
    }

}
