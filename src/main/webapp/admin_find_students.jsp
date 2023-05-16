<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Пошук студентів</title>
  <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_admin.jsp"/>

<div class="col-lg-3 col-md-6; align-items-center" style="margin:0 auto;">
  <h4 style="color: #181d38">Пошук студентів</h4>
  <form method="get">
    <div class="position-relative mx-auto" style="max-width: 450px;">

      <div class="form-check">
        <select name="studentGroup" id="studentGroup" class="form-control">
          <c:forEach items="${studentGroups}" var="element">
            <h3><c:out value="${element.name}"/></h3>
            <option value="<c:out value="${element.name}"/>"><c:out
                    value="${element.name}"/></option>
          </c:forEach>
        </select>
        <label for="studentGroup">Оберіть потрібну групу</label>
      </div>

      <p><button type="submit" value="search" name="searchButton" class="btn btn-primary btn-sm">Пошук</button></p>
    </div>

  </form>
</div>
<p align="center" style="color:red;"><c:out value='${requestScope["errorResponse"]}'/></p>

<div class="col-lg-3 col-md-6; align-items-center" style="margin:0 auto;">
  <form method="get">
    <button type="submit" value="clear" name="clearButton" class="btn btn-primary btn-sm">Очистити дані пошуку</button>
  </form>
</div>

<hr>

<c:choose>
  <c:when test="${fn:length(searchedStudents) > 0}">
    <div class="container">
      <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title bg-white text-center text-primary px-3">Студенти в групі</h6>
        <h1 class="mb-5"></h1>
        <table class="table">
          <thead class="thead-light">
          <tr>
            <th scope="col">Email</th>
            <th scope="col">Ім'я</th>
            <th scope="col">Прізвище</th>
            <th scope="col">По батькові</th>
            <th scope="col">Стать</th>
            <th scope="col">Відкрити профіль</th>
          </tr>
          </thead>
          <c:forEach items="${searchedStudents}" var="element">
            <tbody>
            <tr>
              <td><c:out value="${element.email}"/></td>
              <td><c:out value="${element.name}"/></td>
              <td><c:out value="${element.surname}"/></td>
              <td><c:out value="${element.fatherhood}"/></td>
              <td><c:out value="${element.gender}"/></td>
              <td>
                <a href="<c:out value="${request.requestURL}adminShowStudent/"/><c:out value="${element.id}"/>" class="nav-item nav-link">Подивитися</a>
              </td>
            </tr>
            </tbody>
          </c:forEach>
        </table>
      </div>
    </div>

  </c:when>
  <c:when test="${searchedStudents == null}">
    <h6 class="bg-white text-center text-primary px-3">Введіть пошукові дані для пошуку студентів</h6>
  </c:when>
  <c:otherwise>
    <h6 class="bg-white text-center text-primary px-3">Немає студентів за заданими критеріями</h6>
  </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>

<!-- Back to Top -->
<a class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


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
