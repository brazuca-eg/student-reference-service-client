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

<h6 class="section-title bg-white text-center text-primary px-3">Сортувати</h6>
<form method="get">
    <div class="container">
        <select name="requestReasonFilter" id="requestReasonFilter" class="form-select form-select-sm">
                <option value="reasonNameFilter">Сортувати за причиною надання довідки</option>
                <option value="startDateFilter">Сортувати за датою подання заявки</option>
                <option value="endDateFilter">Сортувати за датою надання довідки</option>
                <option value="endDateFilter">Сортувати за назвою групи</option>
        </select>
    </div>
</form>

<div class="container">
    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title bg-white text-center text-primary px-3">Мої заявки на замовлення довідок</h6>
        <h1 class="mb-5"></h1>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Дата подання заявки</th>
                <th scope="col">Дата закриття заявки</th>
                <th scope="col">Причина надання довідки</th>
                <th scope="col">Опис причини</th>
                <th scope="col">Завантажити довідку</th>
            </tr>
            </thead>
            <c:forEach items="${requests}" var="element">
                <tbody>
                <tr>
                    <fmt:parseDate value="${element.startDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedStartDate" type="both" />
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedStartDate}" /></td>
                    <fmt:parseDate value="${element.endDate}"  pattern="yyyy-MM-dd'T'HH:mm" var="parsedEndDate" type="both" />
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${parsedEndDate}" /></td>
                    <td><c:out value="${element.reasonName}"/></td>
                    <td><c:out value="${element.reasonDescription}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${element.s3FileName != null}">
                                <form action="#" method="get">
                                    <input type="hidden" value="<c:out value="${element.s3FileName}"/>"
                                           name="s3FileName" id="s3FileName"/>
                                    <button type="submit" class="btn btn-primary w-100 py-3"
                                            value="<c:out value="${element.id}"/>" name="downloadReport">
                                        Завантажити
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p>В процесі</p>
                            </c:otherwise>
                        </c:choose>
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