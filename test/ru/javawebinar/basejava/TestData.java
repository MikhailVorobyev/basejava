package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.addContact(ContactType.PHONE, "1111111");
        RESUME_1.addContact(ContactType.SKYPE, "skype1");

        RESUME_2.addContact(ContactType.PHONE, "2222222");
        RESUME_2.addContact(ContactType.SKYPE, "skype2");
        RESUME_2.addContact(ContactType.EMAIL, "mail1@yandex.ru");
        RESUME_2.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin1");

        RESUME_3.addContact(ContactType.PHONE, "3333333");
        RESUME_3.addContact(ContactType.SKYPE, "skype3");
        RESUME_3.addContact(ContactType.EMAIL, "mail2@yandex.ru");
        RESUME_3.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin2");
        RESUME_3.addContact(ContactType.GITHUB, "https://github.com/github1");
        RESUME_3.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/stackoverflow1");

        RESUME_4.addContact(ContactType.PHONE, "4444444");
        RESUME_4.addContact(ContactType.SKYPE, "skype4");
        RESUME_4.addContact(ContactType.EMAIL, "mail3@yandex.ru");
        RESUME_4.addContact(ContactType.GITHUB, "https://github.com/github2");
        RESUME_4.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin3");
        RESUME_4.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/stackoverflow2");
        RESUME_4.addContact(ContactType.HOME_PAGE, "http://homepage.ru/");

        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal_data1"));

        RESUME_2.addSection(SectionType.OBJECTIVE, new TextSection("Objective2"));
        RESUME_2.addSection(SectionType.PERSONAL, new TextSection("Personal_data2"));
        RESUME_2.addSection(SectionType.ACHIEVEMENT,
                new ListSection("Achievement1", "Achievement2", "Achievement3"));
        RESUME_2.addSection(SectionType.QUALIFICATIONS,
                new ListSection("Qualifications1", "Qualifications2", "Qualifications3"));

        RESUME_3.addSection(SectionType.OBJECTIVE, new TextSection("Objective3"));
        RESUME_3.addSection(SectionType.PERSONAL, new TextSection("Personal_data3"));
        RESUME_3.addSection(SectionType.ACHIEVEMENT,
                new ListSection("Achievement4", "Achievement5", "Achievement6"));
        RESUME_3.addSection(SectionType.QUALIFICATIONS,
                new ListSection("Qualifications1", "Qualifications2", "Qualifications3"));
        RESUME_3.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization1", "http://Organization1.ru",
                        new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY,
                                "position2", "content2"))));

        RESUME_3.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER,
                                        "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY,
                                        "student", "IT faculty")),
                        new Organization("Organization2", "http://Organization2.ru")));


        RESUME_4.addSection(SectionType.OBJECTIVE, new TextSection("Objective4"));
        RESUME_4.addSection(SectionType.PERSONAL, new TextSection("Personal_data4"));
        RESUME_4.addSection(SectionType.ACHIEVEMENT,
                new ListSection("Achievement7", "Achievement8", "Achievement9"));
        RESUME_4.addSection(SectionType.QUALIFICATIONS,
                new ListSection("Qualifications4", "Qualifications5", "Qualifications6"));
        RESUME_4.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization3", "http://Organization3",
                        new Organization.Position(2010, Month.JULY,
                                "position2", "content2"))));
        RESUME_4.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("University", "http://University.ru",
                        new Organization.Position(1999, Month.SEPTEMBER, 2003, Month.JUNE,
                                "student", "Computer science"))
        ));
    }
}
