package ru.javawebinar.basejava.model;

public class ExperienceSection implements Section {
    private String companyName;
    private String dateStart;
    private String dateFinish;
    private String position;
    private String description;

    public ExperienceSection(String companyName, String dateStart, String dateFinish, String position, String description) {
        this.companyName = companyName;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.position = position;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Experience\n" +
                "Company Name  " + companyName +
                "\n" + dateStart  + "-" + dateFinish +
                "\n" + position +
                "\n" + description;
    }
}
