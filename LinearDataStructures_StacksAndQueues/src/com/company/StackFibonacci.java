package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class StackFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n < 2){
            System.out.println(1);
            return;
        }
        LinkedList<Long> stack = new LinkedList<>();
        stack.push(1L);
        stack.push(1L);
        int counter = 1;
        while (counter < n){
            long second = stack.poll();
            long first = stack.poll();
            stack.push(second);
            stack.push(second + first);
            counter++;
        }
        System.out.println(stack.peek());
    }
}
