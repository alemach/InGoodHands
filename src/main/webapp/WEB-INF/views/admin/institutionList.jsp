<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 20.02.2020
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Instytucje</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/views/user/header-logged.jsp" %>
<a href="<c:url value="/admin/users"/>">Lista użytkowników</a>
<a href="<c:url value="/admin/institutions"/>">Lista instytucji</a>
<a href="<c:url value="/admin/donations"/>">Lista darowizn</a>
<br/>
<button class="add-institution">Dodaj nową instytucję</button><br/>
<div id="institution-form" hidden>
    <form:form modelAttribute="institution" action="/admin/create-update-institution" method="post">
        <form:hidden path="id"/>
        <form:label path="name">Nazwa</form:label><br>
        <form:input path="name"/><br>
        <form:label path="description">Opis</form:label><br>
        <form:input path="description"/><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Wyślij">
    </form:form>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Opis</th>
        <th>Email</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${institutions}" var="institution">
        <tr>
            <td><c:out value="${institution.id}"/></td>
            <td><c:out value="${institution.name}"/></td>
            <td><c:out value="${institution.description}"/></td>
            <td>
                <a class="edit-institution" href="#institution-form"><button>Edytuj</button></a>
            </td>
            <td>
                <form action="/admin/delete-institution" method="post">
                    <input type="hidden" name="id" value="<c:out value="${institution.id}"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Usuń">
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
<%@include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/institutionApp.js"/>"></script>

</body>
</html>