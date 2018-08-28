package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistSearchKey(resume.getUuid());
        saveElement(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        deleteElement(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        updateElement(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return getElement(searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getCopyResumeList();
        Collections.sort(resumeList);
        return resumeList;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (containsElement(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!containsElement(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean containsElement(Object elementKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateElement(Resume resume, Object elementKey);

    protected abstract Resume getElement(Object elementKey);

    protected abstract void deleteElement(Object elementKey);

    protected abstract void saveElement(Resume resume, Object elementKey);

    protected abstract List<Resume> getCopyResumeList();
}

