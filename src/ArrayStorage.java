import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("The array is full");
            return;
        }
        if (findResume(resume.uuid) >= 0) {
            System.out.println("Resume already exists!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index;
        if ((index = findResume(resume.uuid)) < 0) {
            System.out.println("Resume does not exist!");
        } else {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        int index;
        if ((index = findResume(uuid)) < 0) {
            System.out.println("Resume does not exist!");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index;
        if ((index = findResume(uuid)) < 0) {
            System.out.println("Resume does not exist!");
        } else {
            //System.arraycopy(storage,  index + 1, storage, index, size - 1 - index); does not work
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
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
