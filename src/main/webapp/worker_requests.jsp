<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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


</body>

</html>