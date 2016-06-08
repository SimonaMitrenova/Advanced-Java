package com.company;

import com.company.IO.OutputWriter;
import com.company.StaticData.ExceptionMessages;
import com.company.StaticData.SessionData;

import java.io.File;
import java.util.LinkedList;

public class IOManager {
    public static void traverseDirectory(int depth){
        LinkedList<File> subFolders = new LinkedList<>();
        String path = getCurrentDirectoryPath();
        int initialIndentation = path.split("\\\\").length;
        File root = new File(path);
        subFolders.add(root);

        while (subFolders.size() > 0){
            File currentFolder = subFolders.removeFirst();
            int currentIndentation = currentFolder.toString().split("\\\\").length - initialIndentation;
            if (depth - currentIndentation < 0){
                break;
            }
            OutputWriter.writeMessageOnNewLine(currentFolder.toString());

            if (currentFolder.listFiles() != null){
                for (File file : currentFolder.listFiles()) {
                    if (file.isDirectory()){
                        subFolders.add(file);
                    } else {
                        int indexOfLastSlash = file.toString().lastIndexOf("\\");
                        for (int i = 0; i < indexOfLastSlash; i++) {
                             OutputWriter.writeMessage("-");
                        }
                        OutputWriter.writeMessageOnNewLine(file.getName());
                    }
                }
            }

        }
    }

    public static void createDirectoryInCurrentFolder(String name){
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        file.mkdir();
    }

    public static String getCurrentDirectoryPath() {
        String currentPath = SessionData.currentPath;
        return currentPath;
    }

    public static void changeCurrentDirRelativePath(String relativePath){
        if (relativePath.equals("..")){
            try {
                // go one directory up
                String currentPath = getCurrentDirectoryPath();
                int indexOfLastSlash = currentPath.lastIndexOf("\\");
                String newPath = currentPath.substring(0, indexOfLastSlash);
                changeCurrentDirAbsolutePath(newPath);
            } catch (StringIndexOutOfBoundsException ex){
                OutputWriter.displayException(ExceptionMessages.INVALID_DESTINATION);
            }

        } else {
            // do to a given directory
            String currentPath = getCurrentDirectoryPath();
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolutePath(currentPath);
        }
    }

    public static void changeCurrentDirAbsolutePath(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()){
            OutputWriter.displayException(ExceptionMessages.FILE_DOES_NOT_EXIST);
            return;
        }

        SessionData.currentPath = absolutePath;
    }
}
