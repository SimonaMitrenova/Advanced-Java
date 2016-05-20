package com.company;

import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) {
        HashMap<Character, Integer> powers = new HashMap<>();
        powers.put('2', 2);
        powers.put('3', 3);
        powers.put('4', 4);
        powers.put('5', 5);
        powers.put('6', 6);
        powers.put('7', 7);
        powers.put('8', 8);
        powers.put('9', 9);
        powers.put('T', 10);
        powers.put('J', 11);
        powers.put('Q', 12);
        powers.put('K', 13);
        powers.put('A', 14);

        powers.put('S', 4);
        powers.put('H', 3);
        powers.put('D', 2);
        powers.put('C', 1);

        LinkedHashMap<String, HashSet<String>> peopleWithCards = new LinkedHashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("JOKER")){
                break;
            }
            String[] inputArgs = input.split(":");
            String name = inputArgs[0];
            String[] cards = inputArgs[1].trim().replaceAll("10", "T").split(", ");
            if (!peopleWithCards.containsKey(name)){
                peopleWithCards.put(name, new HashSet<>());
            }
            for (String card : cards) {
                peopleWithCards.get(name).add(card);
            }
        }
        for (Map.Entry<String, HashSet<String>> entry : peopleWithCards.entrySet()) {
            int value = 0;
            for (String card : entry.getValue()) {
                Integer face = powers.get(card.charAt(0));
                Integer suit = powers.get(card.charAt(1));
                value += face * suit;
            }
            System.out.printf("%s: %d%n", entry.getKey(), value);
        }
    }
}
