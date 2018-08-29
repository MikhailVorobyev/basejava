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
    public void saveElement(Resume resume, Resume resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void deleteElement(Resume resumeKey) {
        storage.remove(resumeKey.getUuid());
    }

    @Override
    public void updateElement(Resume resume, Resume resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume getElement(Resume resumeKey) {
        return resumeKey;
    }

    @Override
    public List<Resume> getCopyResumeList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected boolean containsElement(Resume resumeKey) {
        return resumeKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
