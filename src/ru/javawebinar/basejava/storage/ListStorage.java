package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
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
        storage.remove((int) index);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        storage.set((int) index, resume);
    }

    @Override
    protected Resume getElement(Object index) {
        return storage.get((int) index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected boolean containsElement(Object foundKey) {
        return (int) foundKey >= 0;
    }

    @Override
    protected Object findElementKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
