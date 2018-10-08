package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

@FunctionalInterface
public interface CustomConsumer<T> {
    void accept(T a) throws IOException;
}
