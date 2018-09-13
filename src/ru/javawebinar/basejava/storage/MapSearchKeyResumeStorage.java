package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSearchKeyResumeStorage extends AbstractStorage<Resume> {
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
    public void doSave(Resume resume, Resume resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void doDelete(Resume resumeKey) {
        storage.remove(resumeKey.getUuid());
    }

    @Override
    public void doUpdate(Resume resume, Resume resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume doGet(Resume resumeKey) {
        return resumeKey;
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected boolean isExist(Resume resumeKey) {
        return resumeKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
