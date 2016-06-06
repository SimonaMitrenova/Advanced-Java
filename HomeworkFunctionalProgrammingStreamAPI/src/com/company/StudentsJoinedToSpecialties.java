package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentsJoinedToSpecialties {
    public static void main(String[] args) {
        List<StudentSpecialty> specialties = new ArrayList<>();
        List<Collegian> students = new ArrayList<>();
        String specialtyRegex = "(.*?)\\s(\\d+)";
        Pattern specialtyPattern = Pattern.compile(specialtyRegex);

        Scanner scanner = new Scanner(System.in);

        while (true){
            String line = scanner.nextLine();
            if (line.equals("Students:")){
                break;
            }
            Matcher matcher = specialtyPattern.matcher(line);
            if (!matcher.find()){
                continue;
            }

            String specialtyName = matcher.group(1);
            String facultyNumber = matcher.group(2);
            StudentSpecialty specialty = new StudentSpecialty(specialtyName, facultyNumber);
            specialties.add(specialty);
        }

        String studentRegex = "(\\d+)\\s(.+)";
        Pattern studentPattern = Pattern.compile(studentRegex);

        while (true){
            String line = scanner.nextLine();
            if (line.equals("END")){
                break;
            }
            Matcher matcher = studentPattern.matcher(line);
            if (!matcher.find()){
                continue;
            }

            String facultyNumber = matcher.group(1);
            String studentName = matcher.group(2);
            Collegian collegian = new Collegian(studentName, facultyNumber);
            students.add(collegian);
        }

        List<String> joined = new ArrayList<>();
        students.stream().flatMap(student -> specialties.stream().filter(specialty -> {
            if (specialty.getFacultyNumber().equals(student.getFacultyNumber())){
                joined.add(String.format("%s %s %s", student.getName(), student.getFacultyNumber(), specialty.getSpecialtyName()));
            }
            return specialty.getFacultyNumber().equals(student.getFacultyNumber());
        })).collect(Collectors.toList());

        joined.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);
    }
}

class Collegian{

    private String name;
    private String facultyNumber;

    public Collegian(String name, String facultyNumber) {
        this.name = name;
        this.facultyNumber = facultyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }
}

class StudentSpecialty{
    private String specialtyName;
    private String facultyNumber;

    public StudentSpecialty(String specialtyName, String facultyNumber) {
        this.specialtyName = specialtyName;
        this.facultyNumber = facultyNumber;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }
}
