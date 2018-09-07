package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestResume {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STAKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        Section objective = new StringSection(SectionType.OBJECTIVE.getTitle(),
                "Ведущий стажировок и корпоративного обучения по " +
                        "Java Web и Enterprise технологиям");
        Section personal = new StringSection(SectionType.PERSONAL.getTitle(),
                "Аналитический склад ума, сильная логика, креативность, инициативность. " +
                        "Пурист кода и архитектуры.");

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
        Section achievement = new ListStringSection(SectionType.ACHIEVEMENT.getTitle(), achievementList);

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

        Section qualification = new ListStringSection(SectionType.QUALIFICATIONS.getTitle(), qualificationList);

        InstitutionSection experience = new InstitutionSection(SectionType.EXPERIENCE.getTitle());
        experience.addInstitution("Java Online Projects", "http://javaops.ru/",
                            "10/2013", "Сейчас",
                            "Автор проекта.\n" +
                                    "Создание, организация и проведение Java онлайн проектов и стажировок.");
        experience.addInstitution("Wrike", "https://www.wrike.com/",
                            "10/2014", "01/2016",
                            "Старший разработчик (backend)\n" +
                                    "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                    "(Java 8 API, Maven, Spring,\nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                    "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,\nJWT SSO.");
        experience.addInstitution("RIT Center", "", "04/2012", "10/2014",
                            "Java архитектор\nОрганизация процесса разработки системы ERP для разных " +
                                    "окружений: релизная политика, версионирование,\nведение CI (Jenkins), " +
                                    "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx),\n" +
                                    "AAA via SSO. Архитектура БД и серверной части системы. " +
                                    "Разработка интергационных сервисов: CMIS,\nBPMN2, 1C (WebServices), " +
                                    "сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                                    "Интеграция Alfresco\nJLAN для online редактирование из браузера документов " +
                                    "MS Office. Maven + plugin development, Ant, Apache\nCommons, " +
                                    "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, " +
                                    "Python scripting, Unix shell\nremote scripting via ssh tunnels, PL/Python");
        experience.addInstitution("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                            "12/2010", "04/2012",
                            "Ведущий программист\nУчастие в проекте Deutsche Bank CRM " +
                                    "(WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,\nOracle)." +
                                    " Реализация клиентской и серверной части CRM. " +
                                    "Реализация RIA-приложения для\nадминистрирования, мониторинга и " +
                                    "анализа результатов в области алгоритмического трейдинга. JPA, Spring,\n" +
                                    "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        experience.addInstitution("Yota", "https://www.yota.ru/", "06/2008",
                            "12/2010",
                            "Ведущий специалист\nДизайн и имплементация Java EE фреймворка для отдела " +
                                    "\"Платежные Системы\" (GlassFish v2.1, v3, OC4J,\nEJB3, JAX-WS RI 2.1, " +
                                    "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики " +
                                    "и\nмониторинга фреймворка. Разработка online JMX клиента " +
                                    "(Python/ Jython, Django, ExtJS)");
        experience.addInstitution("Enkata", "http://enkata.com/", "03/2007",
                            "06/2008",
                            "Разработчик ПО\nРеализация клиентской (Eclipse RCP) и серверной " +
                                    "(JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного\nJ2EE приложения " +
                                    "(OLAP, Data mining).");
        experience.addInstitution("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                            "01/2005", "02/2007",
                            "Разработчик ПО\nРазработка информационной модели, проектирование интерфейсов, " +
                                    "реализация и отладка ПО на мобильной\nIN платформе Siemens @vantage (Java, Unix).");
        experience.addInstitution("Alcatel", "http://www.alcatel.ru/", "09/1997",
                            "01/2005",
                            "Инженер по аппаратному и программному тестированию\nТестирование, отладка, " +
                                    "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");


        InstitutionSection education = new InstitutionSection(SectionType.EDUCATION.getTitle());
        education.addInstitution("Coursera", "https://www.coursera.org/course/progfun",
                            "03/2013", "05/2013",
                            "\"Functional Programming Principles in Scala\" by Martin Odersky");
        education.addInstitution("Luxoft",
                            "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                            "03/2011", "04/2011",
                            "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        education.addInstitution("Siemens AG", "http://www.siemens.ru/", "01/2005",
                            "04/2005",
                            "3 месяца обучения мобильным IN сетям (Берлин)");
        education.addInstitution("Alcatel", "http://www.alcatel.ru/", "09/1997",
                            "03/1998",
                            "6 месяцев обучения цифровым телефонным сетям (Москва)");


        String institutionName = "Санкт-Петербургский национальный исследовательский университет " +
                                "информационных технологий, механики и оптики";
        education.addInstitution(institutionName, "http://www.ifmo.ru/",
                            "09/1993", "07/1996", "Аспирантура (программист С, С++)");
        education.addPeriodOfActivity(institutionName, "09/1987",
                            "07/1993", "Инженер (программист Fortran, C)");


        education.addInstitution("Заочная физико-техническая школа при МФТИ",
                            "http://www.school.mipt.ru/", "09/1984", "06/1987",
                            "Закончил с отличием");

        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualification);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);


        System.out.println(resume.getFullName());
        System.out.println();

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": " + entry.getValue());
        }
        System.out.println();
        System.out.println();

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            Section section = entry.getValue();
            System.out.println(section);
            System.out.println();
        }
    }
}
