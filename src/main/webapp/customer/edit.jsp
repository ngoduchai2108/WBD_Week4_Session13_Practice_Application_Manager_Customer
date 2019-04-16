<%--
  Created by IntelliJ IDEA.
  User: admin1
  Date: 16/04/2019
  Time: 08:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
<h1>Edit Customer</h1>
<p>
    <c:if test = '${requestScope["message"]!=null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/customers">Back to customer list</a>
</p>
<form method="post">
    <fieldset>
        <legend>
            Customer Information
        </legend>
        <table>
            <tr>
                <td>Name</td>
                <td><label for="name"></label><input type="text" name="name" id="name" value="${requestScope["customer"].getName()}"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><label for="email"></label><input type="text" name="email" id="email" value="${requestScope["customer"].getEmail()}"/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><label for="address"></label><input type="text" name="address" id="address" value="${requestScope["customer"].getAddress()}"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update Customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
