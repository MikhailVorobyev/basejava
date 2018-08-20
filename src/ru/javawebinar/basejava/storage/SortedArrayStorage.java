package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {
/*

    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume resume1, Resume resume2) {
            return resume1.getUuid().compareTo(resume2.getUuid());
        }
    }

*/

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
    protected void sortList(List<Resume> list) {}

    @Override
    protected Integer findElementKey(String uuid) {
        Resume searchKey = new Resume(uuid, null);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
