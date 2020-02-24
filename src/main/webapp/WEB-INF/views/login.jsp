<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 17.02.2020
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
				<meta charset="UTF-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<meta http-equiv="X-UA-Compatible" content="ie=edge"/>
				<title>Logowanie</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="header.jsp" %>

<section class="login-page">
				<h2>Zaloguj się</h2>
				<form:form method="post">
								<c:if test="${not empty param.error}">
												<h4 class="form-error">Niewłaściwe dane logowania</h4>
								</c:if>
								<div class="form-group">
												<input type="email" name="username" placeholder="Email"/>
								</div>
								<div class="form-group">
												<input type="password" name="password" placeholder="Hasło"/>
												<a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="form-group form-group--buttons">
												<a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
												<button class="btn" type="submit">Zaloguj się</button>
								</div>
				</form:form>
</section>

<%@include file="footer.jsp" %>
</body>
</html>