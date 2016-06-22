package com.company.io;

import com.company.io.IOManager;
import com.company.io.OutputWriter;
import com.company.judge.Tester;
import com.company.network.DownloadManager;
import com.company.repository.StudentRepository;
import com.company.staticData.ExceptionMessages;
import com.company.staticData.SessionData;

import java.awt.*;
import java.io.*;

public class CommandInterpreter {
    public static void interpretCommand(String input){
        String[] data = input.split("\\s+");
        String command = data[0].toLowerCase();
        switch (command){
            case "open":
                tryOpenFile(input, data);
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

            case "cdrel":
                tryChangeRelativePath(input, data);
                break;

            case "cdabs":
                tryChangeAbsolutePath(input, data);
                break;

            case "readdb":
                tryReadDatabaseFromFile(input, data);
                break;

            case "help":
                tryGetHelp(input, data);
                break;

            case "show":
                tryShowWantedCourse(input, data);
                break;

            case "filter":
                tryPrintFilteredStudents(input, data);
                break;

            case "order":
                tryPrintOrderedStudents(input, data);
                break;

            case "download":
                tryDownloadFile(input, data);
                break;

            case "downloadasynch":
                tryDownloadFileOnNewThread(input, data);
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

    private static void tryGetHelp(String input, String[] data) {
        if (data.length != 1) {
            displayInvalidCommandMessage(input);
            return;
        }

        displayHelp();
    }

    private static void displayHelp() {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources\\getHelp.txt"))){
            while (true){
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                OutputWriter.writeMessageOnNewLine(line);
            }
            OutputWriter.writeEmptyLine();

        }  catch (IOException e) {
            OutputWriter.displayException(e.getMessage());
        }
    }

    private static void tryDownloadFileOnNewThread(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String fileUrl = data[1];
        DownloadManager.downloadOnNewThread(fileUrl);
    }

    private static void tryDownloadFile(String input, String[] data) {
        if (data.length != 2){
            displayInvalidCommandMessage(input);
            return;
        }
        String fileUrl = data[1];
        DownloadManager.download(fileUrl);
    }

    private static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 5) {
            displayInvalidCommandMessage(input);
            return;
        }

        String courseName = data[1];
        String orderType = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }
    private static void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
            return;
        }

        if (takeQuantity.equals("all")) {
            StudentRepository.orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            StudentRepository.orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }

    private static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 5) {
            displayInvalidCommandMessage(input);
            return;
        }

        String course = data[1];
        String filter = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        tryParseParametersForFilter(takeCommand, takeQuantity, course, filter);
    }

    private static void tryParseParametersForFilter(
            String takeCommand, String takeQuantity,
            String courseName, String filter) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
            return;
        }

        if (takeQuantity.equals("all")) {
            StudentRepository.filterAndTake(courseName, filter);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            StudentRepository.filterAndTake(courseName, filter, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
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
