package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>(); //key for Map - uuid

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void saveElement(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    public void deleteElement(Object key) {
        storage.remove(key);
    }

    @Override
    public void updateElement(Resume resume, Object key) {
        storage.put((String) key, resume);
    }

    @Override
    public Resume getElement(Object key) {
        return storage.get(key);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected boolean containsElement(Object foundKey) {
        return storage.containsKey(foundKey);
    }

    @Override
    protected Object findElementKey(String uuid) {
        return uuid;
    }
}
