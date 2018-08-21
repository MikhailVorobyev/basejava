package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void replaceElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void sortList(List<Resume> list) {
        Collections.sort(list, Resume.getResumeComparator());
    }

    @Override
    protected Integer findElementKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
