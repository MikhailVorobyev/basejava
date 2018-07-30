package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = findElementIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " does not exist!");
        } else {
            storage[index] = r;
            System.out.println("Update was successful");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist!");
            return null;
        } else {
            return storage[index];
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int findElementIndex(String uuid);
}
