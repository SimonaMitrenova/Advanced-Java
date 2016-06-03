package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        while (true){
            String command = scanner.nextLine();
            if (command.equals("end")){
                break;
            }

            switch (command){
                case "add":
                    executeCommand(numbers, x -> x + 1);
                    break;

                case "subtract":
                    executeCommand(numbers, x -> x -1);
                    break;

                case "multiply":
                    executeCommand(numbers, x -> x * 2);
                    break;

                case "print":
                    executeConsumerCommand(numbers, e -> System.out.printf("%d ", e));
                    System.out.println();
                    break;

                default:
                    break;
            }
        }

    }
    private static void executeCommand(int[] numbers, Function<Integer, Integer> func){
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = func.apply(numbers[i]);
        }
    }

    private static void executeConsumerCommand(int[] numbers, Consumer<Integer> consumer){
        for (int number : numbers) {
            consumer.accept(number);
        }
    }
}
