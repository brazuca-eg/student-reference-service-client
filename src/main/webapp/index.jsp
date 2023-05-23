<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Сервіс надання довідок студента</title>

    <jsp:include page="style.jsp"/>
</head>

<body>
<!-- Spinner Start -->
<div id="spinner"
     class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading...</span>
    </div>
</div>
<!-- Spinner End -->

<jsp:include page="header.jsp"/>

<div class="container-xxl py-5">
    <div class="container">
        <h6 class="section-title bg-white text-start text-primary pe-3">Раді вітати Вас на сервісі для замовлення
            довідок про навчання з деканату для здобувачів вищої освіти</h6>

        <p>Якщо в Вас вже є акаунт, то перейдіть на сторінку логіну</p>
        <p>
            <a class="btn btn-primary py-3 px-5 mt-2" href="<c:out value="${request.requestURL}login"/>">Логін</a>
        </p>


        <p>Якщо в Вас вже ше немає акаунту, то перейдіть на сторінку реєстрації</p>
        <p>
            <a class="btn btn-primary py-3 px-5 mt-2" href="<c:out value="${request.requestURL}register"/>">Реєстрація</a>
        </p>
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