package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            if (o1.getFullName().compareTo(o2.getFullName()) == 0) {
                return -1;
            }
            return o1.getFullName().compareTo(o2.getFullName());
        }
    };


    @Override
    public void save(Resume resume) {
        Object elementKey = getExistElementKey(resume.getUuid());
        saveElement(resume, elementKey);
    }

    @Override
    public void delete(String uuid) {
        Object elementKey = getNotExistElementKey(uuid);
        deleteElement(elementKey);
    }

    @Override
    public void update(Resume resume) {
        Object elementKey = getNotExistElementKey(resume.getUuid());
        updateElement(resume, elementKey);
    }

    @Override
    public Resume get(String uuid) {
        Object elementKey = getNotExistElementKey(uuid);
        return getElement(elementKey);
    }

    private Object getExistElementKey(String uuid) {
        Object elementKey = findElementKey(uuid);
        if (containsElement(elementKey)) {
            throw new ExistStorageException(uuid);
        }
        return elementKey;
    }

    private Object getNotExistElementKey(String uuid) {
        Object elementKey = findElementKey(uuid);
        if (!containsElement(elementKey)) {
            throw new NotExistStorageException(uuid);
        }
        return elementKey;
    }

    protected abstract boolean containsElement(Object elementKey);

    protected abstract Object findElementKey(String uuid);

    protected abstract void updateElement(Resume resume, Object elementKey);

    protected abstract Resume getElement(Object elementKey);

    protected abstract void deleteElement(Object elementKey);

    protected abstract void saveElement(Resume resume, Object elementKey);
}

