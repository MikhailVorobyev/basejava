package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor<R> {
     R execute(PreparedStatement ps) throws SQLException;
}
