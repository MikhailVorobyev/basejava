package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
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
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        Section objective = new StringSection("Ведущий стажировок и корпоративного обучения по " +
                                            "Java Web и Enterprise технологиям");
        Section personal = new StringSection("Аналитический склад ума, сильная логика, " +
                                            "креативность, инициативность. " +
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

        Section achievement = new ListStringSection(achievementList);

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


        List<Institution> expInstitutions = new ArrayList<>();
        expInstitutions.add(new Institution("Java Online Projects", "http://javaops.ru/",
                            DateUtil.of(2013, Month.OCTOBER), LocalDate.now(),
                        "Автор проекта.", "Создание, организация и проведение " +
                            "Java онлайн проектов и стажировок."));
        expInstitutions.add(new Institution("Wrike", "https://www.wrike.com/",
                                    DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016, Month.JANUARY),
                                    "Старший разработчик (backend).",
                                    "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                    "(Java 8 API, Maven, Spring,\nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                    "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2,\nJWT SSO."));
        expInstitutions.add(new Institution("RIT Center", null, DateUtil.of(2012, Month.APRIL),
                                    DateUtil.of(2014, Month.OCTOBER),
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
                                    "Python scripting, Unix shell\nremote scripting via ssh tunnels, PL/Python"));
        expInstitutions.add(new Institution("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                                    DateUtil.of(2010, Month.DECEMBER), DateUtil.of(2012, Month.APRIL),
                                    "Ведущий программист.", "Участие в проекте Deutsche Bank CRM " +
                                    "(WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper,\nOracle)." +
                                    " Реализация клиентской и серверной части CRM. " +
                                    "Реализация RIA-приложения для\nадминистрирования, мониторинга и " +
                                    "анализа результатов в области алгоритмического трейдинга. JPA, Spring,\n" +
                                    "Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        expInstitutions.add(new Institution("Yota", "https://www.yota.ru/", DateUtil.of(2008, Month.JUNE),
                                    DateUtil.of(2010, Month.DECEMBER),
                                    "Ведущий специалист.",
                                    "Дизайн и имплементация Java EE фреймворка для отдела " +
                                    "\"Платежные Системы\" (GlassFish v2.1, v3, OC4J,\nEJB3, JAX-WS RI 2.1, " +
                                    "Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики " +
                                    "и\nмониторинга фреймворка. Разработка online JMX клиента " +
                                    "(Python/ Jython, Django, ExtJS)"));
        expInstitutions.add(new Institution("Enkata", "http://enkata.com/", DateUtil.of(2007, Month.MARCH),
                                    DateUtil.of(2008, Month.JUNE),
                                    "Разработчик ПО.", "Реализация клиентской (Eclipse RCP) и серверной " +
                                    "(JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного\nJ2EE приложения " +
                                    "(OLAP, Data mining)."));
        expInstitutions.add(new Institution("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                                    DateUtil.of(2005, Month.JANUARY), DateUtil.of(2007, Month.FEBRUARY),
                                    "Разработчик ПО.",
                                    "Разработка информационной модели, проектирование интерфейсов, " +
                                    "реализация и отладка ПО на мобильной\nIN платформе Siemens @vantage (Java, Unix)."));
        expInstitutions.add(new Institution("Alcatel", "http://www.alcatel.ru/",
                                    DateUtil.of(1997, Month.SEPTEMBER),
                                    DateUtil.of(2005, Month.JANUARY),
                                    "Инженер по аппаратному и программному тестированию.",
                                    "Тестирование, отладка, " +
                                    "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        Section experience = new InstitutionSection(expInstitutions);

        List<Institution> educInstitutions = new ArrayList<>();
        educInstitutions.add(new Institution("Coursera", "https://www.coursera.org/course/progfun",
                                DateUtil.of(2013 , Month.MARCH), DateUtil.of(2013, Month.MAY),
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                null));

        educInstitutions.add(new Institution("Luxoft",
                            "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                            DateUtil.of(2011, Month.MARCH), DateUtil.of(2011, Month.APRIL),
                            "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                            null));
        educInstitutions.add(new Institution("Siemens AG", "http://www.siemens.ru/",
                            DateUtil.of(2005, Month.JANUARY),
                            DateUtil.of(2005, Month.APRIL),
                            "3 месяца обучения мобильным IN сетям (Берлин)", null));
        educInstitutions.add(new Institution("Alcatel", "http://www.alcatel.ru/",
                            DateUtil.of(1997, Month.SEPTEMBER),
                            DateUtil.of(1998, Month.MARCH),
                            "6 месяцев обучения цифровым телефонным сетям (Москва)", null));


        String institutionName = "Санкт-Петербургский национальный исследовательский университет " +
                                "информационных технологий, механики и оптики";
        educInstitutions.add(new Institution(institutionName, "http://www.ifmo.ru/",
                            DateUtil.of(1993, Month.SEPTEMBER), DateUtil.of(1996, Month.JULY),
                            "Аспирантура (программист С, С++)", null));
        //education.addPeriodOfActivity(institutionName, "09/1987",
                            //"07/1993", "Инженер (программист Fortran, C)");


        educInstitutions.add(new Institution("Заочная физико-техническая школа при МФТИ",
                            "http://www.school.mipt.ru/", DateUtil.of(1984, Month.SEPTEMBER),
                            DateUtil.of(1987, Month.JUNE),
                            "Закончил с отличием", null));
        Section education = new InstitutionSection(educInstitutions);

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
            System.out.println(entry.getKey().getTitle());
            System.out.println(entry.getValue());
            System.out.println();
        }
    }
}
