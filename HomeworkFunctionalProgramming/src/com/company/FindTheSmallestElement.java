package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Function<int[], Integer> findMin = (collection) -> {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < collection.length; i++) {
                if (collection[i] <= min){
                    min = collection[i];
                    minIndex = i;
                }
            }
            return minIndex;
        };

        int result = findMin.apply(numbers);
        System.out.println(result);
    }
}
