package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogs {
    public static void main(String[] args) {
        TreeMap<String, LinkedHashMap<String, Integer>> users = new TreeMap<>();
        String regex = "IP=(.*)\\s+message=(.*)\\s+user=(.*)";
        Pattern pattern = Pattern.compile(regex);

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("end")){
                break;
            }
            Matcher matcher = pattern.matcher(input);
            if (!matcher.find()){
                continue;
            }
            String ip = matcher.group(1);
            String username = matcher.group(3);
            if (!users.containsKey(username)){
                users.put(username, new LinkedHashMap<>());
            }
            if (!users.get(username).containsKey(ip)){
                users.get(username).put(ip, 0);
            }
            users.get(username).put(ip, users.get(username).get(ip) + 1);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, LinkedHashMap<String, Integer>> user : users.entrySet()) {
            result.append(String.format("%s:%n", user.getKey()));
            for (Map.Entry<String,Integer> ipAddress : user.getValue().entrySet()) {
                result.append(String.format("%s => %d, ", ipAddress.getKey(), ipAddress.getValue()));
            }
            result.delete(result.length() - 2, result.length());
            result.append(".\n");
        }
        System.out.print(result);
    }
}
