package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeServlet extends HttpServlet {
    private final Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Storage storage = Config.get().getStorage();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("name");
        response.getWriter().write(writeResumes(uuid));

    }

    private String writeResumes(String paramUuid) {
        List<Resume> resumesList = new ArrayList<>();
        if (paramUuid == null) {
            resumesList = storage.getAllSorted();
        } else {
            resumesList.add(storage.get(paramUuid));
        }
        String emptyString = "<tr height=\"30\"</tr>";

        StringBuilder sb = new StringBuilder("" +
    "<html>" +
	"<head>" +
		"<title>Resumes</title>" +
	"</head>" +
	"<body>" +
		"<center>" +
            "<h1>Table of resumes</h1>" +
			"<table width=\"1000\" cellspacing=\"0\" border=\"1\">" +
				"<tr height=\"30\">" +
					"<td rowspan=\"2\" width=\"300\"><center>Uuid</center></td>" +
					"<td rowspan=\"2\" width=\"200\"><center>Full name</center></td>" +
					"<td colspan=\"2\"><center>Contacts</center></td>" +
                "</tr>" +
				"<tr height=\"30\">" +
					"<td><center>type</center></td>" +
					"<td><center>value</center></td>" +
                "</tr>");
        for (Resume r : resumesList) {
            String uuid = r.getUuid();
            String fullName = r.getFullName();
            Map<ContactType, String> contacts = r.getContacts();
            for (Map.Entry<ContactType, String> entry :contacts.entrySet()) {
                sb.append("<tr height=\"30\">" +
                                "<td>" + uuid + "</td>" +
                                "<td><center>" + fullName + "</center></td>" +
                                "<td>" + entry.getKey() + "</td>" +
                                "<td>" + entry.getValue() + "</td>" +
                        "</tr>");
            }
            sb.append(emptyString);
        }
        sb.delete((sb.length() - emptyString.length()), sb.length());
        sb.append("</table></center></body></html>");
        return sb.toString();
    }
}
