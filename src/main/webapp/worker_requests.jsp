<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>


<head>
    <title>Перегляд запитів довідок</title>
    <jsp:include page="style.jsp"/>
</head>

<body>
<jsp:include page="header_worker.jsp"/>

<form method="get">
    <button type="submit" value="reasonNameFilter" name="reasonNameFilter">Filter by reason name</button>
</form>

<div class="container">
    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title bg-white text-center text-primary px-3">Заявки на надання довідок від студентів</h6>
        <h1 class="mb-5"></h1>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Дата подання заявки</th>
                <th scope="col">Причина надання довідки</th>
                <th scope="col">Опис причини надання</th>
                <th scope="col">Студент</th>
                <th scope="col">Група</th>
                <th>Підтвердити надання</th>
                <th>Відмовити від надання</th>
            </tr>
            </thead>
            <c:forEach items="${waitingRequests}" var="element">
                <tbody>
                <tr>
                    <td><c:out value="${element.startDate}"/></td>
                    <td><c:out value="${element.reasonName}"/></td>
                    <td><c:out value="${element.reasonDescription}"/></td>
                    <td><c:out value="${element.studentFullName}"/></td>
                    <td><c:out value="${element.groupName}"/></td>
                    <td>
                        <form method="post">
                            <button type="submit" class="btn btn-primary btn-sm" value="<c:out value="${element.id}"/>" name="approveRequest">
                                Підтвердити надання
                            </button>
                        </form>
                    </td>
                    <td>
                        <form method="post">
                            <input type="text" id="deniedComment" class="form-control form-control-sm" name="deniedComment" placeholder="Причина відмови надання довідки"/>
                            <input id="deniedRequestId" name="deniedRequestId" type="hidden" value="<c:out value = "${element.id}"/>">
                            <button type="submit" class="btn btn-primary btn-sm" value="val" name="denyRequestButton">Відмовити надання</button>
                        </form>
                    </td>
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