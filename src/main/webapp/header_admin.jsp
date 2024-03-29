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
      <a href="<c:out value="${request.requestURL}admin"/>" class="nav-item nav-link active">Профіль</a>
      <a href="<c:out value="${request.requestURL}adminApproveWorkers"/>" class="nav-item nav-link active">Заявки робітників деканату</a>
      <a href="<c:out value="${request.requestURL}adminShowWaitingStudents"/>" class="nav-item nav-link active">Заявки студентів</a>
      <a href="<c:out value="${request.requestURL}adminSearchStudents"/>" class="nav-item nav-link active">Пошук студентів</a>
      <a href="<c:out value="${request.requestURL}adminCreation"/>" class="nav-item nav-link active">Створення</a>
      <a href="<c:out value="${request.requestURL}logout"/>" class="nav-item nav-link">Вийти</a>
    </div>
  </div>
</nav>
