package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LinkedHashMap<String, Long> resources = new LinkedHashMap<>();
        while (true) {
            String resource = scanner.nextLine();
            if (resource.equals("stop")) {
                break;
            }
            if (!resources.containsKey(resource)) {
                resources.put(resource, 0L);
            }
            long quantity = Long.parseLong(scanner.nextLine());
            resources.put(resource, resources.get(resource) + quantity);
        }

        for (Map.Entry<String, Long> entry : resources.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}