package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseYourChainsBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String regex = "<p>(.+?)<\\/p>";
        Pattern pattern = Pattern.compile(regex);
        StringBuilder extractedText = new StringBuilder();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            extractedText.append(matcher.group(1));
        }
        String replacedText = extractedText.toString().replaceAll("[^a-z0-9]+", " ");
        StringBuilder result = new StringBuilder();
        for (Character character : replacedText.toCharArray()) {
            if (character.compareTo('a') >= 0 && character.compareTo('m') <= 0){
                Character toAppend = (char)(character + 13);
                result.append(toAppend);
            } else if(character.compareTo('n') >= 0 && character.compareTo('z') <= 0){
                result.append((char)(character - 13));
            } else {
                result.append(character);
            }
        }
        System.out.println(result.toString().replaceAll("\\s+", " "));
    }
}
