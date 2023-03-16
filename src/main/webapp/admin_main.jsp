<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Student Reference Service</title>

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

<div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
    <div class="container">
        <div class="text-center">
            <h6 class="section-title bg-white text-center text-primary px-3">Адміністратор сервісу</h6>
            <h1 class="mb-5">Мій профіль</h1>
        </div>
        <div class="position-relative">
            <div class="testimonial-item text-center">
                <img class="border rounded-circle p-2 mx-auto mb-3" src="img/nure.png"
                     style="width: 80px; height: 80px;">
                <h5 class="mb-0">
                    <c:out value="${current.name} ${current.surname} ${current.fatherhood}"/>
                </h5>
                <p>Пошта: <c:out value="${current.email}"/></p>
                <p>Стать: <c:out value="${current.gender}"/></p>
            </div>
        </div>
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