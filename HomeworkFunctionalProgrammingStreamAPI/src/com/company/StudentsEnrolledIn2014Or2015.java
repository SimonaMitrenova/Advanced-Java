package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsEnrolledIn2014Or2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        List<StudentData> students = new ArrayList<>();

        while (true){
            String input = scanner.readLine();
            if (input.equals("END")){
                break;
            }
            String[] tokens = input.split("\\s+");
            String facultyNumber = tokens[0];

            List<String> marks = new ArrayList<>();
            for (int i = 1; i < tokens.length; i++) {
                marks.add(tokens[i]);
            }
            StudentData student = new StudentData();
            student.setFacultyNumber(facultyNumber);
            student.setMarks(marks);
            students.add(student);
        }

        students.stream().filter(s -> s.getFacultyNumber().endsWith("14") || s.getFacultyNumber().endsWith("15"))
                .forEach(s -> System.out.println(String.join(" ", s.getMarks())));
    }
}

class StudentData{
    private String facultyNumber;
    private List<String> marks;

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public List<String> getMarks() {
        return marks;
    }

    public void setMarks(List<String> marks) {
        this.marks = marks;
    }
}
