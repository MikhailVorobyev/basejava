package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        if (containsElement(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        }
        int index = findElementIndex(resume.getUuid());
        saveElement(resume, index);
    }

    @Override
    public void delete(String uuid) {
        if (!containsElement(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        int index = findElementIndex(uuid);
        deleteElement(uuid, index);
    }

    @Override
    public void update(Resume resume) {
        if (!containsElement(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        }
        int index = findElementIndex(resume.getUuid());
        updateElement(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        if (!containsElement(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        int index = findElementIndex(uuid);
        return getElement(uuid, index);
    }

    protected abstract boolean containsElement(String uuid);

    protected abstract int findElementIndex(String uuid);

    protected abstract void updateElement(Resume resume, int index);

    protected abstract Resume getElement(String uuid, int index);

    protected abstract void deleteElement(String uuid, int index);

    protected abstract void saveElement(Resume resume, int index);
}
