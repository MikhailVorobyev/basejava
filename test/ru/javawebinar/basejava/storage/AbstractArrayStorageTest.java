package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final Resume UUID_1 = new Resume("uuid1");
    private static final Resume UUID_2 = new Resume("uuid2");
    private static final Resume UUID_3 = new Resume("uuid3");

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(UUID_1);
        storage.save(UUID_2);
        storage.save(UUID_3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        Resume expected = new Resume("uuid4");
        storage.save(expected);
        Assert.assertEquals(expected, storage.get("uuid4"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1.getUuid());
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume expected = UUID_1;
        storage.update(expected);
        Assert.assertSame(expected, storage.get(UUID_1.getUuid()));
    }

    @Test
    public void get() throws Exception {
        Resume expected = UUID_1;
        Assert.assertEquals(expected, storage.get(UUID_1.getUuid()));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] actual = storage.getAll();
        Resume[] expected = new Resume[3];
        expected[0] = UUID_1;
        expected[1] = UUID_2;
        expected[2] = UUID_3;
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(UUID_1);
    }

    @Test(expected = StorageException.class)
    public void saveInFullStorage() throws Exception {
        storage.clear();
        try {
            fillingStorage();
        } catch (StorageException e) {
            Assert.fail("The test is failed");
        }
        storage.save(new Resume());
    }

    private void fillingStorage() throws StorageException {
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume());
        }
    }
}