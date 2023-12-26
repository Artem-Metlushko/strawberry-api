<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

</head>
<body>
<a href="<%=request.getContextPath()%>/users/new">Add New User</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>address</th>
        <th>phoneNumber</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.usersList}">

        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.address}"/></td>
            <td><c:out value="${user.phoneNumber}"/></td>
            <td><a href="/users/<c:out value='${user.id}'/>/edit">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp; <a href="/users/<c:out value='${user.id}'/>">Delete</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>

</body>
</html>
