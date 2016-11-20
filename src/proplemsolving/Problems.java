/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proplemsolving;

import java.util.Scanner;

/**
 *
 * @author prateek.kesarwani
 */
public class Problems {

    /**
     * https://www.hackerrank.com/challenges/chocolate-feast
     *
     */
    // Input Format 
    // t - No of trips
    // n - Total money
    // c - Price of each chocolate
    // m - Wrappers needed per chocolate 
    // Constraints
    // 1 <= t <= 1000, 
    // 2 <= n <= 10^5, 
    // 1 <= c <= n, 
    // 2 <= m <= n
    public static void chocolateFeastScanner() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();

            int totalChocolates = chocolateFeast(n, c, m);
            System.out.println(totalChocolates);
        }
    }

    // 45, 2, 3
    public static int chocolateFeast(int n, int c, int m) {

        int currentChocolates = n / c;

        int totalChocolates = currentChocolates;

        while (currentChocolates >= m) {
            totalChocolates = totalChocolates + currentChocolates / m;
            currentChocolates = currentChocolates / m + currentChocolates % m;
        }

        return totalChocolates;
    }

    public static void compareTheTripLetScanner(String[] args) {
        Scanner in = new Scanner(System.in);
        int a0 = in.nextInt();
        int a1 = in.nextInt();
        int a2 = in.nextInt();
        int b0 = in.nextInt();
        int b1 = in.nextInt();
        int b2 = in.nextInt();
    }

    public static void compareTheTriplet(int a0, int a1, int a2, int b0, int b1, int b2) {
        int alice = 0;
        int bob = 0;

        if (a0 > b0) {
            alice++;
        } else if (a0 < b0) {
            bob++;
        }

        if (a1 > b1) {
            alice++;
        } else if (a1 < b1) {
            bob++;
        }

        if (a2 > b2) {
            alice++;
        } else if (a2 < b2) {
            bob++;
        }
    }
}