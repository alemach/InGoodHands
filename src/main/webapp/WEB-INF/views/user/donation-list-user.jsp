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
				<meta charset="UTF-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<meta http-equiv="X-UA-Compatible" content="ie=edge"/>
				<title>Moje darowizny</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<%@include file="/WEB-INF/views/user/header-logged.jsp" %>
<a href="<c:url value="/admin/users"/>">Lista użytkowników</a>
<a href="<c:url value="/admin/institutions"/>">Lista instytucji</a>
<a href="<c:url value="/admin/donations"/>">Lista darowizn</a><br/>

<h4 class="form-error"><form:errors/></h4>
<table>
				<tr>
								<th>#</th>
								<th>Kategorie</th>
								<th>Ilość worków</th>
								<th>Instytucje</th>
								<th>Adres odbioru</th>
								<th>Data odbioru</th>
								<th>Godzina odbioru</th>
								<th>Uwagi</th>
								<th>
												<c:choose>
																<c:when test="${direction == 'asc'}">
																				<a href="<c:url value="/user/donations?direction=desc&sortedBy=status.status"/>">Status</a>
																</c:when>
																<c:when test="${direction == 'desc'}">
																				<a href="<c:url value="/user/donations?direction=asc&sortedBy=status.status"/>">Status</a>
																</c:when>
												</c:choose>
								</th>
								<th>
												<c:choose>
																<c:when test="${direction == 'asc'}">
																				<a href="<c:url value="/user/donations?direction=desc&sortedBy=created"/>">Utworzone</a>
																</c:when>
																<c:when test="${direction == 'desc'}">
																				<a href="<c:url value="/user/donations?direction=asc&sortedBy=created"/>">Utworzone</a>
																</c:when>
												</c:choose>
								</th>
								<th>
												<c:choose>
																<c:when test="${direction == 'asc'}">
																				<a href="<c:url value="/user/donations?direction=desc&sortedBy=modified"/>">Zmodyfikowane</a>
																</c:when>
																<c:when test="${direction == 'desc'}">
																				<a href="<c:url value="/user/donations?direction=asc&sortedBy=modified"/>">Zmodyfikowane</a>
																</c:when>
												</c:choose>
								</th>
				</tr>
				<c:forEach items="${donations}" var="donation">
								<tr>
												<td><c:out value="${donation.id}"/></td>
												<td>
																<c:forEach items="${donation.categories}" var="category" varStatus="index">
																				<c:out value="${category.name}"/><c:if test="${!index.last}">, </c:if>
																</c:forEach>
												</td>
												<td><c:out value="${donation.quantity}"/></td>
												<td><c:out value="${donation.institution.name}"/></td>
												<td><c:out value="${donation.street}"/>, <c:out value="${donation.city}"/>, <c:out value="${donation.zipCode}"/></td>
												<td><c:out value="${donation.pickUpDate}"/></td>
												<td><c:out value="${donation.pickUpTime}"/></td>
												<td><c:out value="${donation.pickUpComment}"/></td>
												<td><c:out value="${donation.status.status}"/></td>
												<td><c:out value="${donation.created}"/></td>
												<td><c:out value="${donation.modified}"/></td>
								</tr>
				</c:forEach>
</table>

<%@include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/formApp.js"/>"></script>
</body>
</html>