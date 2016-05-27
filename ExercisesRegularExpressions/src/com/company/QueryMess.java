package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryMess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "([^?=&]+)=([^?=&]+)";
        Pattern pattern = Pattern.compile(regex);

        while (true){
            String input = scanner.nextLine();
            if (input.equals("END")){
                break;
            }

            input = input.replaceAll("\\+", " ");
            input = input.replaceAll("%20", " ");
            input = input.replaceAll("\\s+", " ");

            LinkedHashMap<String, List<String>> matches = new LinkedHashMap<>();
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()){
                String key = matcher.group(1).trim();
                String value = matcher.group(2).trim();
                if (!matches.containsKey(key)){
                    matches.put(key, new ArrayList<>());
                }
                matches.get(key).add(value);
            }
            for (Map.Entry<String, List<String>> entry : matches.entrySet()) {
                System.out.printf("%s=%s", entry.getKey(), entry.getValue());
            }
            System.out.println();
        }
    }
}
