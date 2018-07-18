import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        Resume resume;
        for (int i = 0; i < size(); i++) {
            resume = storage[i];
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size(); i++) {
            resume = storage[i];
            if (resume.uuid.equals(uuid)) {
                Resume[] newArrayBeforeNull = Arrays.copyOfRange(storage, 0, i);
                Resume[] newArrayAfterNull = Arrays.copyOfRange(storage, i + 1, size());
                Resume[] newArray = new Resume[storage.length - 1];
                System.arraycopy(newArrayBeforeNull, 0, newArray, 0, newArrayBeforeNull.length);
                System.arraycopy(newArrayAfterNull, 0, newArray, newArrayBeforeNull.length, newArrayAfterNull.length);
                storage = newArray;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                return count;
            }
            count++;
        }
        return count;
    }
}
