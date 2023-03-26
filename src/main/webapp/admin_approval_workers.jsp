<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
  <title>Student requests page</title>
  <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_admin.jsp"/>

<div class="container">
  <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
    <h6 class="section-title bg-white text-center text-primary px-3">Робітники деканату, які чекають підтвердження реєстрації</h6>
    <h1 class="mb-5"></h1>
    <table class="table">
      <thead class="thead-light">
      <tr>
        <th scope="col">Email</th>
        <th scope="col">Ім'я</th>
        <th scope="col">Прізвище</th>
        <th scope="col">По батькові</th>
        <th scope="col">Стать</th>
        <th scope="col">Робоча спеціальність</th>
        <th scope="col">Факультет</th>
        <th scope="col">Підтвердити</th>
      </tr>
      </thead>
      <c:forEach items="${waitingApprovalWorkers}" var="element">
        <tbody>
        <tr>
          <td><c:out value="${element.email}"/></td>
          <td><c:out value="${element.name}"/></td>
          <td><c:out value="${element.surname}"/></td>
          <td><c:out value="${element.fatherhood}"/></td>
          <td><c:out value="${element.gender}"/></td>
          <form  method="post">
            <input type="hidden" value="<c:out value="${element.id}"/>" name="workerId" />
            <td>
              <input type="text" class="form-control border-3 w-75 p-3" id="jobTitle" name="jobTitle" placeholder="Встановіть робочу спеціальність робітнику"/>
            </td>
            <td>
              <select name="faculty" id="faculty" class="form-control">
                <c:forEach items="${faculties}" var="element">
                  <h3><c:out value="${element.name}"/></h3>
                  <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/></option>
                </c:forEach>
              </select>
              <label for="faculty">Оберіть факультет робітника деканату</label>
            </td>
            <td>
              <button type="submit" value="submit" class="btn btn-primary btn-sm" name="approveWorkerButton">Підтвердити реєстрацію</button>
            </td>
          </form>
        </tr>
        </tbody>
      </c:forEach>
    </table>
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