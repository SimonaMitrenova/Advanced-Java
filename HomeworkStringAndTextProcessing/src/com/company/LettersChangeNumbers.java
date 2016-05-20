package com.company;

import java.util.Scanner;

public class LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] line = scan.nextLine().split("\\s+");

        double totalSum = 0;
        for (String string : line) {
            double number = Double.parseDouble(string.substring(1, string.length() - 1));
            if (Character.isUpperCase(string.charAt(0))) {
                number /= GetPositionInAlphabet(string.charAt(0));
            } else {
                number *= GetPositionInAlphabet(string.charAt(0));
            }
            if (Character.isUpperCase(string.charAt(string.length() - 1))) {
                number -= GetPositionInAlphabet(string.charAt(string.length() - 1));
            } else {
                number += GetPositionInAlphabet(string.charAt(string.length() - 1));
            }
            totalSum += number;
        }

        System.out.printf("%.2f", totalSum);
    }

    public static int GetPositionInAlphabet(char ch) {
        if (Character.isUpperCase(ch)) {
            return ((int) ch - (int) 'A') + 1;
        } else {
            return ((int) ch - (int) 'a') + 1;
        }
    }
}
