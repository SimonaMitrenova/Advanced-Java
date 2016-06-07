package com.company;

import java.awt.*;
import java.io.*;

public class CommandInterpreter {
    public static void interpretCommand(String input){
        String[] data = input.split("\\s+");
        String command = data[0];
        switch (command){
            case "open":
                tryOpenFile(input, data);
                break;

            case "show":
                tryShowWantedCourse(input, data);
                break;

            case "mkdir":
                tryCreateDirectory(input, data);
                break;

            case "ls":
                tryTraverseFolders(input, data);
                break;

            case "cmp":
                tryCompareFiles(input, data);
                break;

            case "changeDirRel":
                tryChangeRelativePath(input, data);
                break;

            case "changeDirAbs":
                tryChangeAbsolutePath(input, data);
                break;

            case "readDb":
                tryReadDatabaseFromFile(input, data);
                break;

            case "filter":
                tryPrintFilteredStudents(input, data);
                break;

            case "order":
                tryPrintOrderedStudents(input, data);
                break;

            case "download":
                downloadFile(data);
                break;

            case "downloadAsynch":
                downloadFileAsync(data);
                break;

            case "help":
                getHelp();
                break;

            default:
                displayInvalidCommandMessage(input);
                break;
        }
    }

    private static void tryShowWantedCourse(String input, String[] data) {
        if (data.length != 2 && data.length != 3){
            displayInvalidCommandMessage(input);
            return;
        }

        if (data.length == 2){
            String courseName = data[1];
            StudentRepository.getStudentsByCourse(courseName);
        } else {
            String courseName = data[1];
            String student = data[2];
            StudentRepository.getStudentMarksInCourse(courseName, student);
        }
    }   

    private static void displayInvalidCommandMessage(String input) {
        String output = String.format("The command %s is invalid", input);
        OutputWriter.writeMessageOnNewLine(output);
    }

    private static void getHelp() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources\\getHelp.txt"))){
            while (true){
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                OutputWriter.writeMessageOnNewLine(line);
            }
            OutputWriter.writeEmptyLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFileAsync(String[] data) {
        // TODO
    }

    private static void downloadFile(String[] data) {
        // TODO
    }

    private static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4){
            displayInvalidCommandMessage(input);
            return;
        }

        String course = data[1];
        String comparisonType = data[2];
        Integer numberOfStudents = null;
        if (data.length == 4){
            numberOfStudents = Integer.parseInt(data[3]);
        }

        StudentRepository.printOrderedStudents(course, comparisonType, numberOfStudents);
    }

    private static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4){
            displayInvalidCommandMessage(input);
            return;
        }

        String course = data[1];
        String filter = data[2];
        Integer numberOfStudents = null;
        if (data.length == 4){
            numberOfStudents = Integer.parseInt(data[3]);
        }

        StudentRepository.printFilteredStudents(course, filter, numberOfStudents);
    }

    private static void tryReadDatabaseFromFile(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        String fileName = data[1];
        StudentRepository.initializeData(fileName);
    }

    private static void tryChangeAbsolutePath(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        String absolutePath = data[1];
        IOManager.changeCurrentDirAbsolutePath(absolutePath);
    }

    private static void tryChangeRelativePath(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        String relativePath = data[1];
        IOManager.changeCurrentDirRelativePath(relativePath);
    }

    private static void tryCompareFiles(String input, String[] data) {
        if (data.length != 3){
            displayInvalidCommandMessage(input);
            return;
        }

        String firstPath = data[1];
        String secondPath = data[2];
        Tester.compareContent(firstPath, secondPath);
    }

    private static void tryTraverseFolders(String input, String[] data) {
        if (data.length != 1 && data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        if (data.length == 1){
            IOManager.traverseDirectory(0);
        } else {
            int depth = Integer.parseInt(data[1]);
            IOManager.traverseDirectory(depth);
        }
    }

    private static void tryCreateDirectory(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        String folderName = data[1];
        IOManager.createDirectoryInCurrentFolder(folderName);
    }

    private static void tryOpenFile(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }

        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
