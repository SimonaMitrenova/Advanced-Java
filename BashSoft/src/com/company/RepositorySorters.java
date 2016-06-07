package com.company;

import java.util.*;

public class RepositorySorters {
    public static void printSortedStudents(HashMap<String, List<Integer>> courseData, String comparisonType, int numberOfStudents){
        Comparator<Map.Entry<String, List<Integer>>> comparator = createComparator(comparisonType);
        if (comparator == null){
            OutputWriter.displayException(ExceptionMessages.INVALID_COMPARISON_TYPE);
            return;
        }
        List<Map.Entry<String, List<Integer>>> sortedStudents = new ArrayList<>();
        sortedStudents.addAll(courseData.entrySet());
        Collections.sort(sortedStudents, comparator);
        int studentsCount = 0;
        for (Map.Entry<String, List<Integer>> student : sortedStudents) {
            if (studentsCount == numberOfStudents){
                break;
            }
            OutputWriter.displayStudent(student.getKey(), student.getValue());
            studentsCount++;
        }
    }

    private static Comparator<Map.Entry<String, List<Integer>>> createComparator(String comparisonType){
        switch (comparisonType){
            case "ascending":
                return (firstStudent, secondStudent) -> Double.compare(getTotalScore(firstStudent.getValue()), getTotalScore(secondStudent.getValue()));

            case "descending":
                return (firstStudent, secondStudent) -> Double.compare(getTotalScore(secondStudent.getValue()), getTotalScore(firstStudent.getValue()));

            default:
                return null;
        }
    }

    private static double getTotalScore(List<Integer> grades){
        double totalScore = grades.stream().reduce(0, Integer::sum);
        return totalScore / grades.size();
    }
}
