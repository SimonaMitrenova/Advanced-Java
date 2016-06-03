package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetAscii = Integer.parseInt(scanner.nextLine());
        List<String> names = Arrays.asList(scanner.nextLine().split("\\s+"));
        BiFunction<String, Integer, Boolean> checkForAscii = (String name, Integer targetSum) -> {
                int totalSum = 0;
                for (int i = 0; i < name.length(); i++) {
                    totalSum += name.charAt(i);
                }
                return totalSum >= targetSum;
            };

        TriFunction<Integer, BiFunction<String, Integer, Boolean>, List<String>, String> findFirstOccurency =
                (Integer targetSum, BiFunction<String, Integer, Boolean> biFunction, List<String> collection) -> {
                for (String name : collection) {
                    if (biFunction.apply(name, targetSum)){
                        return name;
                    }
                }
                return "";
            };

        String result = findFirstOccurency.apply(targetAscii, checkForAscii, names);
        System.out.println(result);
    }
}

@FunctionalInterface
interface TriFunction<T1, T2, T3, TR> {
    TR apply(T1 first, T2 second, T3 third);
}
