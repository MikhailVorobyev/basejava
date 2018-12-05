package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("name");
        response.getWriter().write(writeResumes(uuid));

    }

    private String writeResumes(String paramUuid) {
        List<Resume> resumesList = new ArrayList<>(1);
        if (paramUuid == null) {
            resumesList = storage.getAllSorted();
        } else {
            resumesList.add(storage.get(paramUuid));
        }
        StringBuilder sb = new StringBuilder("" +
    "<html>" +
	"<head>" +
		"<title>Resumes</title>" +
	"</head>" +
	"<body>" +
		"<center>" +
            "<h1>Table of resumes</h1>" +
			"<table width=\"500\" cellspacing=\"0\" border=\"1\">" +
				"<tr height=\"40\">" +
					"<td ><center>Uuid</center></td>" +
					"<td width=\"150\"><center>Full name</center></td>" +
                "</tr>");
        for (Resume r : resumesList) {
            String uuid = r.getUuid();
            String fullName = r.getFullName();
            sb.append("<tr height=\"30\">" +
                        "<td>" + uuid + "</td>" +
                        "<td>" + fullName + "</td>" +
                    "</tr>");
        }
        sb.append("</table></center></body></html>");
        return sb.toString();
    }
}
