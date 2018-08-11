package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        if (containsElement(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        saveElement(r);
    }

    @Override
    public void delete(String uuid) {
        if (!containsElement(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        deleteElement(uuid);
    }

    @Override
    public void update(Resume r) {
        if (!containsElement(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateElement(r);
    }

    @Override
    public Resume get(String uuid) {
        if (!containsElement(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getElement(uuid);
    }

    protected abstract boolean containsElement(String uuid);

    protected abstract void updateElement(Resume r);

    protected abstract Resume getElement(String uuid);

    protected abstract void deleteElement(String uuid);

    protected abstract void saveElement(Resume r);

}
