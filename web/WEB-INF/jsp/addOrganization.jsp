<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <jsp:useBean id="type" type="ru.javawebinar.basejava.model.SectionType" scope="request"/>
    <title>Новая организация</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <h2>${type.title}:</h2>
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <input type="hidden" name="fullName" value="${resume.fullName}">
        <input type="hidden" name="type" value="${type}">
        <dl>
            <dt>Название:</dt>
            <dd><input type="text" name="name" size=50></dd>
        </dl>
        <dl>
            <dt>URL:</dt>
            <dd><input type="text" name="url" size=50></dd>
        </dl>
        <dl>
            <dt>Дата начала:</dt>
            <dd><input type="date" name="startDate"></dd>
        </dl>
        <dl>
            <dt>Дата окончания:</dt>
            <dd><input type="date" name="endDate"></dd>
        </dl>
        <dl>
            <dt>Позиция:</dt>
            <dd><textarea rows="3" cols="80" name="title"></textarea></dd>
        </dl>
        <dl>
            <dt>Описание:</dt>
            <dd><textarea rows="8" cols="100" name="description"></textarea></dd>
        </dl>
        <hr>
        <button type="submit" onclick="window.history.back()">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
