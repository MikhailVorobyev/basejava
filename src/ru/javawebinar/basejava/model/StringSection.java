package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringSection implements Section {

    private String description;

    public StringSection(String description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    @Override
    public String toString() {
        return description + '\n';
    }
}
