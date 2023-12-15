<%@ page import="com.metlushko.strawberry.model.User" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>HTML Table</h2>
<%
    User user = (User)request.getAttribute("user");
%>
<table>
    <tr>
        <th>UserId</th>
        <th>UserId</th>
        <th>Name</th>
        <th>Address</th>
        <th>PhoneNumber</th>
    </tr>
    <tr>
        <td>${user.userId}</td>
        <td>${user.getName()}</td>
        <td>${user.getAddress()}</td>
        <td>${user.getPhoneNumber()}</td>
    </tr>
</table>

</body>
</html>
