package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortStudents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true){
            String input = scanner.nextLine();
            if (input.equals("END")){
                break;
            }
            String[] tokens = input.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            students.add(student);
        }

        students.stream().sorted((s1, s2) -> {
            int result = s1.getLastName().compareTo(s2.getLastName());
            if (result == 0){
                result = s2.getFirstName().compareTo(s1.getFirstName());
            }
            return result;
        }).forEach(s -> System.out.printf("%s %s%n", s.getFirstName(), s.getLastName()));
    }
}
