package storage;

import model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = findElementIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exists!");
        } else {
            int indexToPut = index * (-1) - 1;
            System.arraycopy(storage, indexToPut, storage, indexToPut + 1, size - index);
            storage[indexToPut] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " does not exist!");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int findElementIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
