package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractHyperlinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "<a[^>]*href\\s*=[\"'\\s]*([^>].*?)['\"\\s>]+";
        Pattern pattern = Pattern.compile(regex);
        StringBuilder sb = new StringBuilder();

        while (true){
            String input = scanner.nextLine();
            if (input.equals("END")){
                break;
            }
            sb.append(input);
        }

        Matcher matcher = pattern.matcher(sb.toString());
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
}
