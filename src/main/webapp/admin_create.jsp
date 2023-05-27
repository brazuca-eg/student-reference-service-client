<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Admin creation page</title>
    <jsp:include page="style.jsp" />
</head>

<body>
<jsp:include page="header_admin.jsp"/>
<!-- Contact Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Додати факультет до системи</h6>
            <h1 class="mb-5"></h1>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s"></div>
            <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                <form action="#" method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="facultyName" name="facultyName" placeholder="Введіть назву факультету" required>
                                <label for="facultyName">Назва факультету</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="facultyShortName" name="facultyShortName" placeholder="Введіть скорочену назву факультету" required>
                                <label for="facultyShortName">Скорочена назва факультету</label>
                            </div>
                        </div>

                        <div class="col-12">
                            <button class="btn btn-primary w-100 py-3" name="createFacultyButton" type="submit" value="submit">Створити факультет</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<hr>

<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Додати спеціальність до системи</h6>
            <h1 class="mb-5"></h1>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s"></div>
            <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                <form action="#" method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="specialityName" name="specialityName" placeholder="Введіть назву спеціальності" required>
                                <label for="specialityName">Назва спеціальності</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="specialityShortName" name="specialityShortName" placeholder="Введіть скорочену назву спеціальності" required>
                                <label for="specialityShortName">Скорочена назва спеціальності</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="number" class="form-control" id="specialityNumber" name="specialityNumber" placeholder="Введіть номер спеціальності" required>
                                <label for="specialityNumber">Номер спеціальності</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="educationalProgram" name="educationalProgram" placeholder="Введіть назву освітньої програми" required>
                                <label for="educationalProgram">Освітня програма</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <select name="faculty" id="faculty" class="form-control" required>
                                    <c:forEach items="${faculties}" var="element">
                                        <h3><c:out value="${element.name}"/></h3>
                                        <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/> [<c:out value="${element.shortName}"/>]</option>
                                    </c:forEach>
                                </select>
                                <label for="faculty">Оберіть факультет спеціальності</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary w-100 py-3" name="createSpecialityButton" type="submit" value="submit">Створити спеціальність</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<hr>

<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Додати групу до системи</h6>
            <h1 class="mb-5"></h1>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s"></div>
            <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                <form action="#" method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="groupName" name="groupName" placeholder="Введіть назву групи" required/>
                                <label for="groupName">Назва групи</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <select name="groupLearnForm" id="groupLearnForm" class="form-control">
                                    <option value="Денна">Денна</option>
                                    <option value="Заочна">Заочна</option>
                                </select>
                                <label for="groupLearnForm">Форма навчання</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <select name="groupDegreeForm" id="groupDegreeForm" class="form-control" required>
                                    <option value="Бакалавр">Бакалавр</option>
                                    <option value="Магістр">Магістр</option>
                                </select>
                                <label for="groupDegreeForm">Рівень освіти</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="date" class="form-control" id="groupStartYear" name="groupStartYear" placeholder="Оберіть дату початку навчання групи" required/>
                                <label for="groupStartYear">Дата початку навчання групи</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="date" class="form-control" id="groupEndYear" name="groupEndYear" placeholder="Оберіть дату закінчення навчання групи" required/>
                                <label for="groupEndYear">Дата закінчення навчання групи</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <select name="specialityForGroup" id="specialityForGroup" class="form-control" required>
                                    <c:forEach items="${specialities}" var="element">
                                        <h3><c:out value="${element.name}"/></h3>
                                        <option value="<c:out value="${element.id}"/>"><c:out value="${element.number}"/> <c:out value="${element.name}"/> - <c:out value="${element.educationalProgram}"/></option>
                                    </c:forEach>
                                </select>
                                <label for="specialityForGroup">Оберіть спеціальність групи</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary w-100 py-3" name="createGroupButton" type="submit" value="submit">Створити групу</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<!-- Contact End -->


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
