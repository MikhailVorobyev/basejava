package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();

            writeForEach(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> sections = resume.getSections();
            writeForEach(dos, sections.entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                writeSection(dos, sectionType, entry.getValue());
            });
        }
    }

    private void writeSection(DataOutputStream dos, SectionType sectionType, Section section) throws IOException {
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                dos.writeUTF(((StringSection) section).getContent());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                writeForEach(dos, ((ListStringSection) section).getItems(), dos::writeUTF);
                break;
            case EXPERIENCE:
            case EDUCATION:
                writeForEach(dos, ((InstitutionSection) section).getInstitutions(), institution -> {
                    dos.writeUTF(institution.getHomePage().getName());
                    dos.writeUTF(institution.getHomePage().getUrl());
                    writeForEach(dos, institution.getPositions(), position -> {
                        writeLocalDate(dos, position.getStartDate());
                        writeLocalDate(dos, position.getEndDate());
                        dos.writeUTF(position.getTitle());
                        dos.writeUTF(position.getDescription());
                    });
                });
                break;
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);

            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });

        }
        return resume;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        Section section = null;
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                section = new StringSection(dis.readUTF());
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                section = new ListStringSection(readForEach(dis, dis::readUTF));
                break;
            case EXPERIENCE:
            case EDUCATION:
                section = new InstitutionSection(readForEach(dis, () ->
                        new Institution(new Link(dis.readUTF(), dis.readUTF()),
                                readForEach(dis, () -> new Institution.Position(
                                        readLocalDate(dis),
                                        readLocalDate(dis),
                                        dis.readUTF(),
                                        dis.readUTF()
                                )))));
                break;
        }
        return section;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    @FunctionalInterface
    public interface CustomConsumer<T> {
        void accept(T a) throws IOException;
    }

    private <T> void writeForEach(DataOutputStream dos, Collection<T> collection,
                                  CustomConsumer<T> consumer) throws IOException {
        dos.writeInt(collection.size());
        Objects.requireNonNull(consumer);
        for (T t : collection) {
            consumer.accept(t);
        }
    }

    @FunctionalInterface
    public interface CustomSupplier<T> {
        T get() throws IOException;
    }

    private <T> List<T> readForEach(DataInputStream dis, CustomSupplier<T> supplier) throws IOException {
        Objects.requireNonNull(supplier);
        List<T> resultList = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            resultList.add(supplier.get());
        }
        return resultList;
    }

    @FunctionalInterface
    public interface ElementProcessor {
        void process() throws IOException;
    }
}