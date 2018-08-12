package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        int indexToPut = -index - 1;
        if (indexToPut < size) {
            System.arraycopy(storage, indexToPut, storage, indexToPut + 1, size - indexToPut);
        }
        storage[indexToPut] = resume;

    }

    @Override
    protected void replaceElement(int index) {
        int copyLength = size - 1 - index;
        if (copyLength > 0) {
            System.arraycopy(storage, index + 1, storage, index, copyLength);
        }

    }

    @Override
    protected Object findElementKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
