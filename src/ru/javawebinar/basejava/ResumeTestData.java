package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.Section;
import ru.javawebinar.basejava.model.SectionType;

import java.util.Map;
import java.util.UUID;

public class ResumeTestData {
    private final Resume resume;

    public ResumeTestData(String fullName) {
        resume = new Resume(fullName);
    }

    public ResumeTestData(String uuid, String fullName) {
        resume = new Resume(uuid, fullName);

        //Contacts
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");


/*
        //Objective
        Section objective = new StringSection("Ведущий стажировок и корпоративного обучения по " +
                "Java Web и Enterprise технологиям");

        //Personal
        Section personal = new StringSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. " +
                "Пурист кода и архитектуры.");


        //Achievement
        List<String> achievementList = new ArrayList<>();
        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\"," +
                "\"Java Enterprise\", \"Многомодульный maven.\nМногопоточность. " +
                "XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\".\n" +
                "Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio,\nDuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM,\nCMIS, LDAP. Разработка приложения управления окружением " +
                "на стеке: Scala/Play/Anorm/JQuery. Разработка SSO\nаутентификации и авторизации различных " +
                "ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT\n(GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура,\nJAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации " +
                "о состоянии через систему мониторинга Nagios.\nРеализация онлайн клиента для " +
                "администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay,\nСбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        Section achievement = new ListStringSection(achievementList);


        //Qualification
        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA\n(Hibernate, EclipseLink), Guice, " +
                "GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, " +
                "JUnit,\nSelenium (htmlelements).");
        qualificationList.add("Python: Django.");
        qualificationList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, " +
                "StAX, SAX, DOM, XSLT, MDB, JMX,\nJDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, " +
                "Flyway, Nagios, iReport, OpenCmis, Bonita,\npgBouncer.");
        qualificationList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов");
        qualificationList.add("проектрирования, архитектурных шаблонов, UML, функционального");
        qualificationList.add("программирования");
        qualificationList.add("Родной русский, английский \"upper intermediate\"");

        Section qualification = new ListStringSection(qualificationList);


        //Experience
        Section experience = new InstitutionSection(
                new Institution("Java Online Projects", "http://javaops.ru/",
                        new Institution.Position(2013, Month.OCTOBER, "Автор проекта.",
                                "Создание, организация и проведение " +
                                        "Java онлайн проектов и стажировок.")),
                new Institution("Wrike", "https://www.wrike.com/",
                        new Institution.Position(2014, Month.OCTOBER, 2016, Month.JANUARY,
                                "Старший разработчик (backend).",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                        "(Java 8 API, Maven, Spring,\nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,\nJWT SSO.")),
                new Institution("RIT Center", null,
                        new Institution.Position(2012, Month.APRIL, 2014, Month.OCTOBER,
                                "Java архитектор.",
                                "Организация процесса разработки системы ERP для разных " +
                                        "окружений: релизная политика, версионирование,\nведение CI (Jenkins), " +
                                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),\n" +
                                        "AAA via SSO. Архитектура БД и серверной части системы. " +
                                        "Разработка интергационных сервисов: CMIS,\nBPMN2, 1C (WebServices), " +
                                        "сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                                        "Интеграция Alfresco\nJLAN для online редактирование из браузера документов " +
                                        "MS Office. Maven + plugin development, Ant, Apache\nCommons, " +
                                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                                        "Python scripting, Unix shell\nremote scripting via ssh tunnels, PL/Python")),
                new Institution("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        new Institution.Position(2010, Month.DECEMBER, 2012, Month.APRIL,
                                "Ведущий программист.",
                                "Участие в проекте Deutsche Bank CRM " +
                                        "(WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,\nOracle)." +
                                        " Реализация клиентской и серверной части CRM. " +
                                        "Реализация RIA-приложения для\nадминистрирования, мониторинга и " +
                                        "анализа результатов в области алгоритмического трейдинга. JPA, Spring,\n" +
                                        "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")),
                new Institution("Yota", "https://www.yota.ru/",
                        new Institution.Position(2008, Month.JUNE, 2010, Month.DECEMBER,
                                "Ведущий специалист.",
                                "Дизайн и имплементация Java EE фреймворка для отдела " +
                                        "\"Платежные Системы\" (GlassFish v2.1, v3, OC4J,\nEJB3, JAX-WS RI 2.1, " +
                                        "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики " +
                                        "и\nмониторинга фреймворка. Разработка online JMX клиента " +
                                        "(Python/ Jython, Django, ExtJS)")),
                new Institution("Enkata", "http://enkata.com/",
                        new Institution.Position(2007, Month.MARCH, 2008, Month.JUNE,
                                "Разработчик ПО.",
                                "Реализация клиентской (Eclipse RCP) и серверной " +
                                        "(JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного\nJ2EE приложения " +
                                        "(OLAP, Data mining).")),
                new Institution("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        new Institution.Position(2005, Month.JANUARY, 2007, Month.FEBRUARY,
                                "Разработчик ПО.",
                                "Разработка информационной модели, проектирование интерфейсов, " +
                                        "реализация и отладка ПО на мобильной\n" +
                                        "IN платформе Siemens @vantage (Java, Unix).")),
                new Institution("Alcatel", "http://www.alcatel.ru/",
                        new Institution.Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY,
                                "Инженер по аппаратному и программному тестированию.",
                                "Тестирование, отладка, " +
                                        "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")));


        //Education
        Section education = new InstitutionSection(
                new Institution("Coursera", "https://www.coursera.org/course/progfun",
                        new Institution.Position(2013, Month.MARCH, 2013, Month.MAY,
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                null)),
                new Institution("Luxoft",
                        "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        new Institution.Position(2011, Month.MARCH, 2011, Month.APRIL,
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                null)),
                new Institution("Siemens AG", "http://www.siemens.ru/",
                        new Institution.Position(2005, Month.JANUARY, 2005, Month.APRIL,
                                "3 месяца обучения мобильным IN сетям (Берлин)", null)),
                new Institution("Alcatel", "http://www.alcatel.ru/",
                        new Institution.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH,
                                "6 месяцев обучения цифровым телефонным сетям (Москва)", null)),
                new Institution("Санкт-Петербургский национальный исследовательский университет " +
                        "информационных технологий, механики и оптики",
                        "http://www.ifmo.ru/",
                        new Institution.Position(1993, Month.SEPTEMBER, 1996, Month.JULY,
                                "Аспирантура (программист С, С++)", null),
                        new Institution.Position(1987, Month.SEPTEMBER, 1993, Month.JULY,
                                "Инженер (программист Fortran, C)", null)),
                new Institution("Заочная физико-техническая школа при МФТИ",
                        "http://www.school.mipt.ru/",
                        new Institution.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE,
                                "Закончил с отличием", null)));


        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualification);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);
*/
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
