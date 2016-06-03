package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

public class InfernoIII {
    private static int[] numbers;
    public static void main(String[] args) {
        HashMap<String, Predicate<Integer>> filter = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        while (true){
            String line = scanner.nextLine();
            if (line.equals("Forge")){
                break;
            }
            String[] tokens = line.split(";");
            String command = tokens[0];
            String modifier = tokens[1];
            int variable = Integer.parseInt(tokens[2]);

            switch (command){
                case "Exclude":
                    Predicate<Integer> predicate = buildPredicate(modifier, variable);
                    filter.put(modifier + variable, predicate);
                    break;

                case "Reverse":
                    filter.remove(modifier + variable);
                    break;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            boolean shouldPrint = false;
            for (Predicate<Integer> predicate : filter.values()) {
                if (predicate.test(i)){
                    shouldPrint = true;
                    break;
                }
            }
            if (!shouldPrint){
                System.out.printf("%d ", numbers[i]);
            }
        }
    }

    private static Predicate<Integer> buildPredicate(String modifier, int variable) {
        switch (modifier){
            case "Sum Left":
                return x -> (x - 1 < 0 ? 0 : numbers[x - 1]) + numbers[x] == variable;

            case "Sum Right":
                return x -> (x + 1 >= numbers.length ? 0 : numbers[x + 1]) + numbers[x] == variable;

            case  "Sum Left Right":
                return x -> (x - 1 < 0 ? 0 : numbers[x - 1]) + numbers[x] + (x + 1 >= numbers.length ? 0 : numbers[x + 1]) == variable;

            default:
                return null;
        }
    }
}
