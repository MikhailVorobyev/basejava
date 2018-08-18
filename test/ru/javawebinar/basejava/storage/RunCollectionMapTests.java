package ru.javawebinar.basejava.storage;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.ExcludeCategory(ArrayCategory.class)
@Suite.SuiteClasses({ListStorageTest.class, MapUuidStorageTest.class})
public class RunCollectionMapTests {
}
