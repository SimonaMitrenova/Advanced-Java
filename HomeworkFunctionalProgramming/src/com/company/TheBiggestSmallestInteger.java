package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class TheBiggestSmallestInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();

        Function<List<Integer>, Integer> findMin = (collection) -> {
            int min = Integer.MAX_VALUE;
            for (int number : collection) {
                if (number < min){
                    min = number;
                }
            }
            return min;
        };

        Function<List<Integer>, Integer> findMax = (collectin) -> {
            int max = Integer.MIN_VALUE;
            for (int number : collectin) {
                if (number > max){
                    max = number;
                }
            }
            return max;
        };

        CustomFunction<Function<List<Integer>, Integer>, int[], Integer, Integer> triFunction = (function, collection, parity) -> {
            List<Integer> buffer = new ArrayList<>();
            for (int number : collection) {
                if (number % 2 == parity){
                    buffer.add(number);
                }
            }
            if (buffer.size() > 0){
                return function.apply(buffer);
            }
            return null;
        };

        Integer result = 0;

        switch (command){
            case "minEven":
                result = triFunction.apply(findMin, numbers, 0);
                break;

            case "maxEven":
                result = triFunction.apply(findMax, numbers, 0);
                break;

            case "minOdd":
                result = triFunction.apply(findMin, numbers, 1);
                break;

            case "maxOdd":
                result = triFunction.apply(findMax, numbers, 1);
                break;
        }

        System.out.println(result == null ? "" : String.valueOf(result));
    }
}

@FunctionalInterface
interface CustomFunction<T1, T2, T3, TR>{
    TR apply(T1 first, T2 second, T3 third);
}
