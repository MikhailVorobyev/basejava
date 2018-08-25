package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

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
    public void saveElement(Resume resume, Object elementKey) {
        storage.put(((Resume) elementKey).getUuid(), resume);
    }

    @Override
    public void deleteElement(Object elementKey) {
        storage.remove(((Resume) elementKey).getUuid());
    }

    @Override
    public void updateElement(Resume resume, Object elementKey) {
        storage.put(((Resume) elementKey).getUuid(), resume);
    }

    @Override
    public Resume getElement(Object elementKey) {
        return storage.get(((Resume) elementKey).getUuid());
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList(storage.values());
    }

    @Override
    protected boolean containsElement(Object elementKey) {
        return storage.containsKey(((Resume) elementKey).getUuid());
    }

    @Override
    protected Resume findElementKey(String uuid) {
        return new Resume(uuid, null);
    }
}
