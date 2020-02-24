<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 13.02.2020
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
				<title>Error</title>
</head>
<body>
error<br>
<hr>
Failed URL: ${url}<br>
<hr>
Exception: ${exception.message}<br>
<hr>
<c:forEach items="${exception.stackTrace}" var="ste">
				${ste}<br>
</c:forEach>
</body>
</html>
