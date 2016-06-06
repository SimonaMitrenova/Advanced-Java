package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OfficeStuff {
    public static void main(String[] args) {
        TreeMap<String, HashMap<String, Integer>> companies = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String regex = "\\|(.+?)\\s-\\s(.+?)\\s-\\s(.+?)\\|";
        Pattern pattern = Pattern.compile(regex);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (!matcher.find()){
                continue;
            }
            String companyName = matcher.group(1);
            int amount = Integer.parseInt(matcher.group(2));
            String product = matcher.group(3);
            if (!companies.containsKey(companyName)){
                companies.put(companyName, new LinkedHashMap<>());
            }
            if (!companies.get(companyName).containsKey(product)){
                companies.get(companyName).put(product, 0);
            }

            companies.get(companyName).put(product, companies.get(companyName).get(product) + amount);
        }

        StringBuilder result= new StringBuilder();
        for (Map.Entry<String, HashMap<String, Integer>> company : companies.entrySet()) {
            result.append(String.format("%s: ", company.getKey()));
            for (Map.Entry<String, Integer> product : company.getValue().entrySet()) {
                result.append(String.format("%s-%d, ", product.getKey(), product.getValue()));
            }
            result.delete(result.length() - 2, result.length());
            result.append("\n");
        }
        System.out.println(result);
    }
}
