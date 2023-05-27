<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<head>
    <title>Сторінка очікування підтвердження акаунту</title>

    <jsp:include page="style.jsp" />
</head>

<jsp:include page="header.jsp"/>

<body>
    <div class="container-xxl py-5">
        <div class="container">
            <h6 class="section-title bg-white text-start text-primary pe-3">Раді вітати Вас на сервісі для замовлення
                довідок про навчання з деканату для здобувачів вищої освіти</h6>

            <p>Ваш акаунт чекає на підтвердження адміністратором сервісу. В найближчий час у Вас буде доступ до нього.</p>

            <p>
                <a class="btn btn-primary py-3 px-5 mt-2" href="<c:out value="${request.requestURL}logout"/>">Вийти</a>
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
