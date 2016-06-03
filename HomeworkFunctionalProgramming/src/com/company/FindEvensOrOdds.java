package com.company;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FindEvensOrOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] range = scanner.nextLine().split("\\s+");
        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);
        int[] numbers = IntStream.rangeClosed(start, end).toArray();
        String type = scanner.nextLine();
        if (type.equals("odd")){
            filter(numbers, x -> x % 2 != 0);
        } else {
            filter(numbers, x -> x % 2 == 0);
        }
    }
    public static void filter(int[] numbers, Predicate<Integer> predicate){
        for (int number : numbers) {
            if (predicate.test(number)){
                System.out.printf("%d ", number);
            }
        }
    }
}