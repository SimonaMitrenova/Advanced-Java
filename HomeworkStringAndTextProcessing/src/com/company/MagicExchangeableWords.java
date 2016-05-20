package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class MagicExchangeableWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        boolean areExchangeable = checkForExchangeableWords(input[0], input[1]);
        System.out.println(areExchangeable);
    }

    private static boolean checkForExchangeableWords(String first, String second) {
        HashMap<Character, Character> mappedCharsLongest = new HashMap<>();
        HashMap<Character, Character> mappedCharsShortest = new HashMap<>();
        String shortest;
        String longest;
        if (first.length() <= second.length()) {
            shortest = first;
            longest = second;
        } else {
            shortest = second;
            longest = first;
        }
        for (int i = 0; i < shortest.length(); i++) {
            if (!mappedCharsLongest.containsKey(longest.charAt(i))) {
                mappedCharsLongest.put(longest.charAt(i), shortest.charAt(i));
            }
            if (!mappedCharsShortest.containsKey(shortest.charAt(i))){
                mappedCharsShortest.put(shortest.charAt(i), longest.charAt(i));
            }
            if (mappedCharsLongest.get(longest.charAt(i)) != shortest.charAt(i) ||
                    mappedCharsShortest.get(shortest.charAt(i)) != longest.charAt(i)){
                return false;
            }
        }
        for (int i = shortest.length(); i < longest.length(); i++) {
             if (!mappedCharsLongest.containsKey(longest.charAt(i))){
                 return false;
             }
        }

        return true;
    }
}
