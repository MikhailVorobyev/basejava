package ru.javawebinar.basejava.model;

import java.util.*;

public class InstitutionSection implements Section {
    private final List<Institution> institutions;

    public InstitutionSection(Institution... institutions) {
        this(Arrays.asList(institutions));
    }

    public InstitutionSection(List<Institution> institutions) {
        Objects.requireNonNull(institutions, "institutions must be not null");
        this.institutions = institutions;
    }

    public List<Institution> getInstitutions() {
        return institutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionSection that = (InstitutionSection) o;

        return institutions.equals(that.institutions);
    }

    @Override
    public int hashCode() {
        return institutions.hashCode();
    }

    @Override
    public String toString() {
        return institutions.toString();
    }
}
