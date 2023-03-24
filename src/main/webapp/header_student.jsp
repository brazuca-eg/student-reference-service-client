<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
  <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
    <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Student Reference Service</h2>
  </a>
  <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarCollapse">
    <div class="navbar-nav ms-auto p-4 p-lg-0">
      <a href="<c:out value="${request.requestURL}student"/>" class="nav-item nav-link active">Профіль</a>
      <a href="<c:out value="${request.requestURL}studentReports"/>" class="nav-item nav-link active">Надані довідки</a>
      <a href="<c:out value="${request.requestURL}studentRequests"/>" class="nav-item nav-link active">Заявки</a>
      <a href="<c:out value="${request.requestURL}studentDeniedRequests"/>" class="nav-item nav-link active">Відмовлені заявки</a>
      <a href="<c:out value="${request.requestURL}createRequest"/>" class="nav-item nav-link active">Створити запит</a>
      <a href="<c:out value="${request.requestURL}logout"/>" class="nav-item nav-link">Вийти</a>
    </div>
  </div>
</nav>
