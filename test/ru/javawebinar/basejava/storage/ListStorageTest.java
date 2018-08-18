package ru.javawebinar.basejava.storage;

import org.junit.experimental.categories.Categories;

@Categories.ExcludeCategory(ArrayCategory.class)
public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest(){
        super(new ListStorage());
    }

}