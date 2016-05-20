package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SrabskoUnleashed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<String, Long>> venues = new LinkedHashMap<>();
        String regex = "([\\w+\\s*]+)\\s@([\\w+\\s*]+)\\s(\\d+)\\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);

        while (true){
            String line = scanner.nextLine();
            if ("End".equals(line)){
                break;
            }
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()){
                continue;
            }

            if (!venues.containsKey(matcher.group(2))){
                venues.put(matcher.group(2), new LinkedHashMap<>());
            }
            if (!venues.get(matcher.group(2)).containsKey(matcher.group(1))){
                venues.get(matcher.group(2)).put(matcher.group(1), 0L);
            }
            int ticketsPrice = Integer.parseInt(matcher.group(3));
            int ticketsCount = Integer.parseInt(matcher.group(4));
            venues.get(matcher.group(2)).put(matcher.group(1), venues.get(matcher.group(2)).get(matcher.group(1)) + (ticketsCount * ticketsPrice));
        }

        for (Map.Entry<String, LinkedHashMap<String, Long>> venue : venues.entrySet()) {
            System.out.println(venue.getKey());
            venue
                    .getValue()
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(e -> System.out.printf("#  %s -> %d\n", e.getKey(), e.getValue()));
        }
    }
}
