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
				<title>Profil Użytkownika</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="header-logged.jsp" %>
<section class="login-page">
				<h2>Edytuj swoje dane</h2>
				<form:form method="post" modelAttribute="userDetailsDTO">
								<div class="col">
												<div class="form-group">
																<h4 class="form-error"><form:errors path="firstName"/></h4>
																<form:input type="text" path="firstName" placeholder="Imię"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors path="lastName"/></h4>
																<form:input type="text" path="lastName" placeholder="Nazwisko"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors path="phone"/></h4>
																<form:input type="tel" path="phone" placeholder="Numer telefonu"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors path="street"/></h4>
																<form:input type="text" path="street" placeholder="Ulica, numer domu/mieszkania"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors path="city"/></h4>
																<form:input type="text" path="city" placeholder="Miasto"/>
												</div>
												<div class="form-group">
																<h4 class="form-error"><form:errors path="zipCode"/></h4>
																<form:input type="text" path="zipCode" placeholder="Kod pocztowy"/>
												</div>
								</div>
								<div class="col">
																<%--            <div class="form-group">--%>
																<%--                <h4 class="form-error"><form:errors path="email"/></h4>--%>
																<%--                <form:input type="email" path="email" placeholder="Email"/>--%>
																<%--            </div>--%>
																<%--            <div class="form-group">--%>
																<%--                <h4 class="form-error"><form:errors/></h4>--%>
																<%--                <h4 class="form-error"><form:errors path="password"/></h4>--%>
																<%--                <form:input type="password" path="password" value = "" placeholder="Hasło"/>--%>
																<%--            </div>--%>
																<%--            <div class="form-group">--%>
																<%--                <form:input type="password" path="repassword" placeholder="Powtórz hasło"/>--%>
																<%--            </div>--%>
												<a href="<c:url value="/user/edit-credentials"/>" class="btn btn--without-border">Zmień email i hasło</a>
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
