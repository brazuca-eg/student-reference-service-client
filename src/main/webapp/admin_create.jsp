<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>

<head>
    <title>Admon creation page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>

<h2>Create new faculty</h2>

<form action="#" method="post">
    <input type="text" id="name" name="name" placeholder="Enter the name of the faculty"/>
    <input type="text" id="shortName" name="shortName" placeholder="Enter the short name of the faculty"/>

    <button type="submit" value="submit" name="createFacultyButton">Create faculty</button>
</form>

<h2>Create new speciality</h2>

<form action="#" method="post">
    <input type="text" id="specialityName" name="specialityName" placeholder="Enter the name of the speciality"/>
    <input type="text" id="specialityShortName" name="specialityShortName"
           placeholder="Enter the short name of the speciality"/>
    <input type="number" id="specialityNumber" name="specialityNumber"
           placeholder="Enter the number of the speciality"/>
    <select name="faculty" id="faculty">
        <c:forEach items="${faculties}" var="element">
            <h3><c:out value="${element.name}"/></h3>
            <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/></option>
        </c:forEach>
    </select>

    <button type="submit" value="submit" name="createSpecialityButton">Create speciality</button>
</form>

<h2>Create new group</h2>

<form action="#" method="post">
    <input type="text" id="groupName" name="groupName" placeholder="Enter the name of the group"/>
    <select name="groupLearnForm" id="groupLearnForm">
        <option value="Денна">Денна</option>
        <option value="Заочна">Заочна</option>
    </select>
    <select name="groupDegreeForm" id="groupDegreeForm">
        <option value="Бакалавр">Бакалавр</option>
        <option value="Магістр">Магістр</option>
    </select>
    <input type="date" id="groupStartYear" name="groupStartYear">
    <input type="date" id="groupEndYear" name="groupEndYear">
    <select name="speciality" id="speciality">
        <c:forEach items="${specialities}" var="element">
            <h3><c:out value="${element.name}"/></h3>
            <option value="<c:out value="${element.id}"/>"><c:out value="${element.name}"/></option>
        </c:forEach>
    </select>

    <button type="submit" value="submit" name="createGroupButton">Create group</button>
</form>


</body>


</html>
