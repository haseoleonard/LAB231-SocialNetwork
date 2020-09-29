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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet"  href="style.css"/>
        <style>
            td input{
                width: 100%;
            }
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/Banner.jsp"/>
        <section class="login-form">
            <h1 class="mb-4" style = "text-align: center; color: #3b5998">Create New Account</h1>
            <form action="GuestDispatchController" method="POST">
                <table>
                    <tr>
                        <td colspan="2">
                            <input style=" padding: 6px; margin-bottom: 5px" 
                                   placeholder="Email*" type="text" name="txtEmail" value="${param.txtEmail}"/><br/>
                            <c:if test="${not empty requestScope.ERROR.emailLengthErr}">
                                <font style="color: red">${requestScope.ERROR.emailLengthErr}</font><br/>
                            </c:if>
                            <c:if test="${not empty requestScope.ERROR.emailNotValidErr}">
                                <font style="color: red">${requestScope.ERROR.emailNotValidErr}</font><br/>
                            </c:if>
                            <c:if test="${not empty requestScope.ERROR.emailExistedErr}">
                                <font style="color: red">${requestScope.ERROR.emailExistedErr}</font><br/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input style=" padding: 6px; margin-bottom: 5px" 
                                   placeholder="Name*" type="text" name="txtName" value="${param.txtName}"/><br/>
                            <c:if test="${not empty requestScope.ERROR.nameLengthNotMatchErr}">
                                <font style="color: red">${requestScope.ERROR.nameLengthNotMatchErr}</font><br/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input style=" padding: 6px; margin-bottom: 5px" placeholder="Password*" type="password" name="txtPassword"/><br/>
                            <c:if test="${not empty requestScope.ERROR.passwordLengthErr}">
                                <font style="color: red">${requestScope.ERROR.passwordLengthErr}</font><br/>
                            </c:if>
                        </td>
                    </tr>
                    <tr >
                        <td colspan="2">
                            <input style=" padding: 6px; margin-bottom: 5px" 
                                   placeholder="Confirm*" type="password" name="txtConfirm"/><br/>
                            <c:if test="${not empty requestScope.ERROR.confirmNotMatchedErr}">
                                <font style="color: red">${requestScope.ERROR.confirmNotMatchedErr}</font><br/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style = "text-align: center;">
                            <input style ="margin-right: 10px; margin-top: 20px" id ="login" 
                                   type="submit" name="btAction" value="Create"/>
                            <input id="create" type="submit" name="btAction" value="Back"/><br/>

                            <c:if test="${not empty requestScope.CREATE_SUCCESS}">
                                <font style="color: green">Account Created Successfully</font>
                                <a href="login.jsp"> Click here to return to Login</a>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </form>
        </section>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
    </body>
</html>
