<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
    private String getListItems(ListSection section, SectionType type) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table cellpadding=\"0\">")
                .append("<tr>")
                .append("<td width=\"178\" valign=\"top\" rowspan=\"").append(section.getItems().size() + 1).append("\">")
                .append(type.getTitle()).append("</td>")
                .append("<td>").append("</td>")
                .append("</tr>");
        for (String item : section.getItems()) {
            sb.append("<tr>")
                    .append("<td>")
                    .append("<textarea rows=\"6\" cols=\"100\" name=")
                    .append(type.name())
                    .append(">")
                    .append(item)
                    .append("</textarea>")
                    .append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType"/>
            <c:choose>
                <c:when test="${SectionType.OBJECTIVE.equals(type)
                                || SectionType.PERSONAL.equals(type)}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><textarea rows="4" cols="80" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${SectionType.ACHIEVEMENT.equals(type)
                                || SectionType.QUALIFICATIONS.equals(type)}">
                    <%=getListItems((ListSection) resume.getSection(type), type)%>
                    <br/>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
