package com.company;

import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        long result = multiplyCharacters(input[0], input[1]);
        System.out.println(result);
    }

    private static long multiplyCharacters(String first, String second) {
        long result = 0;
        int length = Math.min(first.length(), second.length());
        for (int i = 0; i < length; i++) {
             result += first.charAt(i) * second.charAt(i);
        }
        while (length < first.length()){
            result += first.charAt(length);
            length++;
        }
        while (length < second.length()){
            result += second.charAt(length);
            length++;
        }

        return result;
    }
}
