package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
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
    protected List<Resume> getSortedList() {
        List<Resume> resumeList = Arrays.asList(Arrays.copyOf(storage, size));
        Collections.sort(resumeList);
        return resumeList;
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
