<%--
  Created by IntelliJ IDEA.
  User: admin1
  Date: 15/04/2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new customers</title>
    <style>
        .message{
            color:green;
        }
    </style>
</head>
<body>
    <h1>Create new Customers</h1>
    <p>
        <c:if test='${requestScope["message"]!=null}'>
        <span class="message">${requestScope["message"]}</span>
        </c:if>
    </p>
    <p>
        <a href="/customers"> Back to customer list</a>
    </p>
    <form method="post">
        <fieldset>
            <legend>Customer Information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><label for="name"></label><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><label for="email"></label><input type="text" name="email" id="email"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><label for="address"></label><input type="text" name="address" id="address"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create customer"></td>
            </tr>
        </table>
        </fieldset>
</form>
</body>
</html>
