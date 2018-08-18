package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
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
    public void saveElement(Resume resume, Object elementKey) {
        storage.put((String) elementKey, resume);
    }

    @Override
    public void deleteElement(Object elementKey) {
        storage.remove(elementKey);
    }

    @Override
    public void updateElement(Resume resume, Object elementKey) {
        storage.put((String) elementKey, resume);
    }

    @Override
    public Resume getElement(Object elementKey) {
        return storage.get(elementKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList(storage.values());
        Collections.sort(resumes);
        return resumes;
    }

    @Override
    protected boolean containsElement(Object elementKey) {
        return storage.containsKey(elementKey);
    }

    @Override
    protected String findElementKey(String uuid) {
        return uuid;
    }
}