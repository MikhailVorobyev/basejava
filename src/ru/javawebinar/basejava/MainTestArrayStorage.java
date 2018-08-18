package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    //private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1");
        Resume resume2 = new Resume("uuid2");
        Resume resume3 = new Resume("uuid3");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        printAll();

        ARRAY_STORAGE.delete(resume1.getUuid());
        printAll();

        Resume updateR3 = new Resume("uuid3");
        ARRAY_STORAGE.update(resume3);

        Resume dummy = new Resume("uuid4");
        ARRAY_STORAGE.update(dummy);

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(resume);
        }
    }
}
