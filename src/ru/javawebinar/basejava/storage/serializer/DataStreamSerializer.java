package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DataStreamSerializer implements StreamSerializer {
    private static final String NULL = "Null";
    private static final String STRING_SECTION = "StringSection";
    private static final String LIST_STRING_SECTION = "ListStringSection";
    private static final String INSTITUTION_SECTION = "InstitutionSection";

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = resume.getSections();
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                Section section = entry.getValue();
                writeSection(section, dos);
            }
        }
    }

    private void writeSection(Section section, DataOutputStream dos) throws IOException {
        String className = section.getClass().getSimpleName();
        dos.writeUTF(className);
        Consumer<String> consumer = x -> {
            try {
                dos.writeUTF(x);
            } catch (IOException e) {
                throw new StorageException("Write DataStream error", e);
            }
        };
        switch (className) {
            case STRING_SECTION:
                StringSection stringSection = (StringSection) section;
                writeStringSection(consumer, stringSection);
                break;
            case LIST_STRING_SECTION:
                ListStringSection listStringSection = (ListStringSection) section;
                dos.writeInt(listStringSection.getItems().size());
                writeListStringSection(consumer, listStringSection);
                break;
            case INSTITUTION_SECTION:
                InstitutionSection institutionSection = (InstitutionSection) section;
                dos.writeInt(institutionSection.getInstitutions().size());
                for (Institution institution : institutionSection.getInstitutions()) {
                    writeLink(consumer, institution.getHomePage());
                    dos.writeInt(institution.getPositions().size());
                    writePositions(consumer, institution.getPositions());
                }
                break;
        }
    }

    private void writeStringSection(Consumer<String> consumer, StringSection stringSection) throws IOException {
        consumer.accept(stringSection.getContent());
    }

    private void writeListStringSection(Consumer<String> consumer,
                                        ListStringSection listStringSection) throws IOException {
        for (String item : listStringSection.getItems()) {
            consumer.accept(item);
        }
    }

    private void writeLink(Consumer<String> consumer, Link link) throws IOException {
        consumer.accept(link.getName());
        String url = link.getUrl();
        if (url == null) {
            consumer.accept(NULL);
        } else {
            consumer.accept(url);
        }

    }

    private void writePositions(Consumer<String> consumer,
                                List<Institution.Position> positions) throws IOException {
        for (Institution.Position position : positions) {
            consumer.accept(position.getStartDate().toString());
            consumer.accept(position.getEndDate().toString());
            consumer.accept(position.getTitle());
            String description = position.getDescription();
            if (description == null) {
                consumer.accept(NULL);
            } else {
                consumer.accept(description);
            }
        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume = null;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            while (dis.available() > 0) {
                resume.addSection(SectionType.valueOf(dis.readUTF()), readSection(dis));
            }
        }
        return resume;
    }


    private Section readSection(DataInputStream dis) throws IOException {
        Section section = null;
        String sectionName = dis.readUTF();
        if (STRING_SECTION.equals(sectionName)) {
            section = readStringSection(dis);
        } else if (LIST_STRING_SECTION.equals(sectionName)) {
            section = readListStringSection(dis);
        } else if (INSTITUTION_SECTION.equals(sectionName)) {
            section = readInstitutionSection(dis);
        }
        return section;
    }

    private Section readStringSection(DataInputStream dis) throws IOException {
        return new StringSection(dis.readUTF());
    }

    private Section readListStringSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
        return new ListStringSection(items);

    }

    private Section readInstitutionSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Institution> institutions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            institutions.add(new Institution(readLink(dis), readPositions(dis)));
        }
        return new InstitutionSection(institutions);
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        if (NULL.equals(url)) {
            url = null;
        }
        return new Link(name, url);
    }

    private List<Institution.Position> readPositions(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Institution.Position> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            LocalDate startDate = LocalDate.parse(dis.readUTF());
            LocalDate endDate = LocalDate.parse(dis.readUTF());
            String title = dis.readUTF();
            String description = dis.readUTF();
            if (NULL.equals(description)) {
                description = null;
            }
            positions.add(new Institution.Position(startDate, endDate, title, description));
        }
        return positions;
    }
}