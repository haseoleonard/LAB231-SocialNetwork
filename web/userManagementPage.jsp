<%-- 
    Document   : userManagementPage
    Created on : Sep 16, 2020, 10:22:19 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management Page</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <h3>User Management Portal</h3>
        <form action="">
            Search by name<br/>
            <input type="text" name="txtName" value="${param.txtName}"/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
        <c:if test="${empty param.txtName}">
            a
        </c:if>
        <c:if test="${not empty param.txtName}">
            x
        </c:if>
    </body>
</html>
