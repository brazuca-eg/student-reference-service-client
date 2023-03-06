<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>

<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>--%>
<%--    <meta charset=utf-8>--%>
<%--</head>--%>

<body>

<form action="#" method="post">

    <input type="text" id="email" name="email" placeholder="Email"></p>

    <p><input type="password" id="password" name="password" placeholder="Password"></p>

    <p><input type="password" id="password2" name="password2" placeholder="Repeat password">

    <p><input type="text" id="name" name="name" placeholder="Name"></p>

    <p><input type="text" id="surname" name="surname" placeholder="Surname"></p>

    <p><input type="text" id="fatherhood" name="fatherhood" placeholder="Fatherhood"></p>

    <p>Please select your gender</p>
    <input type="radio" id="gender_m" name="gender" value="Male">
    <label for="gender_m">Male</label><br>
    <input type="radio" id="gender_f" name="gender" value="Female">
    <label for="gender_f">Female</label><br>

    <p>Please select your attitude to the web sevice</p>
    <input type="radio" id="role_student" name="role" value="Student">
    <label for="role_student">I'm a student</label><br>
    <input type="radio" id="role_worker" name="role" value="Worker">
    <label for="role_worker">I'm a worker</label><br>

    <button type="submit" value="submit" name="submitButton">Register</button>

</form>


</body>

</html>