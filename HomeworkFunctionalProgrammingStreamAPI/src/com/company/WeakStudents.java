package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WeakStudents {
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
            List<Integer> marks = new ArrayList<>();
            for (int i = 2; i < tokens.length; i++) {
                marks.add(Integer.parseInt(tokens[i]));
            }
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setMarks(marks);
            students.add(student);
        }

        students.stream()
                .filter(s -> s.getMarks().stream().filter(m -> m <= 3).count() >= 2)
                .forEach(s -> System.out.printf("%s %s%n", s.getFirstName(), s.getLastName()));
    }
}
