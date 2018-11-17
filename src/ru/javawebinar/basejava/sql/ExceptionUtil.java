package ru.javawebinar.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if ("23505".equals(e.getSQLState())) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
