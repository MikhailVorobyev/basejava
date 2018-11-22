package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.processingSql("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(
                conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("" +
                            "DELETE FROM resume " +
                            " WHERE uuid = ?")) {
                        ps.setString(1, resume.getUuid());
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                    }
                    doSave(conn, resume);
                    return null;
                });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            doSave(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.processingSql("" +
                        " SELECT * FROM resume r " +
                        "   LEFT JOIN contact c " +
                        "     ON r.uuid = c.resume_uuid " +
                        "  WHERE r.uuid =?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        ContactType type = null;
                        String stringType = rs.getString("type");
                        if (stringType != null) {
                            type = ContactType.valueOf(stringType);
                        }
                        String value = rs.getString("value");
                        if (value != null) {
                            resume.addContact(type, value);
                        }
                    } while (rs.next());
                    return resume;
                });

    }

    @Override
    public void delete(String uuid) {
        sqlHelper.processingSql("DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ps.execute();
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        return sqlHelper.processingSql("" +
                        " SELECT * FROM resume r" +
                        "   LEFT JOIN contact c " +
                        "     ON r.uuid = c.resume_uuid " +
                        "  ORDER BY r.full_name, r.uuid ",
                ps -> {
                    ResultSet rs = ps.executeQuery();

                    Resume resume = null;
                    String uuid = null;
                    while (rs.next()) {
                        String newUuid = rs.getString("uuid");
                        if (!newUuid.equals(uuid)) {
                            if (resume != null) {
                                resumes.add(resume);
                            }
                            uuid = newUuid;
                            resume = new Resume(uuid, rs.getString("full_name"));
                        }
                        resume.addContact(ContactType.valueOf(rs.getString("type")),
                                rs.getString("value"));
                    }
                    resumes.add(resume);
                    return resumes;
                });
    }

    @Override
    public int size() {
        return sqlHelper.processingSql("SELECT COUNT(*) FROM resume",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt(1) : 0;
                });
    }

    private void doSave(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("" +
                "INSERT INTO resume (uuid, full_name) " +
                "VALUES (?, ?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        }
        try (PreparedStatement ps = conn.prepareStatement("" +
                "INSERT INTO contact (resume_uuid, type, value) " +
                "VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
