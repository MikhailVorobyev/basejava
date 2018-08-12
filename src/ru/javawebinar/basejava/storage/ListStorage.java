package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(String uuid, int index) {
        storage.remove(index);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    protected Resume getElement(String uuid, int index) {
        return storage.get(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected boolean containsElement(String uuid) {
        return storage.contains(new Resume(uuid));
    }

    @Override
    protected int findElementIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
