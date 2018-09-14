package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Institution {
    private final Link homePage;
    private final List<ActivityPeriod> activityPeriods;

    public Institution(String name, String url, List<ActivityPeriod> activityPeriods) {
        Objects.requireNonNull(activityPeriods, "activityPeriods must not be null");
        this.homePage = new Link(name, url);
        this.activityPeriods = activityPeriods;
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
}
