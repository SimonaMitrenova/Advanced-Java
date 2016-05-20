package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int numbersToPush = input[0];
        int numbersToPop = input[1];
        int searchedElement = input[2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numbersToPush - numbersToPop; i++) {
            stack.push(numbers[i]);
        }
        if (stack.size() == 0){
            if (searchedElement == 0){
                System.out.println("true");
            } else{
                System.out.println(0);
            }
            return;
        }
        int min = Integer.MAX_VALUE;
        for (Integer element : stack) {
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
