package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        List<Student> students = new ArrayList<>();
        while (true){
            String input = scanner.readLine();
            if (input.equals("END")){
                break;
            }
            String[] tokens = input.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];
            int group = Integer.parseInt(tokens[2]);
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setGroup(group);
            students.add(student);
        }

        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup))
                .forEach((g, groupStudents) -> {
                    System.out.printf("%d - ", g);
                    System.out.println(String.join(" ", groupStudents.toString().replaceAll("[\\[\\]]", "")));
                });
    }
}
