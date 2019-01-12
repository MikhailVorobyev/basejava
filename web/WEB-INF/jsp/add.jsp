<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Новое резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <dl>
            <dt>Имя:</dt>
            <dd>
                <input type="text" name="fullName" size=50 value="${resume.fullName}">
            </dd>
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
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><textarea rows="4" cols="80" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT'}">
                    <dl>
                        <dt>${type.title}</dt>
                        <dd><textarea rows="8" cols="100" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
            </c:choose>
            <br/>
            <table border="1" cellpadding="8" cellspacing="0">
                <tr>
                    <c:if test="${type == 'EXPERIENCE'}">
                        <th width="214" align="left">Добавить место работы:&nbsp;</th>
                        <th><a href="resume?uuid=${resume.uuid}&action=addOrganization&type=${type}"><img
                                src="img/add.png"></a>
                        </th>
                    </c:if>
                    <c:if test="${type == 'EDUCATION'}">
                        <th width="214" align="left">Добавить учебное заведение:&nbsp;</th>
                        <th><a href="resume?uuid=${resume.uuid}&action=addOrganization&type=${type}"><img
                                src="img/add.png"></a>
                        </th>
                    </c:if>
                </tr>
            </table>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
