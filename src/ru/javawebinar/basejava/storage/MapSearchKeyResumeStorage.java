package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapSearchKeyResumeStorage extends AbstractMapStorage {

    @Override
    public void saveElement(Resume resume, Object resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void deleteElement(Object resumeKey) {
        storage.remove(((Resume) resumeKey).getUuid());
    }

    @Override
    public void updateElement(Resume resume, Object resumeKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public Resume getElement(Object resumeKey) {
        return (Resume) resumeKey;
    }

    @Override
    protected boolean containsElement(Object resumeKey) {
        return resumeKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}
