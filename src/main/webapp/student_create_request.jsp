<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Student requests page</title>
    <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_student.jsp"/>

<div class="container-xxl py-5">
    <div class="container">
        <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
            <h6 class="section-title bg-white text-center text-primary px-3">Створити нову заявку для замовлення
                довідки</h6>
            <h1 class="mb-5"></h1>
        </div>
        <div class="row g-4">
            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s"></div>
            <div class="col-lg-4 col-md-12 wow fadeInUp" data-wow-delay="0.5s">
                <form method="post">
                    <div class="row g-3">
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="serialNumber" name="serialNumber"
                                       placeholder="Серійний номер студентського квитка"/>
                                <label for="serialNumber">Серійний номер студентського квитка</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="number" name="number"
                                       placeholder="Номер студентського квитка"></p>
                                <label for="number">Номер студентського квитка</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <select name="requestReason" id="requestReason" class="form-control">
                                    <c:forEach items="${reasons}" var="element">
                                        <h3><c:out value="${element.name}"/></h3>
                                        <option value="<c:out value="${element.name}"/>"><c:out
                                                value="${element.name}"/></option>
                                    </c:forEach>
                                </select>
                                <label for="requestReason">Оберіть причину замовлення довідки</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary w-100 py-3" name="submitButton" type="submit" value="submit">
                                Створити замовлення довідки
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<h3><c:out value='${requestScope["response"]}'/></h3>

<p align="center" style="color:red;"><c:out value='${requestScope["errorResponse"]}'/></p>


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