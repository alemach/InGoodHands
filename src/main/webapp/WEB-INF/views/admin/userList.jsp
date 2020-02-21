<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 20.02.2020
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Użytkownicy</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<%@include file="/WEB-INF/views/user/header-logged.jsp" %>
<a href="<c:url value="/admin/users"/>">Lista użytkowników</a>
<a href="<c:url value="/admin/institutions"/>">Lista instytucji</a>
<a href="<c:url value="/admin/donations"/>">Lista darowizn</a>

<c:if test="${not empty error}">
    <c:out value="${error}"/>
</c:if>
<table>
    <tr>
        <th>#</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Email</th>
        <th>Numer telefonu</th>
        <th>Adres</th>
        <th>Zablokowany</th>
        <th>Rola</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.phone}"/></td>
            <td>
                <c:out value="${user.street}"/>, <c:out value="${user.city}"/>, <c:out value="${user.zipCode}"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.enabled == false}">Tak</c:when>
                    <c:when test="${user.enabled == true}">Nie</c:when>
                </c:choose>
            </td>
            <td>
                <c:forEach items="${user.roles}" var="role" varStatus="index">
                    <c:choose>
                        <c:when test="${role.name == 'ROLE_USER'}">Użytkownik</c:when>
                        <c:when test="${role.name == 'ROLE_ADMIN'}">Administrator</c:when>
                    </c:choose>
                    <c:if test="${!index.last}">
                        ,&nbsp
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.enabled == false}">
                        <form action="<c:url value="/admin/toggle-users-enable"/>" method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="hidden" name="enabled" value="true">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" value="Odblokuj">
                        </form>
                    </c:when>
                    <c:when test="${user.enabled == true}">
                        <form action="<c:url value="/admin/toggle-users-enable"/>" method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="hidden" name="enabled" value="false">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" value="Zablokuj">
                        </form>
                    </c:when>
                </c:choose>
            </td>
            <td>
            <td>
                <form action="/admin/delete-user" method="post">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" value="Usuń">
                </form>
            </td>
            <td>
                <form action="/admin/toggle-role" method="post">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <c:if test="${user.roles.contains(adminRole)}">
                        <input type="submit" value="Odbierz przywileje">
                    </c:if>
                    <c:if test="${!user.roles.contains(adminRole)}">
                        <input type="submit" value="Nadaj przywileje">
                    </c:if>
                </form>
            </td>

        </tr>
    </c:forEach>

</table>

<%@include file="/WEB-INF/views/footer.jsp" %>

<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/formApp.js"/>"></script>
</body>
</html>