package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    private <T> void forEach(Collection<T> collection, CustomConsumer<T> consumer) throws IOException {
        Objects.requireNonNull(consumer);
        for (T t : collection) {
            consumer.accept(t);
        }
    }

    private <T> List<T> forEach(int size, CustomSupplier<T> supplier) throws IOException {
        Objects.requireNonNull(supplier);
        List<T> resultList = new ArrayList<>();
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
            dos.writeInt(contacts.size());

            forEach(contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            forEach(sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                writeSection(sectionType, entry.getValue(), dos);
            });
        }
    }

    private void writeSection(SectionType sectionType, Section section, DataOutputStream dos) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                writeStringSection((StringSection) section, dos);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeListStringSection((ListStringSection) section, dos);
                break;
            case EXPERIENCE:
            case EDUCATION:
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
        forEach(listString, dos::writeUTF);
    }

    private void writeInstitutionSection(InstitutionSection institutionSection,
                                         DataOutputStream dos) throws IOException {
        List<Institution> institutions = institutionSection.getInstitutions();
        dos.writeInt(institutions.size());
        forEach(institutions, institution -> {
            writeLink(institution.getHomePage(), dos);
            writePositions(institution.getPositions(), dos);
        });

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
        forEach(positions, position -> {
            dos.writeUTF(position.getStartDate().toString());
            dos.writeUTF(position.getEndDate().toString());
            dos.writeUTF(position.getTitle());
            String description = position.getDescription();
            if (description == null) {
                dos.writeUTF("");
            } else {
                dos.writeUTF(description);
            }
        });
    }



    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            while (dis.available() > 0) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(sectionType, dis));
            }
        }
        return resume;
    }

    private Section readSection(SectionType sectionType, DataInputStream dis) throws IOException {
        Section section = null;
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                section = readStringSection(dis);
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = readListStringSection(dis);
                break;
            case EXPERIENCE:
            case EDUCATION:
                section = readInstitutionSection(dis);
                break;
        }
        return section;
    }

    private Section readStringSection(DataInputStream dis) throws IOException {
        return new StringSection(dis.readUTF());
    }

    private Section readListStringSection(DataInputStream dis) throws IOException {
        return new ListStringSection(forEach(dis.readInt(), dis::readUTF));
    }


    private Section readInstitutionSection(DataInputStream dis) throws IOException {
        int size = dis.readInt();
        List<Institution> institutions = forEach(size, () ->
                new Institution(readLink(dis), readPositions(dis)));
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
        int size = dis.readInt();
        return forEach(size, () -> {
            LocalDate startDate = LocalDate.parse(dis.readUTF());
            LocalDate endDate = LocalDate.parse(dis.readUTF());
            String title = dis.readUTF();
            String description = dis.readUTF();
            if ("".equals(description)) {
                description = null;
            }
            return new Institution.Position(startDate, endDate, title, description);
        });
    }
}