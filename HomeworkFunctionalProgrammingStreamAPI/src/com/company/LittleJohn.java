package com.company;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LittleJohn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            input.append(String.format(" %s", scanner.nextLine()));
        }
        int small = 0;
        int medium = 0;
        int large = 0;

        String regex = "(>>>----->>)|(>>----->)|(>----->)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            if (matcher.group(1) != null){
                large++;
            } else if (matcher.group(2) != null) {
                medium++;
            } else {
                small++;
            }
        }

        int dec = small * 100 + medium * 10 + large;
        String bin = Integer.toBinaryString(dec);
        StringBuilder result = new StringBuilder();
        result.append(bin);
        for (int i = bin.length() - 1; i >= 0; i--) {
            result.append(bin.charAt(i));
        }

        System.out.println(new BigInteger(result.toString(), 2));
    }
}
