package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringSection implements Section {
    private SectionType title;
    private String description;

    public StringSection(SectionType personal, String description) {
        Objects.requireNonNull(personal, "personal must not be null");
        Objects.requireNonNull(description, "description must not be null");
        this.title = personal;
        this.description = description;
    }

    @Override
    public String toString() {
        return title.getTitle() + '\n' + description;
    }
}
