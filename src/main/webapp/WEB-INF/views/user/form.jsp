<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 15.02.2020
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
				<meta charset="UTF-8"/>
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<meta http-equiv="X-UA-Compatible" content="ie=edge"/>
				<title>Podaruj</title>
				<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<%@include file="header-logged.jsp" %>

<section class="form--steps">
				<div class="form--steps-instructions">
								<div class="form--steps-container">
												<h3>Ważne!</h3>
												<p data-step="1" class="active">
																Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
																wiedzieć komu najlepiej je przekazać.
												</p>
												<p data-step="2">
																Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
																wiedzieć komu najlepiej je przekazać.
												</p>
												<p data-step="3">
																Wybierz jedną, do
																której trafi Twoja przesyłka.
												</p>
												<p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
								</div>
				</div>

				<div class="form--steps-container">
								<div class="form--steps-counter">Krok <span>1</span>/4</div>
								<%--@elvariable id="donation" type="donation"--%>
								<form:form modelAttribute="donation" method="post">

												<!-- STEP 1: class .active is switching steps -->
												<div data-step="1" class="active">
																<h3>Zaznacz co chcesz oddać:</h3>
																<h4 class="form-error"><form:errors/></h4>
																<h4 class="form-error"><form:errors path="quantity"/></h4>
																<h4 class="form-error"><form:errors path="institution"/></h4>
																<h4 class="form-error"><form:errors path="street"/></h4>
																<h4 class="form-error"><form:errors path="city"/></h4>
																<h4 class="form-error"><form:errors path="zipCode"/></h4>
																<h4 class="form-error"><form:errors path="pickUpDate"/></h4>
																<h4 class="form-error"><form:errors path="pickUpTime"/></h4>

																<c:forEach items="${categories}" var="category">

																				<div class="form-group form-group--checkbox">
																								<label>
																												<form:checkbox path="categories" value="${category.id}"/>

																												<span class="checkbox"></span>
																												<span class="description">
                            <c:out value="${category.name}"/>
                        </span>
																								</label>
																				</div>
																</c:forEach>


																<div class="form-group form-group--buttons">
																				<button type="button" class="btn next-step">Dalej</button>
																</div>
												</div>

												<!-- STEP 2 -->
												<div data-step="2">
																<h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
																<h4 class="form-error"><form:errors path="quantity"/></h4>
																<div class="form-group form-group--inline">
																				<label>
																								Liczba 60l worków:
																								<form:input path="quantity" type="number" step="1" min="1" value="1"/>
																				</label>
																</div>

																<div class="form-group form-group--buttons">
																				<button type="button" class="btn prev-step">Wstecz</button>
																				<button type="button" class="btn next-step">Dalej</button>
																</div>
												</div>


												<!-- STEP 3 -->
												<div data-step="3">
																<h3>Wybierz organizacje, której chcesz pomóc:</h3>
																<h4 class="form-error"><form:errors path="institution"/></h4>
																<c:forEach items="${institutions}" var="institution">
																				<div class="form-group form-group--checkbox">
																								<label>
																												<form:radiobutton path="institution" value="${institution.id}"/>
																												<span class="checkbox radio"></span>
																												<span class="description">
                                <div class="title"><c:out value="${institution.name}"/></div>
                                <div class="subtitle"><c:out value="${institution.description}"/></div>
                            </span>
																								</label>
																				</div>
																</c:forEach>

																<div class="form-group form-group--buttons">
																				<button type="button" class="btn prev-step">Wstecz</button>
																				<button type="button" class="btn next-step">Dalej</button>
																</div>
												</div>

												<!-- STEP 4 -->
												<div data-step="4">
																<h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>
																<div class="form-section form-section--columns">
																				<div class="form-section--column">
																								<h4>Adres odbioru</h4>
																								<div class="form-group form-group--inline">
																												<h4 class="form-error"><form:errors path="street"/></h4>
																												<label> Ulica <form:input path="street"/> </label>
																								</div>

																								<div class="form-group form-group--inline">
																												<h4 class="form-error"><form:errors path="city"/></h4>
																												<label> Miasto <form:input path="city"/></label>
																								</div>

																								<div class="form-group form-group--inline">
																												<label>
																																<h4 class="form-error"><form:errors path="zipCode"/></h4>
																																Kod pocztowy <form:input path="zipCode"/>
																												</label>
																								</div>

																												<%--                        <div class="form-group form-group--inline">--%>
																												<%--                            <label>--%>
																												<%--                                Numer telefonu <input type="phone" name="phone" />--%>
																												<%--                            </label>--%>
																												<%--                        </div>--%>
																				</div>

																				<div class="form-section--column">
																								<h4>Termin odbioru</h4>
																								<div class="form-group form-group--inline">
																												<h4 class="form-error"><form:errors path="pickUpDate"/></h4>
																												<label> Data <form:input type="date" path="pickUpDate"/> </label>
																								</div>

																								<div class="form-group form-group--inline">
																												<h4 class="form-error"><form:errors path="pickUpTime"/></h4>
																												<label> Godzina <form:input path="pickUpTime" type="time"/> </label>
																								</div>

																								<div class="form-group form-group--inline">
																												<label>
																																Uwagi dla kuriera
																																<form:textarea path="pickUpComment" rows="5"></form:textarea>
																												</label>
																								</div>
																				</div>
																</div>
																<div class="form-group form-group--buttons">
																				<button type="button" class="btn prev-step">Wstecz</button>
																				<button type="button" class="btn next-step">Dalej</button>
																</div>
												</div>

												<!-- STEP 5 -->
												<div data-step="5">
																<h3>Podsumowanie Twojej darowizny</h3>

																<div class="summary">
																				<div class="form-section">
																								<h4>Oddajesz:</h4>
																								<ul>
																												<li>
																																<span class="icon icon-bag"></span>
																																<span class="summary--text">
                                    4 worki ubrań w dobrym stanie dla dzieci
                                </span>
																												</li>

																												<li>
																																<span class="icon icon-hand"></span>
																																<span class="summary--text">
                                    Dla fundacji "Mam marzenie" w Warszawie
                                </span>
																												</li>
																								</ul>
																				</div>

																				<div class="form-section form-section--columns">
																								<div class="form-section--column">
																												<h4>Adres odbioru:</h4>
																												<ul>
																																<li>Prosta 51</li>
																																<li>Warszawa</li>
																																<li>99-098</li>
																																				<%--                                <li>123 456 789</li>--%>
																												</ul>
																								</div>

																								<div class="form-section--column">
																												<h4>Termin odbioru:</h4>
																												<ul>
																																<li>13/12/2018</li>
																																<li>15:40</li>
																																<li>Brak uwag</li>
																												</ul>
																								</div>
																				</div>
																</div>

																<div class="form-group form-group--buttons">
																				<button type="button" class="btn prev-step">Wstecz</button>
																				<button type="submit" class="btn">Potwierdzam</button>
																</div>
												</div>
								</form:form>
				</div>
</section>

<%@include file="/WEB-INF/views/footer.jsp" %>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/app.js"/>"></script>
<script src="<c:url value="/resources/js/formApp.js"/>"></script>
</body>
</html>
