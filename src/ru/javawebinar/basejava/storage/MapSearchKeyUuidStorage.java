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
    protected void saveElement(Resume resume, String uuidKey) {
        storage.put(uuidKey, resume);
    }

    @Override
    protected void deleteElement(String uuidKey) {
        storage.remove(uuidKey);
    }

    @Override
    protected void updateElement(Resume resume, String uuidKey) {
        storage.put(uuidKey, resume);
    }

    @Override
    protected Resume getElement(String uuidKey) {
        return storage.get(uuidKey);
    }

    @Override
    public List<Resume> getCopyResumeList() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    protected boolean containsElement(String uuidKey) {
        return storage.containsKey(uuidKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
