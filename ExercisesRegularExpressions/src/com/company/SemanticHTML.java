package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SemanticHTML {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String rawUserInput = reader.nextLine();
        String result;
        while (!rawUserInput.equals("END")) {

            Pattern closingBracket = Pattern.compile("((?:\\s+)?>)");
            Matcher closingBracketMatcher = closingBracket.matcher(rawUserInput);
            Pattern patternClose = Pattern.compile("(<\\/div>(?:\\s*)?<!--(?:\\s*)?(\\w+)(?:\\s*)?-->)");
            Matcher matcherClose = patternClose.matcher(rawUserInput);
            Pattern patternOpen = Pattern.compile("<(div)(?:.)*(\\s(?:id|class)(?:\\s*)?=(?:\\s*)?\"(\\w+)\")");
            Matcher matcherOpen = patternOpen.matcher(rawUserInput);

            int whiteSpaceCount = rawUserInput.indexOf('<');
            if (matcherOpen.find()) {
                String leftPart = rawUserInput.substring(0, whiteSpaceCount);
                String rightPart = rawUserInput.substring(whiteSpaceCount);
                result = rightPart.replaceAll(matcherOpen.group(1), matcherOpen.group(3));
                result = result.replaceAll(matcherOpen.group(2), "");
                result = result.replaceAll("\\s+", " ");

                if (closingBracketMatcher.find()) {
                    result = result.replaceAll(closingBracketMatcher.group(1), ">");
                }

                System.out.println(leftPart + result);
            } else if (matcherClose.find()) {
                result = rawUserInput.replaceAll(matcherClose.group(1), "</" + matcherClose.group(2) + ">");

                if (closingBracketMatcher.find()) {
                    result = result.replaceAll(closingBracketMatcher.group(1), ">");
                }
                System.out.println(result);
            } else {
                System.out.println(rawUserInput);
            }

            rawUserInput = reader.nextLine();
        }
    }
}

