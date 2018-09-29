package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ArrayStorageTest.class,
    SortedArrayStorageTest.class,
    ListStorageTest.class,
    MapSearchKeyUuidStorageTest.class,
    MapSearchKeyResumeStorageTest.class,
    ObjectFileStorageTest.class,
    ObjectPathStorageTest.class
})
public class RunAllTests {
}
