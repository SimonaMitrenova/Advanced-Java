package com.company;

import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int numbersToAdd = input[0];
        int numbersToPoll = input[1];
        int searchedElement = input[2];
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < numbersToAdd; i++) {
            queue.add(numbers[i]);
        }
        for (int i = 0; i < numbersToPoll; i++) {
            queue.poll();
        }
        if (queue.size() == 0) {
            System.out.println(0);
            return;
        }
        int min = Integer.MAX_VALUE;
        for (Integer element : queue) {
            if (element.equals(searchedElement)){
                System.out.println("true");
                return;
            }
            if (min > element){
                min = element;
            }
        }

        System.out.println(min);
    }
}
