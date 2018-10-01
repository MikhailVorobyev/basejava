package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InstitutionSection extends Section implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Institution> institutions;

    public InstitutionSection() {
    }

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
