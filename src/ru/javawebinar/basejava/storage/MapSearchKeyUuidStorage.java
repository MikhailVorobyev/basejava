package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapSearchKeyUuidStorage extends AbstractMapStorage {

    @Override
    protected void saveElement(Resume resume, Object uuidKey) {
        storage.put((String) uuidKey, resume);
    }

    @Override
    protected void deleteElement(Object uuidKey) {
        storage.remove((String) uuidKey);
    }

    @Override
    protected void updateElement(Resume resume, Object uuidKey) {
        storage.put((String) uuidKey, resume);
    }

    @Override
    protected Resume getElement(Object uuidKey) {
        return storage.get((String) uuidKey);
    }

    @Override
    protected boolean containsElement(Object uuidKey) {
        return storage.containsKey((String) uuidKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
