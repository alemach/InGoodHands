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
				<title>Zmień hasło</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="header.jsp" %>

<section class="login-page">
				<h2>Zaloguj się</h2>
				<form:form modelAttribute="passwordDTO" method="post">
								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
												<h4 class="form-error"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.localizedMessage}"/></h4>
								</c:if>
								<c:if test="${not empty success}">
												<h4><c:out value="${success}"/></h4>
								</c:if>
								<div class="form-group">
												<h4 class="form-error"><form:errors/></h4>
												<h4 class="form-error"><form:errors path="password"/></h4>
												<form:input type="password" path="password" placeholder="Hasło"/>
								</div>
								<div class="form-group">
												<h4 class="form-error"><form:errors path="repassword"/></h4>
												<form:input type="password" path="repassword" placeholder="Powtórz hasło"/>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="form-group form-group--buttons">
												<button class="btn" type="submit">Zmień hasło</button>
								</div>
				</form:form>
</section>

<%@include file="footer.jsp" %>
</body>
</html>