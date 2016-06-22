package com.company.judge;

import com.company.staticData.ExceptionMessages;
import com.company.io.OutputWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void compareContent(String actualOutput, String expectedOutput) {
        try {
            OutputWriter.writeMessageOnNewLine("Reading files ...");
            String mismatchPath = getMismatchPath(expectedOutput);

            List<String> actualOutputString = readTextFile(actualOutput);
            List<String> expectedOutputString = readTextFile(expectedOutput);
            boolean mismatch = compareStrings(actualOutputString, expectedOutputString, mismatchPath);

            if (mismatch){
                List<String> mismatchString = readTextFile(mismatchPath);
                mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
            } else {
                OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
            }
        } catch (IOException ex){
            OutputWriter.displayException(ExceptionMessages.FILE_NOT_FOUND);
        }
    }

    private static boolean compareStrings(
            List<String> actualOutputString,
            List<String> expectedOutputString,
            String mismatchPath) {
        OutputWriter.writeMessageOnNewLine("Comparing files ...");
        boolean isMismatch = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mismatchPath))) {
            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());
            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);
                if (!actualLine.equals(expectedLine)){
                    isMismatch = true;
                    writer.write(String.format("mismatch -> expected {%s}, actual {%s}%n", expectedLine, actualLine));
                } else {
                    writer.write(String.format("line match -> %s%n", actualLine));
                }
            }

        } catch (IOException e) {
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.CANNOT_ACCESS_FILE);
        } catch (IndexOutOfBoundsException e){
            isMismatch = true;
            OutputWriter.displayException(ExceptionMessages.INVALID_OUTPUT_LENGTH);
        }

        return isMismatch;
    }


    private static List<String> readTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (true) {
                if (line == null){
                    break;
                }
                text.add(line);
                line = reader.readLine();
            }
        }

        return text;
    }

    private static String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }

    private static void printOutput(String mismatchPath, boolean isMismatch) throws IOException {
        if (isMismatch)
        {
            List<String> mismatchStrings = Files.readAllLines(Paths.get(mismatchPath));
            mismatchStrings.forEach(OutputWriter::writeMessageOnNewLine);
            return;
        }

        OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
    }
}
