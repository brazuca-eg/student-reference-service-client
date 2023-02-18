<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!doctype html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>

    <body align="center">
        <p>Student Reference Service</p>
        <form  action="#" method="post">

            <input type="text" id="email" name="email" value="" placeholder="Email">

            <input type="password" id="password" name="password"  value="" placeholder="Password">

            <button type="submit" value="submit" name = "submitButton">Login</button>

        </form>

        <h3><c:out value="${error}"/></h3>

    </body>

</html>