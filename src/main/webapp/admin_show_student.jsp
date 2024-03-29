<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>See Student</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <jsp:include page="style_inherit.jsp"/>
</head>

<body>

<jsp:include page="header_admin.jsp"/>

<div class="container">
    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title bg-white text-center text-primary px-3">Перегляд здобувача вищої освіти</h6>
        <h1 class="mb-5"></h1>
                    <p>Ім'я: <c:out value="${student.name}"/></p>

                    <p>Прізвище: <c:out value="${student.surname}"/></p>

                    <p>По батькові: <c:out value="${student.fatherhood}"/></p>

                    <p>Email: <c:out value="${student.email}"/></p>

                    <p>Стать: <c:out value="${student.gender}"/></p>

                    <c:choose>
                        <c:when test="${studentGroup == null}">
                            <p>Студентська група: <c:out value="${studentGroup.name}"/></p>
                        </c:when>
                        <c:otherwise>
                            <p>Студентська група: Не додано</p>
                        </c:otherwise>
                    </c:choose>

                    <h2>Дійсний статус: <c:out value="${student.statusDescription}"/></h2>

                    <c:choose>
                        <c:when test="${student.approved == false}">
                            <form method="post">
                                <div class="col-12">
                                    <div class="form-floating">
                                        <select name="studentGroup" id="studentGroup" class="form-control">
                                            <c:forEach items="${studentGroups}" var="element">
                                                <h3><c:out value="${element.name}"/></h3>
                                                <option value="<c:out value="${element.name}"/>"><c:out
                                                        value="${element.name}"/></option>
                                            </c:forEach>
                                        </select>
                                        <label for="studentGroup">Оберіть групу студента</label>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="serialNumber" name="serialNumber"
                                               placeholder="Введіть серійний номер квитка" required>
                                        <label for="serialNumber">Серійний номер квитка</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="number" name="number"
                                               placeholder="Введіть номер квитка" required>
                                        <label for="number">Номер квитка</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="startDate" name="startDate"
                                               placeholder="Введіть початкову дату дії квитка" required>
                                        <label for="startDate">Початкова дата дії квитка</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="date" class="form-control" id="endDate" name="endDate"
                                               placeholder="Введіть кінцеву дату дії квитка" required>
                                        <label for="endDate">Кінцева дата дії квитка</label>
                                    </div>
                                </div>

                                <button type="submit" value="submit" class="btn btn-primary btn-sm"
                                        name="approveStudentButton">
                                    Підтвердити реєстрацію
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <h2>Профіль підтверджено</h2>
                        </c:otherwise>
                    </c:choose>
    </div>
</div>

<hr>

<div style="width:100%">
    <c:choose>
            <c:when test="${student.active == true && student.approved == true}">
                <div style="width:30%; display: inline-block; vertical-align: top; border: thick double #32a1ce;">
                <h4>Змінити статус студента</h4>
                    <form method="post">
                        <p><input type="radio" id="activeFalse" name="activeFalse" value="false"></p>
                        <p><label for="activeFalse">Не активний студент</label></p>
                        <p>
                            <select name="nonActiveReason" id="nonActiveReason" class="form-control">
                                <c:forEach items="${nonActiveReasons}" var="element">
                        <h3><c:out value="${element}"/></h3>
                        <option value="<c:out value="${element}"/>"><c:out value="${element}"/></option>
                        </c:forEach>
                        </select>
                        </p>
                        <p><label for="studentGroup">Оберіть причину для зміни статусу</label> </p>
                        <p><input  type="date" name="endStatusDate" id="endStatusDate"></p>
                        <p> <label for="endStatusDate">Оберіть кінцеву дату, у разі, якщо така є</label></p>
                        <button type="submit" class="btn btn-primary btn-sm" value="change" name="changeStatusButton">
                            Змінити
                        </button>
                    </form>
                </div>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${student.approved == true}">
                <div style="width:30%; display: inline-block; vertical-align: top; border: thick double #32a1ce;">
                    <h4>Змінити дані про студентський квиток</h4>
                    <form method="post">
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="changedSerialNumber" name="changedSerialNumber"
                                       placeholder="Введіть оновлений серійний номер квитка" required>
                                <label for="changedSerialNumber">Новий серійний номер квитка</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="changedNumber" name="changedNumber"
                                       placeholder="Введіть оновлений номер квитка" required>
                                <label for="changedNumber">Новий номер квитка</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="date" class="form-control" id="changedStartDate" name="changedStartDate"
                                       placeholder="Введіть оновлену початкову дату дії квитка" required>
                                <label for="changedStartDate">Нова початкова дата дії квитка</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="date" class="form-control" id="changedEndDate" name="changedEndDate"
                                       placeholder="Введіть оновлену кінцеву дату дії квитка" required>
                                <label for="changedEndDate">Нова кінцева дата дії квитка</label>
                            </div>
                        </div>

                        <button type="submit" value="submit" class="btn btn-primary btn-sm" name="changeTicketButton">
                            Оновити квиток
                        </button>
                    </form>
                </div>
            </c:when>
        </c:choose>

        <div style="width:30%; display: inline-block; vertical-align: top; border: thick double #32a1ce;">
            <h4>Змінити групу студента</h4>
            <form method="post">
                <p>
                    <select name="changedGroup" id="changedGroup" class="form-control">
                        <c:forEach items="${studentGroups}" var="element">
                            <h3><c:out value="${element.name}"/></h3>
                            <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/></option>
                        </c:forEach>
                    </select>
                </p>
                <p><label for="changedGroup">Оберіть нову групу для студента</label> </p>
                <button type="submit" class="btn btn-primary btn-sm" value="change" name="changeGroupButton">
                    Змінити групу
                </button>
            </form>
        </div>

</div>


<jsp:include page="footer.jsp"/>

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>

</body>


</html>
