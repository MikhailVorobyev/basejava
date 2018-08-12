package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>(); //key for Map - uuid

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void saveElement(Resume resume, int index) {

    }

    @Override
    public void deleteElement(String uuid, int index) {

    }

    @Override
    public void updateElement(Resume resume, int index) {

    }

    @Override
    public Resume getElement(String uuid, int index) {
        return null;
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    protected boolean containsElement(String uuid) {
        return false;
    }

    protected int findElementIndex(String uuid) {
        return 0;
    }
}
