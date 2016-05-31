package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StudentRepository {
    private static boolean isDataInitialized = false;
    private static HashMap<String, HashMap<String, List<Integer>>> studentsByCourse;

    public static void initializeData(String fileName){
        if (isDataInitialized){
            System.out.println(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }

        studentsByCourse = new HashMap<>();
        try {
            readData(fileName);
            isDataInitialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStudentMarksInCourse(String course, String student){
        if (isQueryForStudentPossible(course, student)){
            return;
        }
        List<Integer> marks = studentsByCourse.get(course).get(student);
        OutputWriter.displayStudent(student, marks);
    }

    public static void getStudentsByCourse(String course){
        if (!isQueryForCoursePossible(course)){
            return;
        }
        OutputWriter.writeMessageOnNewLine(course + ":");
        for (Map.Entry<String,List<Integer>> student : studentsByCourse.get(course).entrySet()) {
            OutputWriter.displayStudent(student.getKey(), student.getValue());
        }
    }

    private static void readData(String fileName) throws IOException {
        String path = SessionData.currentPath + "\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            String course = tokens[0];
            String student = tokens[1];
            Integer mark = Integer.parseInt(tokens[2]);

            if (!studentsByCourse.containsKey(course)){
                studentsByCourse.put(course, new HashMap<>());
            }
            if (!studentsByCourse.get(course).containsKey(student)){
                studentsByCourse.get(course).put(student, new ArrayList<>());
            }
            studentsByCourse.get(course).get(student).add(mark);
        }

        OutputWriter.writeMessageOnNewLine("Data read.");
    }

    private static boolean isQueryForCoursePossible(String courseName){
        if (!isDataInitialized){
            OutputWriter.displayException(ExceptionMessages.DATA_NOT_INITIALIZED);
            return false;
        }
        if (!studentsByCourse.containsKey(courseName)){
            OutputWriter.displayException(ExceptionMessages.NOR_EXISTING_COURSE);
            return false;
        }

        return true;
    }

    private static boolean isQueryForStudentPossible(String courseName, String studentName){
        if (!isQueryForCoursePossible(courseName)){
            return false;
        }
        if (!studentsByCourse.get(courseName).containsKey(studentName)){
            OutputWriter.displayException(ExceptionMessages.NOT_EXISTING_STUDENT);
            return false;
        }

        return true;
    }
}
