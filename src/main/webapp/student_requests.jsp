<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<body>

<form action="#" method="get">
    <button type="submit" value="reasonNameFilter" name="reasonNameFilter">Filter by reason name</button>
</form>

<p>My requests</p>
<c:forEach items="${requests}" var="element">
    <p><c:out value="${element.startDate}"/></p>
    <p><c:out value="${element.endDate}"/></p>
    <p><c:out value="${element.reasonName}"/></p>
    <p><c:out value="${element.reasonDescription}"/></p>
</c:forEach>

<p>Create new request</p>




<form action="#" method="post">

    <input type="text" id="serialNumber" name="serialNumber" placeholder="Serial number of the student ticket"></p>

    <input type="text" id="number" name="number" placeholder="Number of the student ticket"></p>


    <select name="requestReason" id="requestReason">
        <c:forEach items="${reasons}" var="element">
            <h3><c:out value="${element.name}"/></h3>
            <option value="<c:out value="${element.name}"/>"><c:out value="${element.name}"/></option>
        </c:forEach>
    </select>

    <button type="submit" value="submit" name="submitButton">Create request</button>

</form>


</body>

</html>