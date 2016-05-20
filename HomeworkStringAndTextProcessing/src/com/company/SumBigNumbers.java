package com.company;

import java.util.Scanner;

public class SumBigNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNum = scanner.nextLine();
        String secondNum = scanner.nextLine();

        String result = sum(firstNum, secondNum);
        System.out.println(result);
    }

    private static String sum(String firstNum, String secondNum) {
        StringBuilder result = new StringBuilder();
        int difference = Math.abs(firstNum.length() - secondNum.length());
        if (firstNum.length() < secondNum.length()){
            String toAppend = new String(new char[difference]).replace('\0', '0');
            firstNum = toAppend + firstNum;
        } else if (secondNum.length() < firstNum.length()){
            String toAppend = new String(new char[difference]).replace('\0', '0');
            secondNum = toAppend + secondNum;
        }
        int length = Math.min(firstNum.length(), secondNum.length());
        int reminder = 0;
        for (int i = length - 1; i >= 0; i--) {
            int sum = Integer.parseInt(String.format("%c", firstNum.charAt(i))) + Integer.parseInt(String.format("%c", secondNum.charAt(i))) + reminder;
            result.append(sum % 10);
            reminder = sum / 10;
        }

        if (reminder != 0){
            result.append(reminder);
        }
        result.reverse();
        while (result.charAt(0) == '0'){
            result.deleteCharAt(0);
        }
        return result.toString();
    }
}
