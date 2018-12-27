package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Resume resume;
        String fullName = request.getParameter("fullName");
        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    String value = request.getParameter(type.name());
                    if (value != null && value.trim().length() != 0) {
                        resume.addSection(type, new TextSection(value));
                    } else {
                        resume.getSections().remove(type);
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    String param = request.getParameter(type.name());
                    if (param != null) {
                        String[] items = param.split("\n");
                        List<String> list = new ArrayList<>();
                        for (String item : items) {
                            if (item.trim().length() != 0) {
                                list.add(item);
                            }
                        }
                        if (list.size() != 0) {
                            resume.addSection(type, new ListSection(list));
                        } else {
                            resume.getSections().remove(type);
                        }
                    }
                    break;
            }
        }
        if (uuid == null) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                request.setAttribute("resume", resume);
                request.getRequestDispatcher(
                        ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
                ).forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }
}
