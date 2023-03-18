<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<body>

<form action="#" method="get">
    <button type="submit" value="reasonNameFilter" name="reasonNameFilter">Filter by reason name</button>
</form>

<p>Мої заявки на замовлення довідок</p>

<table style="width:100%">
    <tr>
        <th>Дата подання заявки</th>
        <th>Дата закриття заявки</th>
        <th>Причина надання довідки</th>
        <th>Опис причини</th>
        <th>Завантажити довідку</th>
    </tr>
    <c:forEach items="${requests}" var="element">
        <tr>
            <td><c:out value="${element.startDate}"/></td>
            <td><c:out value="${element.endDate}"/></td>
            <td><c:out value="${element.reasonName}"/></td>
            <td><c:out value="${element.reasonDescription}"/></td>
            <td>
                <c:choose>
                    <c:when test="${element.s3FileName != null}">
                        <form action="#" method="get">
                            <input type="hidden" value="<c:out value="${element.s3FileName}"/>" name="s3FileName"
                                   id="s3FileName"/>
                            <button type="submit" value="<c:out value="${element.id}"/>" name="downloadReport">
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
    </c:forEach>
</table>


<p>Створити нову заявку для замовлення довідки</p>

<form action="#" method="post">

    <input type="text" id="serialNumber" name="serialNumber" placeholder="Серійний номер студентського квитка"></p>

    <input type="text" id="number" name="number" placeholder="Номер студентського квитка"></p>

    <p>Виберіть причину замовлення довідки</p>
    <select name="requestReason" id="requestReason">
        <c:forEach items="${reasons}" var="element">
            <h3><c:out value="${element.name}"/></h3>
            <option value="<c:out value="${element.name}"/>"><c:out value="${element.name}"/></option>
        </c:forEach>
    </select>

    <button type="submit" value="submit" name="submitButton">Створити запит</button>

</form>


</body>

</html>