<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
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
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                    <dl>
                        <dt><b>${type.title}</b></dt>
                        <dd><textarea rows="4" cols="80" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT'}">
                    <dl>
                        <dt><b>${type.title}</b></dt>
                        <dd><textarea rows="8" cols="100" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </dl>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <h4>${type.title}:</h4>
                    <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType"/>
                    <c:if test="${not empty resume.getSection(type)}">
                        <c:forEach var="org"
                                   items="<%=((OrganizationSection) resume.getSection(type)).getOrganizations()%>">
                            <input type="hidden" name="${type}orgUuid" value="${org.uuid}">
                            <dl>
                                <dt>Название:</dt>
                                <dd><input type="text" name="${org.uuid}name" size=80 value="${org.homePage.name}">
                                </dd>
                            </dl>
                            <dl>
                                <dt>URL:</dt>
                                <dd><input type="text" name="${org.uuid}url" size=80 value="${org.homePage.url}"></dd>
                            </dl>
                            <c:forEach var="position" items="${org.positions}">
                                <dt>Дата начала:</dt>
                                <dd><input type="date"
                                           name="${org.uuid}startDate"
                                           value="${position.startDate}"/></dd>
                                </dl>
                                <dl>
                                    <dt>Дата окончания:</dt>
                                    <dd><input type="date"
                                               name="${org.uuid}endDate"
                                               value="${position.endDate}"/></dd>
                                </dl>
                                <dl>
                                    <dt>Позиция:</dt>
                                    <dd><textarea rows="3" cols="80"
                                                  name="${org.uuid}title">${position.title}</textarea>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>Описание:</dt>
                                    <dd><textarea rows="8" cols="100"
                                                  name="${org.uuid}description">${position.description}</textarea>
                                    </dd>
                                </dl>
                                <br/>
                            </c:forEach>
                            <table border="1" cellpadding="8" cellspacing="0">
                                <tr>
                                    <th>Добавить позицию:&nbsp;</th>
                                    <th>
<a href="resume?uuid=${resume.uuid}&action=addPosition&type=${type}&orgUuid=${org.uuid}"><img
                                             src="img/add.png"></a></th>
                                </tr>
                            </table>
                            <br/>
                        </c:forEach>
                    </c:if>
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
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
