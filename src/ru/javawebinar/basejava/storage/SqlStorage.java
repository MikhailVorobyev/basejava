package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper.processingSQL(connectionFactory, "DELETE FROM resume",
                PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        SqlHelper.processingSQL(connectionFactory, "UPDATE resume set full_name = ? WHERE uuid = ?",
                ps -> {
                    get(resume.getUuid());
                    ps.setString(1, resume.getFullName());
                    ps.setString(2, resume.getUuid());
                    ps.execute();
                });
    }

    @Override
    public void save(Resume resume) {
        SqlHelper.processingSQL(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?, ?)",
                ps -> {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, resume.getFullName());
                    ps.execute();
                });
    }

    @Override
    public Resume get(String uuid) {
        List<String> fullName = new ArrayList<>(1);
        SqlHelper.processingSQL(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    fullName.add(rs.getString("full_name"));

                });
        return new Resume(uuid, fullName.get(0));
    }

    @Override
    public void delete(String uuid) {
        SqlHelper.processingSQL(connectionFactory, "DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                    get(uuid);
                    ps.setString(1, uuid);
                    ps.execute();
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<>();
        SqlHelper.processingSQL(connectionFactory, "SELECT * FROM resume",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        result.add(new Resume(rs.getString("uuid").trim(),
                                rs.getString("full_name")));
                    }
                    Collections.sort(result);
                });
        return result;
    }

    @Override
    public int size() {
        List<Integer> size = new ArrayList<>(1);
        ResultSet rs = null;
        SqlHelper.processingSQL(connectionFactory, "SELECT COUNT(*) AS COUNT FROM resume",
                ps -> {
                    ResultSet rs1 = ps.executeQuery();
                    if (rs1.next()) {
                        size.add(rs1.getInt("COUNT"));
                    }
                });
        return size.get(0);
    }
}
