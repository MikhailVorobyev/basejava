package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    private <T> void writeForEach(Collection<T> collection, CustomConsumer<T> consumer,
                                  DataOutputStream dos) throws IOException {
        dos.writeInt(collection.size());
        Objects.requireNonNull(consumer);
        for (T t : collection) {
            consumer.accept(t);
        }
    }

    private <T> List<T> readForEach(CustomSupplier<T> supplier, DataInputStream dis) throws IOException {
        Objects.requireNonNull(supplier);
        List<T> resultList = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            resultList.add(supplier.get());
        }
        return resultList;
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();

            writeForEach(contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }, dos);

            Map<SectionType, Section> sections = resume.getSections();
            writeForEach(sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                writeSection(sectionType, entry.getValue(), dos);
            }, dos);
        }
    }

    private void writeSection(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                dos.writeUTF(((StringSection) section).getContent());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> listString = ((ListStringSection) section).getItems();
                writeForEach(listString, dos::writeUTF, dos);
                break;
            case EXPERIENCE:
            case EDUCATION:
                writeInstitutionSection((InstitutionSection) section, dos);
                break;
        }
    }

    private void writeInstitutionSection(InstitutionSection institutionSection,
                                         DataOutputStream dos) throws IOException {
        List<Institution> institutions = institutionSection.getInstitutions();
        writeForEach(institutions, institution -> {
            writeLink(institution.getHomePage(), dos);
            writePositions(institution.getPositions(), dos);
        }, dos);

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
        writeForEach(positions, position -> {
            dos.writeUTF(position.getStartDate().toString());
            dos.writeUTF(position.getEndDate().toString());
            dos.writeUTF(position.getTitle());
            String description = position.getDescription();
            if (description == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(description);
            }
        }, dos);
    }



    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);

            readSections(resume, ContactType.class, dis);
            readSections(resume, SectionType.class, dis);

        }
        return resume;
    }

    private void readSections(Resume resume, Class type, DataInputStream dis) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            if (ContactType.class == type) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            } else {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(sectionType, dis));
            }
        }
    }

    private Section readSection(SectionType sectionType, DataInputStream dis) throws IOException {
        Section section = null;
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                section = new StringSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = new ListStringSection(readForEach(dis::readUTF, dis));
                break;
            case EXPERIENCE:
            case EDUCATION:
                section = readInstitutionSection(dis);
                break;
        }
        return section;
    }

    private Section readInstitutionSection(DataInputStream dis) throws IOException {
        List<Institution> institutions = readForEach(() ->
                new Institution(readLink(dis), readPositions(dis)), dis);
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

    private List<Institution.Position> readPositions(DataInputStream dis) throws IOException {
        return readForEach(() -> {
            LocalDate startDate = LocalDate.parse(dis.readUTF());
            LocalDate endDate = LocalDate.parse(dis.readUTF());
            String title = dis.readUTF();
            String description = dis.readUTF();
            if ("".equals(description)) {
                description = null;
            }
            return new Institution.Position(startDate, endDate, title, description);
        }, dis);
    }
}