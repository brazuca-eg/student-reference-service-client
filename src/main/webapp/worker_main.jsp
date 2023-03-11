<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>

<head>
    <title>Worker Main Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>

    <h2>Bio</h2>

    <h4>Name</h4>
    <h3><c:out value="${current.name}"/></h3>

    <h4>Surname</h4>
    <h3><c:out value="${current.surname}"/></h3>

    <h4>Fatherhood</h4>
    <h3><c:out value="${current.fatherhood}"/></h3>

    <h4>Email</h4>
    <h3><c:out value="${current.email}"/></h3>

    <h4>Job title</h4>
    <h3><c:out value="${current.jobTitle}"/></h3>

    <h4>Gender</h4>
    <h3><c:out value="${current.gender}"/></h3>


    <h2>Faculty</h2>

    <h4>Name</h4>
    <h3><c:out value="${faculty.name}"/></h3>

    <h4>Short name</h4>
    <h3><c:out value="${faculty.shortName}"/></h3>

</body>


</html>
