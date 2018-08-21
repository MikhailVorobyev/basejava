package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveElement(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected Resume getElement(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public List<Resume> getAllSorted() {
        Collections.sort(storage, Resume.getResumeComparator());
        return storage;
    }

    @Override
    protected boolean containsElement(Object elementKey) {
        return elementKey != null;
    }

    @Override
    protected Integer findElementKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
