package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlStorage implements Storage {
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.processingSQL("DELETE FROM resume",
                PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.processingSQL("UPDATE resume set full_name = ? WHERE uuid = ?",
                ps -> {
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    if (ps.executeUpdate() != 1) {
                        throw new NotExistStorageException(resume.getUuid());
                    }
                    return null;
                });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.processingSQL("INSERT INTO resume (uuid, full_name) VALUES (?, ?)",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                    return null;
                });
    }

    @Override
    public Resume get(String uuid) {
        String fullName = sqlHelper.processingSQL("SELECT * FROM resume r WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    return rs.getString("full_name");
                });
        return new Resume(uuid, fullName);
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.processingSQL("DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ps.execute();
                    if (ps.executeUpdate() != 1) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();
        return sqlHelper.processingSQL("SELECT * FROM resume",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        result.add(new Resume(rs.getString("uuid").trim(),
                                rs.getString("full_name")));
                    }
                    Collections.sort(result);
                    return result;
                });
    }


    @Override
    public int size() {
        return sqlHelper.processingSQL("SELECT COUNT(*) AS COUNT FROM resume",
                ps -> {
                    int result = 0;
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("COUNT");
                    }
                    return result;
                });
    }
}
