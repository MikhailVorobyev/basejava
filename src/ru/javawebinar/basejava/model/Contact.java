package ru.javawebinar.basejava.model;

public class Contact {
    private String name;
    private String value;

    public Contact(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  name + "  " + value;
    }
}
