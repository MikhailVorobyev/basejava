package ru.javawebinar.basejava.model;

public class ArrayStringSection implements Section {
    private String title;
    private String[] description;

    public ArrayStringSection(String title, String[] description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {

        return title + "\n" + convertToString(description);
    }

    private String convertToString(String[] arrayString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arrayString) {
            stringBuilder.append(string);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
