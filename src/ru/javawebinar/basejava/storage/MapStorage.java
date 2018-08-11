package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void saveElement(Resume r) {

    }

    @Override
    public void updateElement(Resume r) {

    }

    @Override
    public Resume getElement(String uuid) {
        return null;
    }

    @Override
    public void deleteElement(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    protected boolean containsElement(String uuid) {
        return false;
    }
}
