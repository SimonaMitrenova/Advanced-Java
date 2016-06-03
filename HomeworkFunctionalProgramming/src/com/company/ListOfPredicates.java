package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class ListOfPredicates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] divisors = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).distinct().toArray();

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                for (int divisor : divisors) {
                    if (integer % divisor != 0){
                        return false;
                    }
                }
                return true;
            }
        };

        for (int i = 1; i <= n; i++) {
            if (predicate.test(i)){
                System.out.printf("%d ", i);
            }
        }
    }
}
