package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
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
    protected void saveElement(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("The array is full!", resume.getUuid());
        } else {
            insertElement(resume, (int) index);
            size++;
        }
    }

    @Override
    protected void deleteElement(Object index) {
        replaceElement((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    protected Resume getElement(Object index) {
        return storage[(int) index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean containsElement(Object foundKey) {
        return (int) foundKey >= 0;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void replaceElement(int index);
}
