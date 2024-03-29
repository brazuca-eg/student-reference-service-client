<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>

<head>
  <title>Student requests page</title>
  <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_student.jsp"/>

<c:choose>
  <c:when test="${fn:length(studentDeniedRequests) > 0}">
<div class="container">
  <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
    <h6 class="section-title bg-white text-center text-primary px-3">Мої заявки на замовлення довідок</h6>
    <h1 class="mb-5"></h1>
    <table class="table">
      <thead class="thead-light">
      <tr>
        <th scope="col">Дата подання</th>
        <th scope="col">Місце подання</th>
        <th scope="col">Опис причини</th>
        <th scope="col">Причина відмови</th>
        <th scope="col">Дата відмови</th>

      </tr>
      </thead>
      <c:forEach items="${studentDeniedRequests}" var="element">
        <tbody>
        <tr>
          <fmt:parseDate value="${element.startDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDate" type="both" />
          <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedStartDate}" /></td>
          <td><c:out value="${element.reasonName}"/></td>
          <td><c:out value="${element.reasonDescription}"/></td>
          <td><c:out value="${element.comment}"/></td>
          <fmt:parseDate value="${element.endDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDate" type="both" />
          <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEndDate}" /></td>
        </tr>
        </tbody>
      </c:forEach>
    </table>
  </div>
</div>
</c:when>
<c:otherwise>
  <h3 align="center">Немає відмовлених заявок на отриманння довідки</h3>
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