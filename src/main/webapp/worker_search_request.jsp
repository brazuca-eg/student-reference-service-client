<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>


<head>
  <title>Пошук наданих довідок</title>
  <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_worker.jsp"/>

<div class="col-lg-3 col-md-6">
  <h4 class="text-white mb-3">Newsletter</h4>
  <p>Пошук наданих довідок</p>
  <form method="get">
    <div class="position-relative mx-auto" style="max-width: 450px;">
      <input class="form-control border-0 w-100 py-3 ps-4 pe-5" type="text" name="searchRequest" placeholder="Введіть дані для пошуку">

      <div class="form-check">
        <input class="form-check-input" type="radio" name="searchParam" id="searchParam1" value="byStudent">
        <label class="form-check-label" for="searchParam1">
          За ім'ям студента
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="radio" name="searchParam" id="searchParam2" checked value="byGroup">
        <label class="form-check-label" for="searchParam2">
          За групою
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="radio" name="searchParam" id="searchParam3" checked value="byDate">
        <label class="form-check-label" for="searchParam3">
          За датою у форматі (рік-місяць-день)
        </label>
      </div>

      <button type="submit" value="submit" name="searchButton" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">Пошук</button>
    </div>
  </form>
</div>

<hr>

<form method="get">
  <button type="submit" value="clear" name="clearButton" class="btn btn-primary btn-sm">Очистити дані пошуку</button>
</form>

<c:choose>
  <c:when test="${fn:length(assignedReports) > 0}">
    <div class="container">
      <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title bg-white text-center text-primary px-3">Надані довідки</h6>
        <h1 class="mb-5"></h1>
        <table class="table">
          <thead class="thead-light">
          <tr>
            <th scope="col">Дата подання</th>
            <th scope="col">Місце подання</th>
            <th scope="col">Опис причини</th>
            <th scope="col">Студент</th>
            <th scope="col">Спеціальність</th>
            <th scope="col">Освітня програма</th>
            <th scope="col">Група</th>
            <th scope="col">Дата надання</th>
            <th scope="col">Завантажити</th>
          </tr>
          </thead>
            <tbody>
              <c:forEach items="${assignedReports}" var="element">
                <tr>
                  <fmt:parseDate value="${element.startDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDate" type="both" />
                  <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedStartDate}" /></td>
                  <td><c:out value="${element.reasonName}"/></td>
                  <td><c:out value="${element.reasonDescription}"/></td>
                  <td><c:out value="${element.studentFullName}"/></td>
                  <td><c:out value="${element.specialityName}"/></td>
                  <td><c:out value="${element.educationalProgram}"/></td>
                  <td><c:out value="${element.groupName}"/></td>
                  <fmt:parseDate value="${element.endDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDate" type="both" />
                  <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEndDate}" /></td>
                  <td>
                    <form method="get">
                      <input type="hidden" value="<c:out value="${element.s3FileName}"/>" name="s3FileName" id="s3FileName"/>
                      <button type="submit" class="btn btn-primary btn-sm" value="<c:out value="${element.id}"/>" name="downloadReport">Завантажити</button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
      </div>
    </div>
  </c:when>
  <c:when test="${assignedReports == null}">
    <h6 class="bg-white text-center text-primary px-3">Введіть дані для пошуку довідок</h6>
  </c:when>
  <c:otherwise>
    <h6 class="bg-white text-center text-primary px-3">Немає довідок з даними пошуковими параметрами</h6>
  </c:otherwise>
</c:choose>

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