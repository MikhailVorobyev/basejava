package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        int indexToPut = -index - 1;
        if (indexToPut < size) {
            System.arraycopy(storage, indexToPut, storage, indexToPut + 1, size - indexToPut);
        }
        storage[indexToPut] = r;

    }

    @Override
    protected void copyElement(int index) {
        int copyLength = size - 1 - index;
        if (copyLength > 0) {
            System.arraycopy(storage, index + 1, storage, index, copyLength);
        }

    }

    @Override
    protected int findElementIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
