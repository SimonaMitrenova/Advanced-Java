package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        Scanner scan = new Scanner(line);

        List<Integer> numbers = new ArrayList<>();

        while (scan.hasNextInt()) {
            Integer n = scan.nextInt();
            numbers.add(n);
        }

        applyFunction(numbers, findMin);
    }
    private static void applyFunction(List<Integer> numbers, Function<List<Integer>, Integer> funk){
        Integer result = funk.apply(numbers);
        if (result != null){
            System.out.println(result);
        }
    }

    private static Function<List<Integer>, Integer> findMin = new Function<List<Integer>, Integer>()  {

        @Override
        public Integer apply(List<Integer> integers) {
            if (integers.size() == 0){
                return null;
            }

            int minValue = Integer.MAX_VALUE;
            for (Integer integer : integers) {
                if (minValue > integer){
                    minValue = integer;
                }
            }
            return minValue;
        }
    };
}
