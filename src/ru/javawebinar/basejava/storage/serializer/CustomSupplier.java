package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

@FunctionalInterface
public interface CustomSupplier<T> {
    T get() throws IOException;
}
