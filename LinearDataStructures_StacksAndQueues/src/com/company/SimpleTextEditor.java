package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        LinkedList<String> undo = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int command = scanner.nextInt();
            switch (command){
                case 1:
                    String textInput = scanner.next();
                    text.append(textInput);
                    undo.push(text.toString());
                    break;

                case 2:
                    int eraseCount = scanner.nextInt();
                    text.delete(text.length() - eraseCount, text.length());
                    undo.push(text.toString());
                    break;

                case 3:
                    int index = scanner.nextInt();
                    System.out.println(text.charAt(index - 1));
                    break;

                case 4:
                    undo.poll();
                    if (undo.size() > 0){
                        text = new StringBuilder(undo.peek());
                    }else {
                        text.delete(0, text.length());
                    }
                    break;
            }

        }
    }
}
