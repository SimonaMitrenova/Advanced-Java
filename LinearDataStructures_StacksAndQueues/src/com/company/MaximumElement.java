package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxNumberStack = new Stack<>();
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int commandType = scanner.nextInt();
            switch (commandType){
                case 1:
                    int numberToPush = scanner.nextInt();
                    stack.push(numberToPush);
                    if (numberToPush >= maxElement){
                        maxElement = numberToPush;
                        maxNumberStack.push(numberToPush);
                    }
                    break;
                case 2:
                    int itemAtTop = stack.pop();
                    if (itemAtTop == maxElement){
                        maxNumberStack.pop();
                        if (maxNumberStack.size() > 0){
                            maxElement = maxNumberStack.peek();
                        } else  {
                            maxElement = Integer.MIN_VALUE;
                        }
                    }
                    break;
                case 3:
                    System.out.println(maxNumberStack.peek());
                    break;
            }
        }
    }
}
