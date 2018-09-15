package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Institution {
    private final Link homePage;
    private final List<ActivityPeriod> activityPeriods = new ArrayList<>();

    public Institution(String name, String url, LocalDate startDate, LocalDate endDate,
                       String title, String description) {
        this.homePage = new Link(name, url);
        addPeriod(startDate, endDate, title, description);
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate, String title, String description) {
        activityPeriods.add(new ActivityPeriod(startDate, endDate, title, description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        if (!homePage.equals(that.homePage)) return false;
        return activityPeriods.equals(that.activityPeriods);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + activityPeriods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "homePage=" + homePage +
                ", activityPeriods=" + activityPeriods +
                '}';
    }

    private class ActivityPeriod {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        ActivityPeriod(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ActivityPeriod that = (ActivityPeriod) o;

            if (!startDate.equals(that.startDate)) return false;
            if (!endDate.equals(that.endDate)) return false;
            if (!title.equals(that.title)) return false;
            return description != null ? description.equals(that.description) : that.description == null;
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ActivityPeriod{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
