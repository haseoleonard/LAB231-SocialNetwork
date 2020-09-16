<%-- 
    Document   : login
    Created on : Sep 16, 2020, 11:09:32 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1>Login Form</h1>
        <form action="DispatchController" method="POST">
            Username <input type="text" name="txtUsername"/><br/>
            Password <input type="password" name="txtPassword"/><br/>
            <c:if test="${not empty requestScope.error}">
                <font style="color: red">${requestScope.error}</font><br/>
            </c:if>
            <input type="submit" name="btAction" value="Login"/>
            <input type="submit" name="btAction" value="Create Account"/>
        </form>
    </body>
</html>
