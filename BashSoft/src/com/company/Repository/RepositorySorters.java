package com.company.repository;

import com.company.io.OutputWriter;
import com.company.staticData.ExceptionMessages;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorters {
    public static void printSortedStudents(
            HashMap<String, List<Integer>> courseData,
            String comparisonType,
            int numberOfStudents){
        comparisonType = comparisonType.toLowerCase();
        if (!comparisonType.equals("ascending") && !comparisonType.equals("descending")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_COMPARISON_QUERY);
            return;
        }

        Comparator<Map.Entry<String, List<Integer>>> comparator = (x, y) ->
             Double.compare(
                    x.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble(),
                    y.getValue().stream().mapToInt(Integer::valueOf).average().getAsDouble());


        List<String> sortedStudents = courseData
                .entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(x -> x.getKey())
                .collect(Collectors.toList());
        if (comparisonType.equals("descending")){
            Collections.reverse(sortedStudents);
        }

        printStudents(courseData, sortedStudents);
    }

    private static void printStudents(HashMap<String, List<Integer>> courseData, List<String> sortedStudents) {
        for (String student : sortedStudents) {
            OutputWriter.displayStudent(student, courseData.get(student));
        }
    }
}
