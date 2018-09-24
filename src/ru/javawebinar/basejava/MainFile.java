package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-------------------------");

        //HW9: indented printDirectoryDeeply
        File dir = new File("./src/ru/javawebinar/basejava");
        printDirectoryDeeply(dir, "");

    }

    private static void printDirectoryDeeply(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(indent + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println();
                    System.out.println(indent + "Dir: " + file.getName());
                    printDirectoryDeeply(file, indent + "    ");
                }
            }
            System.out.println();
        }
    }
}
