package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.FunctionRead;
import ru.javawebinar.basejava.util.FunctionWrite;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
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
        switch (className) {
            case STRING_SECTION:
                writeStringSection((StringSection) section, dos);
                break;
            case LIST_STRING_SECTION:
                writeListStringSection((ListStringSection) section, dos);
                break;
            case INSTITUTION_SECTION:
                writeInstitutionSection((InstitutionSection) section, dos);
                break;
        }
    }

    private void writeStringSection(StringSection stringSection, DataOutputStream dos) throws IOException {
        dos.writeUTF(stringSection.getContent());
    }

    private void writeListStringSection(ListStringSection listStringSection,
                                        DataOutputStream dos) throws IOException {
        List<String> listString = listStringSection.getItems();
        dos.writeInt(listString.size());
        FunctionWrite.forEach(listString, dos::writeUTF);
    }

    private void writeInstitutionSection(InstitutionSection institutionSection,
                                         DataOutputStream dos) throws IOException {
        List<Institution> institutions = institutionSection.getInstitutions();
        dos.writeInt(institutions.size());
        FunctionWrite.forEach(institutions, f -> writeLink(f.getHomePage(), dos),
                f -> writePositions(f.getPositions(), dos));
    }

    private void writeLink(Link link, DataOutputStream dos) throws IOException {
        dos.writeUTF(link.getName());
        String url = link.getUrl();
        if (url == null) {
            dos.writeUTF("");
        } else {
            dos.writeUTF(url);
        }
    }

    private void writePositions(List<Institution.Position> positions, DataOutputStream dos) throws IOException {
        dos.writeInt(positions.size());
        for (Institution.Position position : positions) {
            dos.writeUTF(position.getStartDate().toString());
            dos.writeUTF(position.getEndDate().toString());
            dos.writeUTF(position.getTitle());
            String description = position.getDescription();
            if (description == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(description);
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
        switch (sectionName) {
            case STRING_SECTION:
                section = readStringSection(dis);
                break;
            case LIST_STRING_SECTION:
                section = readListStringSection(dis);
                break;
            case INSTITUTION_SECTION:
                section = readInstitutionSection(dis);
                break;
        }
        return section;
    }

    private Section readStringSection(DataInputStream dis) throws IOException {
        return new StringSection(dis.readUTF());
    }

    private Section readListStringSection(DataInputStream dis) throws IOException {
        return new ListStringSection(FunctionRead.forEach(dis.readInt(), dis::readUTF));
    }


    private Section readInstitutionSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Institution> institutions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            institutions.add(new Institution(readLink(dis),
                    FunctionRead.forEach(dis.readInt(), () -> readPositions(dis))));
        }
        return new InstitutionSection(institutions);
    }

    private Link readLink(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        String url = dis.readUTF();
        if ("".equals(url)) {
            url = null;
        }
        return new Link(name, url);
    }

    private Institution.Position readPositions(DataInputStream dis) throws IOException {
        LocalDate startDate = LocalDate.parse(dis.readUTF());
        LocalDate endDate = LocalDate.parse(dis.readUTF());
        String title = dis.readUTF();
        String description = dis.readUTF();
        if ("".equals(description)) {
            description = null;
        }
        return new Institution.Position(startDate, endDate, title, description);
    }
}