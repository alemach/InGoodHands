<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 11.02.2020
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
				<title>Footer</title>
</head>
<body>
<footer>
				<div class="contact" id="contact">
								<c:if test="${not empty message}">
												<h4 class="form-error"><c:out value="${message}"/></h4>
								</c:if>
								<h2>Skontaktuj się z nami</h2>
								<h3>Formularz kontaktowy</h3>
								<form:form modelAttribute="messageDTO" action="/send-message" method="post" class="form--contact">
												<h4 class="form-error"><form:errors/></h4>
												<div class="form-group form-group--50">
																<h4 class="form-error"><form:errors path="name"/></h4>
																<form:input path="name" type="text" placeholder="Imię"/>
												</div>
												<div class="form-group form-group--50">
																<h4 class="form-error"><form:errors path="surname"/></h4>
																<form:input path="surname" type="text" placeholder="Nazwisko"/>
												</div>

												<div class="form-group">
																<h4 class="form-error"><form:errors path="message"/></h4>
																<form:textarea path="message" placeholder="Wiadomość" rows="1"></form:textarea>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
												<button class="btn" type="submit">Wyślij</button>
								</form:form>
				</div>
				<div class="bottom-line">
								<span class="bottom-line--copy">Copyright &copy; 2020</span>
								<div class="bottom-line--icons">
												<a href="#" class="btn btn--small"><img src="<c:url value="/resources/images/icon-facebook.svg"/>"/></a> <a href="#" class="btn btn--small"><img
																src="<c:url value="/resources/images/icon-instagram.svg"/>"/></a>
								</div>
				</div>
</footer>

</body>
</html>
