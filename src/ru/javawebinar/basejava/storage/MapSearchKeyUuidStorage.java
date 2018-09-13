package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSearchKeyUuidStorage extends AbstractStorage<String> {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
    
    @Override
    protected void doSave(Resume resume, String uuidKey) {
        storage.put(uuidKey, resume);
    }

    @Override
    protected void doDelete(String uuidKey) {
        storage.remove(uuidKey);
    }

    @Override
    protected void doUpdate(Resume resume, String uuidKey) {
        storage.put(uuidKey, resume);
    }

    @Override
    protected Resume doGet(String uuidKey) {
        return storage.get(uuidKey);
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    protected boolean isExist(String uuidKey) {
        return storage.containsKey(uuidKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
