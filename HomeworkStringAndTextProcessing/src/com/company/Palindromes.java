package com.company;

import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;

public class Palindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\W+");
        Collator collator = Collator.getInstance(Locale.US);
        TreeSet<String> palindromes = new TreeSet<>(collator);
        for (String word : words) {
            boolean isPalindrome = checkForPalindrome(word);
            if (isPalindrome){
                palindromes.add(word);
            }
        }
        System.out.println(palindromes.toString());
    }

    private static boolean checkForPalindrome(String word) {
        for (int i = 0; i < word.length() / 2; i++) {
             if (word.charAt(i) != word.charAt(word.length() - i - 1)){
                 return false;
             }
        }

        return true;
    }
}
