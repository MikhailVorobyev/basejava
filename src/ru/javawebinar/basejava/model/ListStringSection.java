package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListStringSection extends Section {
    private final List<String> items;

    public ListStringSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListStringSection that = (ListStringSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
