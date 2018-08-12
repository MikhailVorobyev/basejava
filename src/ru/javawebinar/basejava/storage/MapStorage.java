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
        return 0;
    }

    @Override
    public void saveElement(Resume resume, Object key) {

    }

    @Override
    public void deleteElement(Object key) {

    }

    @Override
    public void updateElement(Resume resume, Object key) {

    }

    @Override
    public Resume getElement(Object key) {
        return null;
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    protected boolean containsElement(Object foundKey) {
        return false;
    }

    @Override
    protected Object findElementKey(String uuid) {
        return null;
    }
}
