package com.company;

import java.io.File;

public class GetFolderSize {
    public static void main(String[] args) {
        String folderPath = "resources";
        File folder = new File(folderPath);
        long folderSize = 0L;
        for (File file : folder.listFiles()) {
            folderSize += file.length();
        }
        System.out.println(folderSize / 1000000.0);
    }
}
