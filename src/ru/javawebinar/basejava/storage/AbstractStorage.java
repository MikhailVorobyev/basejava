package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object elementKey = checkExistElement(resume.getUuid());
        saveElement(resume, elementKey);
    }

    @Override
    public void delete(String uuid) {
        Object elementKey = checkNotExistElement(uuid);
        deleteElement(elementKey);
    }

    @Override
    public void update(Resume resume) {
        Object elementKey = checkNotExistElement(resume.getUuid());
        updateElement(resume, elementKey);
    }

    @Override
    public Resume get(String uuid) {
        Object elementKey = checkNotExistElement(uuid);
        return getElement(elementKey);
    }

    private Object checkExistElement(String uuid) {
        Object foundKey = findElementKey(uuid);
        if (containsElement(foundKey)) {
            throw new ExistStorageException(uuid);
        }
        return foundKey;
    }

    private Object checkNotExistElement(String uuid) {
        Object foundKey = findElementKey(uuid);
        if (!containsElement(foundKey)) {
            throw new NotExistStorageException(uuid);
        }
        return foundKey;
    }

    protected abstract boolean containsElement(Object foundKey);

    protected abstract Object findElementKey(String uuid);

    protected abstract void updateElement(Resume resume, Object elementKey);

    protected abstract Resume getElement(Object elementKey);

    protected abstract void deleteElement(Object elementKey);

    protected abstract void saveElement(Resume resume, Object elementKey);
}

