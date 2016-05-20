package com.company;

import java.util.Scanner;

public class RecursiveFibonacci {
    private static long[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        dp = new long[n + 2];
        dp[1] = 1;
        dp[2] = 1;
        long result = getFibonacci(n);
        System.out.println(result);
    }

    private static long getFibonacci(int n) {
        if (dp[n] != 0){
            return dp[n];
        }
        dp[n] = getFibonacci(n - 1) + getFibonacci(n - 2);
        return dp[n];
    }
}
