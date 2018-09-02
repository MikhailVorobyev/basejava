package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Map;

public class TestResume {
    public static void main(String[] args) {
        Contact contact1 = new Contact("phone number", "123-45-67");
        Contact contact2 = new Contact("e-mail", "email@gmail.com");
        Contact contact3 = new Contact("Skype", "skypelogin");

        Section personal = new StringSection("Personal", "some personal data");
        Section objective = new StringSection("Objective", "some objective data");

        Section achievement = new ArrayStringSection("Achievement",
                new String[]{"achievement1", "achievement2", "achievement3"} );
        Section qualification = new ArrayStringSection("Qualification",
                new String[]{"qualification1", "qualification2", "qualification3"});

        Section experience = new ExperienceSection("Best Company", "05.2013",
                "now", "Worker", "take more, throw further");
        Section education = new EducationSection("Best University", "07.2003",
                "06.2006", "best knowledge");

        Resume resume = new Resume("Mikhail Vorobyev");
        resume.addContact(contact1.getName(), contact1);
        resume.addContact(contact2.getName(), contact2);
        resume.addContact(contact3.getName(), contact3);

        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualification);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);

        System.out.println(resume.getFullName());
        System.out.println();

        for (Map.Entry<String, Contact> entry : resume.getContacts().entrySet()) {
            Contact contact = entry.getValue();
            System.out.println(contact);
        }
        System.out.println();

        for (Map.Entry<Enum<SectionType>, Section> entry : resume.getSections().entrySet()) {
            Section section = entry.getValue();
            System.out.println(section);
            System.out.println();
        }

        System.out.println(resume.getSections().get(SectionType.ACHIEVEMENT));

    }
}
