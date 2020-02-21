<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Darowizny</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<%@include file="/WEB-INF/views/user/header-logged.jsp" %>
<a href="<c:url value="/admin/users"/>">Lista użytkowników</a>
<a href="<c:url value="/admin/institutions"/>">Lista instytucji</a>
<a href="<c:url value="/admin/donations"/>">Lista darowizn</a>
<%@include file="/WEB-INF/views/footer.jsp" %>

<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/formApp.js"/>"></script>
</body>
</html>