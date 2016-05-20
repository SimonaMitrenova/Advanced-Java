package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PoisonousPlants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] plants = new int[n];
        for (int i = 0; i < plants.length; i++) {
            plants[i] = scanner.nextInt();
        }
        Stack<Integer> proximityStack = new Stack<>();
        int[] days = new int[plants.length];
        proximityStack.push(0);
        for (int x = 1; x < plants.length; x++) {
            int maxDays = 0;
            while (proximityStack.size() > 0 && plants[proximityStack.peek()] >= plants[x]) {

                maxDays = Integer.max(days[proximityStack.pop()], maxDays);
            }
            if (proximityStack.size() > 0) {
                days[x] = maxDays + 1;
            }
            proximityStack.push(x);
        }
        System.out.printf("%d%n", Arrays.stream(days).max().getAsInt());
    }
}
