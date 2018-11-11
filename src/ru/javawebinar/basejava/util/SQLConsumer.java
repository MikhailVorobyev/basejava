package ru.javawebinar.basejava.util;

import java.sql.SQLException;

public interface SQLConsumer<T> {
    void accept(T t) throws SQLException;
}
