package ru.javawebinar.basejava.model;

public class StringSection implements Section {
    private String title;
    private String description;

    public StringSection(String personal, String description) {
        this.title = personal;
        this.description = description;
    }

    @Override
    public String toString() {
        return title + "\n" + description;
    }
}
