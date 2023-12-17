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

<table>
    <tr>
        <th>UserId</th>
        <th>Name</th>
        <th>Address</th>
        <th>PhoneNumber</th>
    </tr>
    <tr>
        <td>${requestScope.user.userId}</td>
        <td>${requestScope.user.name}</td>
        <td>${requestScope.user.address}</td>
        <td>${requestScope.user.phoneNumber}</td>
    </tr>
</table>

</body>
</html>
