package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] usernames = scanner.nextLine().split("[\\s\\/\\\\\\(\\)]+");
        String regex = "^[A-Za-z]\\w{2,24}$";
        Pattern patter = Pattern.compile(regex);
        List<String> validUsernames = new ArrayList<>();
        for (String username : usernames) {
            Matcher matcher = patter.matcher(username);
            if (matcher.find()){
                validUsernames.add(username);
            }
        }
        int maxLength = 0;
        String[] longestTwoWords = new String[2];
        for (int i = 0; i < validUsernames.size() - 1; i++) {
            if (validUsernames.get(i).length() + validUsernames.get(i+1).length() > maxLength)
            {
                maxLength = validUsernames.get(i).length() + validUsernames.get(i+1).length();
                longestTwoWords[0] = validUsernames.get(i);
                longestTwoWords[1] = validUsernames.get(i+1);
            }
        }
        for (String word : longestTwoWords) {
            System.out.println(word);
        }
    }
}
