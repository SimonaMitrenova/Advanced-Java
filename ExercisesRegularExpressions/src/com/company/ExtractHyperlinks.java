package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractHyperlinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder();
        while (true){
            String input = scanner.nextLine();
            if (input.equals("END")){
                break;
            }
            text.append(input);
        }
        String regex = "<a\\s+([^>]+\\s+)?href\\s*=\\s*('([^']*)'|\"([^\"]*)|([^\\s>]+))[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text.toString());

        while (matcher.find()){
            String match = matcher.group(3);
            if (match == null){
                match = matcher.group(4);
                if (match == null){
                    match = matcher.group(5);
                    System.out.println(match);
                } else{
                    System.out.println(match);
                }
            } else {
                System.out.println(match);
            }
        }
    }
}
