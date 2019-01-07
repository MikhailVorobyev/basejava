package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        String sectionType = request.getParameter("type");

        if (uuid == null) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }

        if (sectionType != null) {
            SectionType type = SectionType.valueOf(sectionType);
            Section section = addOrganization(request, resume, type);
            if (section != null) {
                resume.addSection(type, section);
            }
        } else {
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
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> organizations = new ArrayList<>();
                        String[] name = request.getParameterValues(type + "name");
                        String[] url = request.getParameterValues(type + "url");
                        String[] orgHash = request.getParameterValues("" + type + "orgHash");
                        if (name != null) {
                            for (int i = 0; i < name.length; i++) {
                                List<Organization.Position> positions = new ArrayList<>();
                                String[] startDate = request.getParameterValues("" + type + orgHash[i] + "startDate");
                                String[] endDate = request.getParameterValues("" + type + orgHash[i] + "endDate");
                                String[] title = request.getParameterValues("" + type + orgHash[i] + "title");
                                String[] description = request.getParameterValues("" + type + orgHash[i] + "description");

                                if (startDate != null) {
                                    for (int j = 0; j < startDate.length; j++) {
                                        if (startDate[j].length() != 0) {
                                            positions.add(new Organization.Position(DateUtil.parse(startDate[j]),
                                                    DateUtil.parse(endDate[j]), title[j], description[j]));
                                        }
                                    }
                                }
                                if (positions.size() != 0) {
                                    organizations.add(new Organization(new Link(name[i], url[i]), positions));
                                }
                            }
                        }

                        if (organizations.size() != 0) {
                            resume.addSection(type, new OrganizationSection(organizations));
                        } else {
                            resume.getSections().remove(type);
                        }
                }
            }
        }

        if (uuid == null) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        if (sectionType != null) {
            response.sendRedirect("resume?uuid=" + resume.getUuid() + "&action=edit");
        } else {
            response.sendRedirect("resume");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        String type = request.getParameter("type");
        String organizationName = request.getParameter("organizationName");
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
            case "addOrganization":
                resume = storage.get(uuid);
                request.setAttribute("type", SectionType.valueOf(type));
                request.setAttribute("resume", resume);
                request.getRequestDispatcher("/WEB-INF/jsp/addOrganization.jsp").forward(request, response);
                break;
            case "addPosition":
                resume = storage.get(uuid);
                request.setAttribute("type", SectionType.valueOf(type));
                request.setAttribute("resume", resume);
                request.setAttribute("organization",
                        findOrganization(resume.getSection(SectionType.valueOf(type)), organizationName));
                request.getRequestDispatcher("WEB-INF/jsp/addPosition.jsp").forward(request, response);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }

    private Section addOrganization(HttpServletRequest request, Resume resume, SectionType type) {
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String organizationName = request.getParameter("organizationName");

        OrganizationSection section = (OrganizationSection) resume.getSection(type);
        Organization org = null;
        Organization.Position position = addPosition(request);
        if (position != null) {
            if (organizationName != null) {
                org = findOrganization(section, organizationName);
                if (org != null) {
                    org.getPositions().add(position);
                    return section;
                }
            } else {
                org = new Organization(name, url, position);
            }
        } else {
            return null;
        }

        if (section != null) {
            section.getOrganizations().add(org);
        } else {
            return new OrganizationSection(org);
        }
        return section;
    }

    private Organization.Position addPosition(HttpServletRequest request) {
        String startDate = request.getParameter("startDate");
        if (startDate.length() == 0) {
            return null;
        }
        LocalDate localStartDate = DateUtil.parse(request.getParameter("startDate"));
        LocalDate localEndDate = DateUtil.parse(request.getParameter("endDate"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        return new Organization.Position(localStartDate, localEndDate, title, description);
    }

    private Organization findOrganization(Section section, String organizationName) {
        for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
            if (organization.getHomePage().getName().equals(organizationName)) {
                return organization;
            }
        }
        return null;
    }
}
