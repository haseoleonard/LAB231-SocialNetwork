<%-- 
    Document   : createAccountPage
    Created on : Sep 16, 2020, 11:50:04 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <%--<jsp:include page="WEB-INF/jspf/Banner.jsp"/>--%>
        <%@include file="WEB-INF/jspf/Banner.jspf" %>
        <h1>Create New Account</h1>
        <form action="GuestDispatchController" method="POST">
            Email* <input type="text" name="txtEmail" value="${param.txtEmail}"/><br/>
            <c:if test="${not empty requestScope.ERROR.emailLengthErr}">
                <font style="color: red">${requestScope.ERROR.emailLengthErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.ERROR.emailNotValidErr}">
                <font style="color: red">${requestScope.ERROR.emailNotValidErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.ERROR.emailExistedErr}">
                <font style="color: red">${requestScope.ERROR.emailExistedErr}</font><br/>
            </c:if>
            Name* <input type="text" name="txtName" value="${param.txtName}"/><br/>
            <c:if test="${not empty requestScope.ERROR.nameLengthNotMatchErr}">
                <font style="color: red">${requestScope.ERROR.nameLengthNotMatchErr}</font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword"/><br/>
            <c:if test="${not empty requestScope.ERROR.passwordLengthErr}">
                <font style="color: red">${requestScope.ERROR.passwordLengthErr}</font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm"/><br/>
            <c:if test="${not empty requestScope.ERROR.confirmNotMatchedErr}">
                <font style="color: red">${requestScope.ERROR.confirmNotMatchedErr}</font><br/>
            </c:if>
            <input type="submit" name="btAction" value="Create"/>
            <input type="submit" name="btAction" value="Back"/><br/>
            <c:if test="${not empty requestScope.CREATE_SUCCESS}">
                <font style="color: green">Account Created Successfully</font>
                <a href="login.jsp"> Click here to return to Login</a>
            </c:if>
        </form>
    </body>
</html>
