<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<body>

<form action="#" method="get">
    <button type="submit" value="reasonNameFilter" name="reasonNameFilter">Filter by reason name</button>
</form>

<p>Заявки на надання довідок від студентів</p>

<table style="width:100%">
    <tr>
        <th>Дата подання заявки</th>
        <th>Причина надання довідки</th>
        <th>Опис причини надання</th>
        <th>Студент</th>
        <th>Група</th>
        <th>Підтвердити надання</th>
        <th>Відмовити від надання</th>
    </tr>
    <c:forEach items="${waitingRequests}" var="element">
        <tr>
            <td><c:out value="${element.startDate}"/></td>
            <td><c:out value="${element.reasonName}"/></td>
            <td><c:out value="${element.reasonDescription}"/></td>
            <td><c:out value="${element.studentFullName}"/></td>
            <td><c:out value="${element.groupName}"/></td>
            <td>
                <form action="#" method="post">
                    <button type="submit" value="<c:out value="${element.id}"/>" name="approveRequest">Підтвердити
                        надання
                    </button>
                </form>
            </td>
            <td>
                <form action="#" method="post">
                    <button type="submit" value="<c:out value="${element.id}"/>" name="denyRequest">Відмовити надання
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p>Завершені запити довідок</p>
<table style="width:100%">
    <tr>
        <th>Дата подання заявки</th>
        <th>Причина надання довідки</th>
        <th>Опис причини надання</th>
        <th>Студент</th>
        <th>Група</th>
        <th>Завантажити довідку</th>
    </tr>
    <c:forEach items="${assignedRequests}" var="element">
        <tr>
            <td><c:out value="${element.startDate}"/></td>
            <td><c:out value="${element.reasonName}"/></td>
            <td><c:out value="${element.reasonDescription}"/></td>
            <td><c:out value="${element.studentFullName}"/></td>
            <td><c:out value="${element.groupName}"/></td>
            <td>
                <form action="#" method="get">
                    <input type="hidden" value="<c:out value="${element.s3FileName}"/>" name="s3FileName"
                           id="s3FileName"/>
                    <button type="submit" value="<c:out value="${element.id}"/>" name="downloadReport">
                        Завантажити
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>