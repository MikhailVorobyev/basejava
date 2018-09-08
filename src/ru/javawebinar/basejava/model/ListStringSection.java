package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListStringSection implements Section {
    private List<String> description;

    public ListStringSection(List<String> description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    @Override
    public String toString() {
        return convertToString(description);
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
