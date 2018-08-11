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
    protected void saveElement(Resume r) {
        storage.add(r);
    }

    @Override
    protected void deleteElement(String uuid) {
        int index = findElementIndex(uuid);
        storage.remove(index);
    }

    @Override
    protected void updateElement(Resume r) {
        int index = findElementIndex(r.getUuid());
        storage.set(index, r);
    }

    @Override
    protected Resume getElement(String uuid) {
        int index = findElementIndex(uuid);
        return storage.get(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected boolean containsElement(String uuid) {
        return storage.contains(new Resume(uuid));
    }

    private int findElementIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }
}
