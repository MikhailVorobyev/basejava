package storage;

import model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private int size;
    private Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (findElementIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exists!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("The array is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = findElementIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " does not exist!");
        } else {
            storage[index] = resume;
            System.out.println("Update successful");
        }
    }

    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist!");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist!");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int findElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
