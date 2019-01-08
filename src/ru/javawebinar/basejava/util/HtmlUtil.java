package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {
    public static String getDate(LocalDate localDate) {
        if (localDate.toEpochDay() >= LocalDate.now().toEpochDay()) {
            return "Сейчас";
        }
        return localDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public static String getContact(Resume resume, ContactType contactType) {
        return contactType.toHtml(resume.getContact(contactType));
    }
}
