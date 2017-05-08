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
public class ProblemConcepts {

    public static int mul_russian(int x, int y) {
        int z = 0;
        for (; x > 0; x = x / 2, y = y * 2) {
            if (x % 2 == 1) {
                z = z + y;
            }
        }
        return z;
    }

    public static int mul_russian_bin(int x, int y) {
        int z = 0;
        for (; x > 0; x = x >> 1, y = y << 1) {
            z += x % 2 == 1 ? y : 0;
        }
        return z;
    }

    public static int mul_russian_recur(int x, int y) {
        if (x == 0) {
            return 0;
        }
        int z = mul_russian_recur(x / 2, y * 2);
        return x % 2 == 1 ? y + z : z;
    }

    public static int mul_normal(int x, int y) {
        int z = 0;

        for (; x > 0; x--) {
            z += y;
        }

        return z;
    }
}
