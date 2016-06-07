package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class RepositoryFilters {
    public static void printFilteredStudents(HashMap<String, List<Integer>> courseData, String filterType, int numberOfStudents){
        Predicate<Double> predicate = createPredicate(filterType);
        filterAndTake(predicate, courseData, numberOfStudents);
    }

    private static void filterAndTake(Predicate<Double> filter, HashMap<String, List<Integer>> courseData, int numberOfStudents){
        if (filter == null){
            OutputWriter.displayException(ExceptionMessages.INVALID_FILTER);
            return;
        }

        int studentsCount = 0;
        for (String student : courseData.keySet()) {
            if (studentsCount == numberOfStudents){
                break;
            }
            List<Integer> studentMarks = courseData.get(student);
            double averageMark = getStudentAverageGrade(studentMarks);
            if (filter.test(averageMark)){
                OutputWriter.displayStudent(student, studentMarks);
                studentsCount++;
            }
        }
    }

    private static Predicate<Double> createPredicate(String filterType){
        switch (filterType){
            case "excellent":
                return mark -> mark >= 5.0;

            case "average":
                return mark -> mark >= 3.50 && mark < 5.0;

            case "poor":
                return mark -> mark < 3.5;

            default:
                return null;
        }
    }

    private static double getStudentAverageGrade(List<Integer> grades){
        double totalScore = grades.stream().reduce(0, Integer::sum);
        double percentage = totalScore / (grades.size() * 100);
        return (percentage * 4) + 2;
    }
}
