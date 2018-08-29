package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveElement(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("The array is full!", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    @Override
    protected void deleteElement(Integer index) {
        replaceElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage[index];
    }

    @Override
    protected List<Resume> getCopyResumeList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean containsElement(Integer index) {
        return index >= 0;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void replaceElement(int index);

}
