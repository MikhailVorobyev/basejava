package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object elementKey = getNotExistElementKey(resume.getUuid());
        saveElement(resume, elementKey);
    }

    @Override
    public void delete(String uuid) {
        Object elementKey = getExistElementKey(uuid);
        deleteElement(elementKey);
    }

    @Override
    public void update(Resume resume) {
        Object elementKey = getExistElementKey(resume.getUuid());
        updateElement(resume, elementKey);
    }

    @Override
    public Resume get(String uuid) {
        Object elementKey = getExistElementKey(uuid);
        return getElement(elementKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getResumeList();
        Collections.sort(resumeList);
        return resumeList;
    }

    private Object getNotExistElementKey(String uuid) {
        Object elementKey = findElementKey(uuid);
        if (containsElement(elementKey)) {
            throw new ExistStorageException(uuid);
        }
        return elementKey;
    }

    private Object getExistElementKey(String uuid) {
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

    protected abstract List<Resume> getResumeList();
}

