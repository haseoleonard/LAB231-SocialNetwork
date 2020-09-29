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
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
<!--        <style>
            body{
                height: 100vh;
                position: relative;
            }

            body .login-form{
                position: absolute;
                top: 45%;
                left: 50%;
                transform: translate(-50%, -50%);
            }

            input{
                border-radius: 5px;
                border: 1px solid black;
            }

            #login, #create{
                border: none;
                border-radius: 6px;
                color: white;

                transition: 200ms cubic-bezier(.08,.52,.52,1) background-color, 200ms cubic-bezier(.08,.52,.52,1) box-shadow, 200ms cubic-bezier(.08,.52,.52,1) transform;
            }
            #login:hover{
                background-color: #166fe5;
            }
            #login{
                background-color: #1877f2;
                width: 100px;
            }
            #create{
                background-color: #42b72a;
                width: 130px;
            }
            #create:hover{
                background-color: #36a420;
            }
        </style>-->
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/Banner.jsp"/>        
        <section class = "login-form">
            <h1 class="mb-4" style = "text-align: center; color: #3b5998">Login</h1>
            <form action="GuestDispatchController" method="POST">
                <table>
                    <tr>
                        <td colspan="2">
                            <input style="width: 240px; padding: 6px; margin-bottom: 5px" 
                                   placeholder="Username" type="text" name="txtUsername"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input style="width: 240px; padding: 6px" placeholder="Password" 
                                   type="password" name="txtPassword"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style = "text-align: center;">
                            <input style ="margin-right: 10px; margin-top: 20px" id ="login" 
                                   type="submit" name="btAction" value="Login"/>
                            <input id="create" type="submit" name="btAction" value="Create Account"/>
                        </td>
                    </tr>
                    <c:if test="${not empty requestScope.error}">
                        <font style="color: red">${requestScope.error}</font><br/>
                    </c:if>
                </table>
            </form>
        </section>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>        
    </body>
</html>
