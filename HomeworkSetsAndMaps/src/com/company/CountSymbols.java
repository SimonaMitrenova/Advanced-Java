package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        TreeMap<Character, Integer> chars = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (int i = 0; i < input.length(); i++) {
            if (!chars.containsKey(input.charAt(i))){
                chars.put(input.charAt(i), 0);
            }
            chars.put(input.charAt(i), chars.get(input.charAt(i)) + 1);
        }
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            System.out.printf("%c: %d time/s\n", entry.getKey(), entry.getValue());
        }
    }
}
