package com.company;

import java.util.Scanner;

public class MelrahShake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        String pattern = scanner.nextLine();

        while (true)
        {
            if (pattern.length() <= 0)
            {
                System.out.println("No shake.");
                System.out.println(word);
                break;
            }
            int count = 0;
            int index = word.indexOf(pattern);
            while (index >= 0)
            {
                count++;
                index = word.indexOf(pattern, index + pattern.length());
            }
            if (count < 2)
            {
                System.out.println("No shake.");
                System.out.println(word);
                break;
            }
            int firstIndex = word.indexOf(pattern);
            int lastIndex = word.lastIndexOf(pattern);
            word = word.substring(0, firstIndex) +
                    word.substring(firstIndex + pattern.length(), lastIndex) +
                    word.substring(lastIndex + pattern.length(), word.length());
            int indexToRemove = pattern.length() / 2;
            pattern = pattern.substring(0, indexToRemove) + pattern.substring(indexToRemove + 1, pattern.length());
            System.out.println("Shaked it.");
        }
    }
}
