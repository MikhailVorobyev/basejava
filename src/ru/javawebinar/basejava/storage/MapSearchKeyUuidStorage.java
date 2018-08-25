package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapSearchKeyUuidStorage extends AbstractMapStorage {

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
    protected boolean containsElement(Object elementKey) {
        return storage.containsKey(((Resume) elementKey).getUuid());
    }

    @Override
    protected Resume findElementKey(String uuid) {
        return new Resume(uuid, null);
    }
}
