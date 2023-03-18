<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<head>
    <title>See Student</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>
<h2>Bio</h2>

<h4>Name</h4>
<h3><c:out value="${student.name}"/></h3>

<h4>Surname</h4>
<h3><c:out value="${student.surname}"/></h3>

<h4>Fatherhood</h4>
<h3><c:out value="${student.fatherhood}"/></h3>

<h4>Email</h4>
<h3><c:out value="${student.email}"/></h3>

<h4>Gender</h4>
<h3><c:out value="${student.gender}"/></h3>

<c:choose>
    <c:when test="${student.approved == false}">
        <form action="#" method="post">
            <input type="hidden" value="<c:out value="${student.id}"/>" name="studentId"/>
            <input type="text" id="groupName" name="groupName" placeholder="Enter the group name"/>
            <input type="text" id="serialNumber" name="serialNumber" placeholder="Enter the ticket serial number"/>
            <input type="text" id="number" name="number" placeholder="Enter the ticket number"/>
            <label for="startDate">Ticket start date:</label>
            <input type="date" id="startDate" name="startDate">
            <label for="endDate">Ticket end date:</label>
            <input type="date" id="endDate" name="endDate">

            <button type="submit" value="submit" name="approveStudentButton">Approve</button>
        </form>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>

</body>


</html>
