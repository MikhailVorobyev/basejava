package ru.javawebinar.basejava.model;

import java.util.Objects;

public class StringSection implements Section {

    private final String content;

    public StringSection(String content) {
        Objects.requireNonNull(content, "content must not be null");
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringSection that = (StringSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content + '\n';
    }
}
