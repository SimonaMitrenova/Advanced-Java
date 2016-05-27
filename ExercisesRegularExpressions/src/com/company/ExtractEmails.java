package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String regex = "(?:(?<=\\s)|^)([a-z1-9]+[\\-\\._]?[a-z1-9]+)@(?:[a-z]+[\\-]?[a-z]+)(:?\\.[a-z]+[\\-]?[a-z]+)+";
        Pattern pattern = Pattern.compile(regex);
        while (true){
            String text = scanner.nextLine();
            if (text.equals("end")){
                break;
            }
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()){
                System.out.println(matcher.group());
            }
        }
    }
}