package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExcellentStudents {
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
            List<Integer> marks = Arrays.stream(tokens).skip(2).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setMarks(marks);
            students.add(student);
        }

        students.stream()
                .filter(s -> s.getMarks().contains(6))
                .forEach(s -> System.out.printf("%s %s%n", s.getFirstName(), s.getLastName()));
    }
}
