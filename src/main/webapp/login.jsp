<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>Student Reference Service</title>

    <jsp:include page="style.jsp" />
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

<!-- About Start -->
<div class="container-xxl py-5">
    <div class="container">
        <div class="row g-5">
            <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">
                <div class="position-relative h-100">
                    <img class="img-fluid position-absolute w-100 h-100" src="img/about.jpg" alt=""
                         style="object-fit: cover;">
                </div>
            </div>
            <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                <h6 class="section-title bg-white text-start text-primary pe-3">Про нас</h6>
                <h1 class="mb-4">Сервіс надання довідок студентам</h1>
                <p class="mb-4">Автоматизований сервіс, який надає студентам вищих навчальник закладів довідки про
                    навчання в різноманітні державні та приватні установи.</p>
                <p class="mb-4">Наш сервіс надає довідки для студентів по різних напрямках</p>
                <div class="row gy-2 gx-4 mb-4">
                    <div class="col-sm-6">
                        <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Довідка на вимогу роботодавця
                        </p>
                    </div>
                    <div class="col-sm-6">
                        <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Довідка на вимогу ТЦК</p>
                    </div>
                    <div class="col-sm-6">
                        <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Довідка на вимогу ВПО</p>
                    </div>
                    <div class="col-sm-6">
                        <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Довідка в іноземні установи
                        </p>
                    </div>
                    <div class="col-sm-6">
                        <p class="mb-0"><i class="fa fa-arrow-right text-primary me-2"></i>Та інші</p>
                    </div>
                </div>
                <div class="row gy-2 gx-4 mb-4">
                    <form action="#" method="post">
                        Email
                        <input type="text" id="email" name="email" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть вашу пошту">
                        Пароль
                        <input type="password" id="password" name="password" class="form-control border-3 w-75 p-3"
                               placeholder="Введіть ваш пароль">

                        <button type="submit" value="submit" name="submitButton" class="btn btn-primary py-3 px-5 mt-2">
                            Логін
                        </button>
                    </form>
                </div>
                Немає акаунту?
                <p><a class="btn btn-primary py-3 px-5 mt-2" href="<c:out value="${request.requestURL}register"/>">Реєстрація</a>
                </p>
            </div>
        </div>
    </div>
</div>
<!-- About End -->

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