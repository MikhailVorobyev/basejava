package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionType;

import java.util.Map;
import java.util.UUID;

public class ResumeTestData {
    private final Resume resume;

    public ResumeTestData(String uuid, String fullName) {
        resume = new Resume(uuid, fullName);
    }

    public Resume addContacts1() {
        resume.addContact(ContactType.PHONE, "1111111");
        resume.addContact(ContactType.SKYPE, "skype1");
        return resume;
    }

    public Resume addContacts2() {
        resume.addContact(ContactType.PHONE, "2222222");
        resume.addContact(ContactType.SKYPE, "skype2");
        resume.addContact(ContactType.EMAIL, "mail1@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin1");
        return resume;
    }

    public Resume addContacts3() {
        resume.addContact(ContactType.PHONE, "3333333");
        resume.addContact(ContactType.SKYPE, "skype3");
        resume.addContact(ContactType.EMAIL, "mail2@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin2");
        resume.addContact(ContactType.GITHUB, "https://github.com/github1");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/stackoverflow1");
        return resume;
    }

    public Resume addContacts4() {
        resume.addContact(ContactType.PHONE, "4444444");
        resume.addContact(ContactType.SKYPE, "skype4");
        resume.addContact(ContactType.EMAIL, "mail3@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin3");
        resume.addContact(ContactType.GITHUB, "https://github.com/github2");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/stackoverflow2");
        resume.addContact(ContactType.HOME_PAGE, "http://homepage.ru/");
        return resume;
    }

    public Resume getResume() {
        return resume;
    }

    public static void main(String[] args) {
        ResumeTestData resumeTestData = new ResumeTestData(UUID.randomUUID().toString(), "Григорий Кислин");
        Resume resume = resumeTestData.resume;

        System.out.println(resume.getFullName());
        System.out.println();

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        System.out.println();
        System.out.println();

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            System.out.println(entry.getKey().getTitle());
            System.out.println(entry.getValue());
            System.out.println();
        }
    }
}
