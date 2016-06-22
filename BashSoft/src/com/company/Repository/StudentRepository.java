package com.company.repository;

import com.company.staticData.ExceptionMessages;
import com.company.io.OutputWriter;
import com.company.staticData.SessionData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRepository {
    private static boolean isDataInitialized = false;
    private static HashMap<String, HashMap<String, List<Integer>>> studentsByCourse;

    public static void initializeData(String fileName){
        if (isDataInitialized){
            OutputWriter.displayException(ExceptionMessages.DATA_ALREADY_INITIALIZED);
            return;
        }

        studentsByCourse = new LinkedHashMap<>();
        try {
            readData(fileName);
            isDataInitialized = true;
        } catch (IOException e) {
            OutputWriter.displayException(ExceptionMessages.FILE_NOT_FOUND);
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

    public static void printFilteredStudents(String course, String filter, Integer numberOfStudents){
        if (!isQueryForCoursePossible(course)){
            return;
        }

        if (numberOfStudents == null){
            numberOfStudents = studentsByCourse.get(course).size();
        }
        RepositoryFilters.printFilteredStudents(studentsByCourse.get(course), filter, numberOfStudents);
    }

    public static void printOrderedStudents(String course, String comparisonType, Integer numberOfStudents){
        if (!isQueryForCoursePossible(course)){
            return;
        }

        if (numberOfStudents == null){
            numberOfStudents = studentsByCourse.get(course).size();
        }

        RepositorySorters.printSortedStudents(studentsByCourse.get(course), comparisonType, numberOfStudents);
    }

    private static void readData(String fileName) throws IOException {
        String regex = "([A-Z][a-zA-Z+#]*_[A-Z][a-z]{2}_\\d{4})\\s+([A-Z][a-z]{0,3}\\d{2}_\\d{2,4})\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);

        String path = SessionData.currentPath + "\\resources\\" + fileName;
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (line.isEmpty() || !matcher.find()){
                continue;
            }

            String course = matcher.group(1);
            String student = matcher.group(2);
            Integer mark = Integer.parseInt(matcher.group(3));
            if (mark >= 0 && mark <= 100){
                if (!studentsByCourse.containsKey(course)){
                    studentsByCourse.put(course, new LinkedHashMap<>());
                }
                if (!studentsByCourse.get(course).containsKey(student)){
                    studentsByCourse.get(course).put(student, new ArrayList<>());
                }
                studentsByCourse.get(course).get(student).add(mark);
            }
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

    public static void filterAndTake(String courseName, String filter) {
        int studentsToTake = studentsByCourse.get(courseName).size();
        filterAndTake(courseName, filter, studentsToTake);
    }

    public static void filterAndTake(String courseName, String filter, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        RepositoryFilters.printFilteredStudents(studentsByCourse.get(courseName), filter, studentsToTake);
    }

    public static void orderAndTake(String courseName, String orderType, int studentsToTake) {
        if (!isQueryForCoursePossible(courseName)) {
            return;
        }

        RepositorySorters.printSortedStudents(studentsByCourse.get(courseName), orderType, studentsToTake);
    }

    public static void orderAndTake(String courseName, String orderType) {
        int studentsToTake = studentsByCourse.get(courseName).size();
        orderAndTake(courseName, orderType, studentsToTake);
    }
}
