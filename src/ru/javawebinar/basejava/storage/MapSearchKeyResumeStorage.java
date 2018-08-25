package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapSearchKeyResumeStorage extends AbstractMapStorage {

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void saveElement(Resume resume, Object elementKey) {
        storage.put((String) elementKey, resume);
    }

    @Override
    protected void deleteElement(Object elementKey) {
        storage.remove(elementKey);
    }

    @Override
    protected void updateElement(Resume resume, Object elementKey) {
        storage.put((String) elementKey, resume);
    }

    @Override
    protected Resume getElement(Object elementKey) {
        return storage.get(elementKey);
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
