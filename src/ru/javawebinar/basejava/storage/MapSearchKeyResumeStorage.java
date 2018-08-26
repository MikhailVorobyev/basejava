package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapSearchKeyResumeStorage extends AbstractMapStorage {

    @Override
    public void saveElement(Resume resume, Object resumeKey) {
        storage.put(((Resume) resumeKey).getUuid(), resume);
    }

    @Override
    public void deleteElement(Object resumeKey) {
        storage.remove(((Resume) resumeKey).getUuid());
    }

    @Override
    public void updateElement(Resume resume, Object resumeKey) {
        storage.put(((Resume) resumeKey).getUuid(), resume);
    }

    @Override
    public Resume getElement(Object resumeKey) {
        return storage.get(((Resume) resumeKey).getUuid());
    }

    @Override
    protected boolean containsElement(Object resumeKey) {
        return storage.containsKey(((Resume) resumeKey).getUuid());
    }

    @Override
    protected Resume findElementKey(String uuid) {
        return new Resume(uuid, null);
    }
}
