package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveElement(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    protected void deleteElement(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        storage.set(index, resume);
    }

    @Override
    protected Resume getElement(Integer index) {
        return storage.get(index);
    }

    @Override
    public List<Resume> getCopyResumeList() {
        return new ArrayList<>(storage);
    }

    @Override
    protected boolean containsElement(Integer elementKey) {
        return elementKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
