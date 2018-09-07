package ru.javawebinar.basejava.model;

import java.util.*;

public class InstitutionSection implements Section {
    private String title;
    private Map<String, Institution> institutions = new LinkedHashMap<>();

    public InstitutionSection(String title) {
        Objects.requireNonNull(title, "title must be not null");
        this.title = title;
    }

    public void addInstitution(String institutionName, String link, String dateStart,
                               String dateFinish, String description) {
        institutions.put(institutionName, new Institution(institutionName, link, dateStart, dateFinish, description));
    }

    public void addPeriodOfActivity(String institutionName, String dateStart, String dateFinish, String description) {
        Objects.requireNonNull(institutionName, "institutionName must be not null");
        Institution institution = institutions.get(institutionName);
        institution.addPeriod(dateStart, dateFinish, description);
        institutions.put(institution.getInstitutionName(), institution);
    }

    @Override
    public String toString() {
        String result = title + '\n' + convertToString(institutions);
        return result.substring(0, result.length() - 1);
    }

    private String convertToString(Map<String, Institution> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Institution> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append('\n');
            stringBuilder.append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    private class Institution {
        private String institutionName;
        private String link;
        private List<String> period = new ArrayList<>();

        private Institution(String institutionName, String link, String dateStart,
                            String dateFinish, String description) {
            Objects.requireNonNull(institutionName, "institutionName must be not null");
            Objects.requireNonNull(link, "link must be not null");
            Objects.requireNonNull(dateStart, "dateStart must be not null");
            Objects.requireNonNull(dateFinish, "dateFinish must be not null");
            Objects.requireNonNull(description, "description must be not null");
            this.institutionName = institutionName;
            this.link = link;
            addPeriod(dateStart, dateFinish, description);
        }

        public String getInstitutionName() {
            return institutionName;
        }

        void addPeriod(String dateStart, String dateFinish, String description) {
            period.add(dateStart + "-" + dateFinish + "  " +  description);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : period) {
                stringBuilder.append(string);
                stringBuilder.append('\n');
            }
            stringBuilder.append('\n');
            return stringBuilder.toString();
        }
    }
}
