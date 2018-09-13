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

        //HW8: recursion
        File dir = new File(".");
        recursion(dir);
    }

    private static void recursion(File path) {
        File[] list = path.listFiles();
        if (list != null) {
            for (File name : list) {
                System.out.println(name.getName());
                if (name.isDirectory()) {
                    recursion(name.getAbsoluteFile());
                }
            }
        }
    }
}
