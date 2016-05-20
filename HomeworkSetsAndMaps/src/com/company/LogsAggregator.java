package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LogsAggregator {
    public static void main(String[] args) {
        TreeMap<String, TreeMap<String, Integer>> logs = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (!logs.containsKey(input[1])){
                logs.put(input[1], new TreeMap<>());
            }
            if (!logs.get(input[1]).containsKey(input[0])){
                logs.get(input[1]).put(input[0], 0);
            }
            logs.get(input[1]).put(input[0], logs.get(input[1]).get(input[0]) + Integer.parseInt(input[2]));
        }
        for (Map.Entry<String, TreeMap<String, Integer>> person : logs.entrySet()) {
            int duration = 0;
            for (Integer time : person.getValue().values()) {
                duration += time;
            }
            System.out.printf("%s: %d [", person.getKey(), duration);
            System.out.print(String.join(", ", person.getValue().keySet()));
            System.out.print("]");
            System.out.println();
        }
    }
}
