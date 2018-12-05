package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exeption.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

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
                            " UPDATE resume " +
                            "    SET full_name = ?" +
                            "  WHERE uuid = ?")) {

                        ps.setString(1, resume.getFullName());
                        ps.setString(2, resume.getUuid());
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(resume.getUuid());
                        }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("" +
                            " DELETE FROM contact " +
                            "  WHERE resume_uuid = ? ")) {
                        ps.setString(1, resume.getUuid());
                        ps.execute();
                    }
                    insertContacts(conn, resume);
                    try (PreparedStatement ps = conn.prepareStatement("" +
                            " DELETE FROM section " +
                            "  WHERE resume_uuid = ? ")) {
                        ps.setString(1, resume.getUuid());
                        ps.execute();
                    }
                    insertSections(conn, resume);
                    return null;
                });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "INSERT INTO resume (uuid, full_name) " +
                    "VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(conn, resume);
            insertSections(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume = null;
            ResultSet rs = null;
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " SELECT * FROM resume r " +
                    "   LEFT JOIN contact c " +
                    "     ON r.uuid = c.resume_uuid " +
                    "  WHERE r.uuid =?")) {
                 ps.setString(1, uuid);
                 rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    addContact(rs, resume);
                } while (rs.next());
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " SELECT * FROM section " +
                    "  WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.processingSql("DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumeMap = new LinkedHashMap<>();
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " SELECT * FROM resume r " +
                    "  ORDER BY r.full_name, r.uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumeMap.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "  SELECT * FROM contact ")) {
                doAdd(ps, resumeMap, this::addContact);
            }
            try (PreparedStatement ps = conn.prepareStatement("" +
                    " SELECT * FROM section ")) {
                doAdd(ps, resumeMap, this::addSection);
            }
            return new ArrayList<>(resumeMap.values());
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

    private void insertContacts(Connection conn, Resume resume) throws SQLException {
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

    private void insertSections(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("" +
                "INSERT INTO section (resume_uuid, type, value) " +
                "VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                SectionType sectionType = e.getKey();
                ps.setString(1, resume.getUuid());
                ps.setString(2, sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        StringSection ss = (StringSection) e.getValue();
                        ps.setString(3, ss.getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListStringSection lss = (ListStringSection) e.getValue();
                        ps.setString(3, String.join("\n", lss.getItems()));
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    @FunctionalInterface
    public interface ResumeExecutor {
        void execute(ResultSet rs, Resume resume) throws SQLException;
    }

    private void doAdd(PreparedStatement ps, Map<String, Resume> map, ResumeExecutor executor) throws SQLException {
        ResultSet rs = ps.executeQuery();
        Resume resume = null;
        String uuid = null;
        while (rs.next()) {
            String newUuid = rs.getString("resume_uuid");
            if (!newUuid.equals(uuid)) {
                resume = map.get(newUuid);
            }
            uuid = newUuid;
            executor.execute(rs, resume);
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String type = rs.getString("type");
        String value = rs.getString("value");
        if (type != null && value != null) {
            resume.addContact(ContactType.valueOf(type), value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        SectionType sectionType = SectionType.valueOf(rs.getString("type"));
        switch (sectionType) {
            case OBJECTIVE:
            case PERSONAL:
                resume.addSection(sectionType, new StringSection(rs.getString("value")));
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                String[] value = rs.getString("value").split("\n");
                resume.addSection(sectionType, new ListStringSection(value));
                break;
        }
    }
}
