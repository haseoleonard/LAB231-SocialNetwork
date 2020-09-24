<%-- 
    Document   : login
    Created on : Sep 16, 2020, 11:09:32 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="margin: 0; padding: 0">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/Banner.jsp"/>
        <section class = "container">
            <h1 style = "text-align: center; color: #3b5998">Login</h1>
            <form action="GuestDispatchController" method="POST">
                <table>
                    <tr>
                        <td colspan="2"><input style="width: 240px; padding: 6px; margin-bottom: 5px" placeholder="Username" type="text" name="txtUsername"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input style="width: 240px; padding: 6px" placeholder="Password" type="password" name="txtPassword"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" style = "text-align: center;"><input style ="margin-right: 10px; margin-top: 20px" type="submit" name="btAction" value="Login"/><input type="submit" name="btAction" value="Create Account"/></td>
                    </tr>
                    <c:if test="${not empty requestScope.error}">
                        <font style="color: red">${requestScope.error}</font><br/>
                    </c:if>
                </table>
            </form>
        </section>
        <style>
            body{
                position: relatvie;
            }

            body .container{
                position: absolute;
                top: 35%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
            
            input{
                border-radius: 5px;
                border: 1px solid black;
            }
        </style>
    </body>
</html>
