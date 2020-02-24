<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 17.02.2020
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
				<meta charset="UTF-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<meta http-equiv="X-UA-Compatible" content="ie=edge"/>
				<title>Rejestracja</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="header-logged.jsp" %>
<section class="login-page">
				<h2>Załóż konto</h2>
				<form:form method="post" modelAttribute="user">
								<div class="col">
												<div class="form-group">
																<h4 class="form-error"><form:errors path="email"/></h4>
																<form:input type="email" path="email" placeholder="Email"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors/></h4>
																<input type="password" name="oldPassword" placeholder="Hasło"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors/></h4>
																<h4 class="form-error"><form:errors path="password"/></h4>
																<form:input type="password" path="password" placeholder="Nowe hasło"/>
												</div>
												<div class="form-group">
																<form:input type="password" path="repassword" placeholder="Powtórz nowe hasło"/>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												<div class="form-group form-group--buttons">
																<button class="btn" type="submit">Zmień dane</button>
												</div>
								</div>
				</form:form>
</section>
<%@include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
