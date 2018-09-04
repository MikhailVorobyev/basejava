package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListStringSection implements Section {
    private String title;
    private List<String> description;

    public ListStringSection(String title, List<String> description) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(description, "description must not be null");
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return title + '\n' + convertToString(description);
    }

    private String convertToString(List<String> arrayString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arrayString) {
            stringBuilder.append(string);
            stringBuilder.append('\n');
            stringBuilder.append('\n');
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }
}
