package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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

    protected int findElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
