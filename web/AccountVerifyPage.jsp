<%-- 
    Document   : AccountVerifyPage
    Created on : Sep 26, 2020, 9:56:36 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verification Page</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
        <style>
            .newsletter-subscribe {
                color:#313437;
                background-color:#fff;
                padding:50px 0;
            }

            .newsletter-subscribe p {
                color:#7d8285;
                line-height:1.5;
            }

            .newsletter-subscribe h2 {
                font-size:24px;
                font-weight:bold;
                margin-bottom:25px;
                line-height:1.5;
                padding-top:0;
                margin-top:0;
                color:inherit;
            }

            .newsletter-subscribe .intro {
                font-size:16px;
                max-width:500px;
                margin:0 auto 25px;
            }

            .newsletter-subscribe .intro p {
                margin-bottom:35px;
            }

            .newsletter-subscribe form {
                justify-content:center;
            }

            .newsletter-subscribe form .form-control {
                background:#eff1f4;
                border:none;
                border-radius:3px;
                box-shadow:none;
                outline:none;
                color:inherit;
                text-indent:9px;
                height:45px;
                margin-right:10px;
                min-width:250px;
            }

            .newsletter-subscribe form .btn {
                padding:16px 32px;
                border:none;
                background:none;
                box-shadow:none;
                text-shadow:none;
                opacity:0.9;
                text-transform:uppercase;
                font-weight:bold;
                font-size:13px;
                letter-spacing:0.4px;
                line-height:1;
            }

            .newsletter-subscribe form .btn:hover {
                opacity:1;
            }

            .newsletter-subscribe form .btn:active {
                transform:translateY(1px);
            }

            .newsletter-subscribe form .btn-primary {
                background-color:#055ada !important;
                color:#fff;
                outline:none !important;
            }
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <c:if test="${sessionScope.LOGIN_USER.status==1}">
            <div class="newsletter-subscribe">
                <div class="container">
                    <div class="intro">
                        <h2 class="text-center">Verify Your Account</h2>
                        <p class="text-center">Enter the verification code in your email to activate</p>
                    </div>
                    <form class="form-inline" action="DispatchController" method="post">
                        <c:if test="${not empty requestScope.FAILED}">
                            <font style="color: red">
                            Wrong Authentication Code.<br/>
                            Please check your code carefully and enter again
                            </font><br/>
                        </c:if>
                        <div class="form-group">
                            <input class="form-control" type="text" maxlength="6" required="true" name="txtAuthCode" placeholder="Your Verification Code">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary" type="submit" name="btAction" value="Verify">
                                Activate 
                            </button>
                        </div>
                    </form>
                    <div class="text-center">
                        Not receiving your code ?
                        <a href="DispatchController?btAction=resendAuth">Click here to resend your code</a>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${sessionScope.LOGIN_USER.status==2}">
            <div class="alert alert-success" role="alert">
                You Have Successfully Activated your account.
                <a href="ArticleListPage.jsp" class="alert-link">Return to Home Page.</a>
            </div>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
    </body>
    <iframe src="https://discordapp.com/widget?id=702422955099160656&theme=dark" 
            width="300" height="400" allowtransparency="true" frameborder="0" class="text-center"
            sandbox="allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts">
    </iframe>
</html>
