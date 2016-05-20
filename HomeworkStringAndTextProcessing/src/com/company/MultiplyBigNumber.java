package com.company;

import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNum = scanner.nextLine();
        Integer secondNum = Integer.parseInt(scanner.nextLine());
        if (secondNum == 0){
            System.out.println(0);
            return;
        }
        String result = multiply(firstNum, secondNum);
        System.out.println(result);
    }

    private static String multiply(String firstNum, int secondNum) {
        StringBuilder result = new StringBuilder();

        int reminder = 0;
        for (int i = firstNum.length() - 1; i >= 0; i--) {
            int sum = (Integer.parseInt(String.format("%c", firstNum.charAt(i))) * secondNum) + reminder;
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
