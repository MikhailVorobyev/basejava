package ru.javawebinar.basejava.util;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exeption.ExistStorageException;
import ru.javawebinar.basejava.exeption.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    public static <T> void processingSQL(ConnectionFactory connectionFactory, String sql,
                                         SQLConsumer<PreparedStatement> consumer) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            consumer.accept(ps);
        } catch (PSQLException e) {
            throw new ExistStorageException(e);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}



