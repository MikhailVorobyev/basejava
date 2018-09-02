package ru.javawebinar.basejava.model;


import java.util.*;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private Map<String, Contact> contacts = new HashMap<>();

    private Map<Enum<SectionType>, Section> sections = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public Map<String, Contact> getContacts() {
        return new HashMap<>(contacts);
    }

    public Map<Enum<SectionType>, Section> getSections() {
        return new HashMap<>(sections);
    }

    public void addSection(Enum<SectionType> enumSection, Section section) {
        sections.put(enumSection, section);
    }

    public void addContact(String contactName, Contact contact) {
        contacts.put(contactName, contact);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Resume resume = (Resume) object;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    @Override
    public int compareTo(Resume resume) {
        int comparisonByFullName = fullName.compareTo(resume.fullName);
        return comparisonByFullName != 0
                ? comparisonByFullName
                : uuid.compareTo(resume.uuid);
    }
}
