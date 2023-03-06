<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!doctype html>

<head>
  <title>Waiting for approve</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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

  <h2>My group</h2>

  <h4>Group</h4>
  <h3><c:out value="${group.name}"/></h3>

  <h4>Learning form</h4>
  <h3><c:out value="${group.learnForm}"/></h3>

  <h4>Start year</h4>
  <h3><c:out value="${group.startYear}"/></h3>

  <h4>End year</h4>
  <h3><c:out value="${group.endYear}"/></h3>

</body>


</html>
