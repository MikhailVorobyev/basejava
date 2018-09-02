package ru.javawebinar.basejava.model;


public class EducationSection implements Section {
    private String institutionName;
    private String dateStart;
    private String dateFinish;
    private String description;

    public EducationSection(String institutionName, String dateStart, String dateFinish, String description) {
        this.institutionName = institutionName;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Education\n" +
                "Educational Institution  " + institutionName +
                "\n" + dateStart + "-" + dateFinish +
                "\n" + description;
    }
}
