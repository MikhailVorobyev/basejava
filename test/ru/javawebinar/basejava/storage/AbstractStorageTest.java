package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;


public class AbstractStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULL_NAME_1 = "fullName2";
    private static final String FULL_NAME_2 = "fullName2";
    private static final String FULL_NAME_3 = "fullName3";
    private static final String FULL_NAME_4 = "fullName4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
        RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
        RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
        RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_2);
        storage.save(RESUME_1);
    }

    @Test
    public void size() {
        assertSize(3);
    }
    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_1);
    }

    @Category(ArrayCategory.class)
    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            fillingStorage();
        } catch (StorageException e) {
            Assert.fail("The test is failed");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(UUID_4));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualList = storage.getAllSorted();
        List<Resume> expectedList = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(expectedList, actualList);
        assertEquals(3, actualList.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void fillingStorage() throws StorageException {
        for (int i = 0; i < 10000; i++) {
            storage.save(new Resume());

        }
    }
}