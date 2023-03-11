<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<body>


<h2>Workers that need approval</h2>

<c:forEach items="${waitingApprovalWorkers}" var="element">
    <p><c:out value="${element.email}"/></p>
    <p><c:out value="${element.name}"/></p>
    <p><c:out value="${element.surname}"/></p>
    <p><c:out value="${element.fatherhood}"/></p>
    <p><c:out value="${element.gender}"/></p>

    <form action="#" method="post">
        <input type="hidden" value="<c:out value="${element.id}"/>" name="workerId" />
        <input type="text" id="jobTitle" name="jobTitle" placeholder="Enter the job title of the worker"/>
        <select name="faculty" id="faculty">
            <c:forEach items="${faculties}" var="element">
                <h3><c:out value="${element.name}"/></h3>
                <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/></option>
            </c:forEach>
        </select>
        <button type="submit" value="submit" name="approveButton">Approve</button>
    </form>
</c:forEach>


</body>

</html>