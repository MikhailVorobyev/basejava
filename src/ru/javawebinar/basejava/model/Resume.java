package ru.javawebinar.basejava.model;

import java.util.Comparator;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    private String fullName;

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume resume1, Resume resume2) {
            if (resume1.getFullName().compareTo(resume2.getFullName()) == 0) {
                return -1;
            }
            return resume1.getFullName().compareTo(resume2.getFullName());
        }
    };

    public Resume() {
        this(UUID.randomUUID().toString(), null);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public static Comparator<Resume> getResumeComparator() {
        return RESUME_COMPARATOR;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Resume resume = (Resume) object;

        if (!uuid.equals(resume.uuid)) return false;
        //return fullName != null ? fullName.equals(resume.fullName) : resume.fullName == null;
        return true;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        //result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume resume) {
        return uuid.compareTo(resume.uuid);
    }
}
