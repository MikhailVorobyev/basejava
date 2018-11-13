package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLConsumer<R> {
     R accept(PreparedStatement t) throws SQLException;
}
