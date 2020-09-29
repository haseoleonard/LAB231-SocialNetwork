<%-- 
    Document   : notificationsPage
    Created on : Sep 16, 2020, 10:09:34 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications</title>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
        <style>

            .timeline {
                border-left: 3px solid #727cf5;
                border-bottom-right-radius: 4px;
                border-top-right-radius: 4px;
                background: rgba(114, 124, 245, 0.09);
                margin: 0 auto;
                letter-spacing: 0.2px;
                position: relative;
                line-height: 1.4em;
                font-size: 1.03em;
                padding: 50px;
                list-style: none;
                text-align: left;
                max-width: 40%;
            }

            @media (max-width: 767px) {
                .timeline {
                    max-width: 98%;
                    padding: 25px;
                }
            }

            .timeline h1 {
                font-weight: 300;
                font-size: 1.4em;
            }

            .timeline h2,
            .timeline h3 {
                font-weight: 600;
                font-size: 1rem;
                margin-bottom: 10px;
            }

            .timeline .event {
                border-bottom: 1px dashed #e8ebf1;
                padding-bottom: 25px;
                margin-bottom: 25px;
                position: relative;
            }

            @media (max-width: 767px) {
                .timeline .event {
                    padding-top: 30px;
                }
            }

            .timeline .event:last-of-type {
                padding-bottom: 0;
                margin-bottom: 0;
                border: none;
            }

            .timeline .event:before,
            .timeline .event:after {
                position: absolute;
                display: block;
                top: 0;
            }

            .timeline .event:before {
                left: -207px;
                content: attr(data-date);
                text-align: right;
                font-weight: 100;
                font-size: 0.9em;
                min-width: 120px;
            }

            @media (max-width: 767px) {
                .timeline .event:before {
                    left: 0px;
                    text-align: left;
                }
            }

            .timeline .event:after {
                -webkit-box-shadow: 0 0 0 3px #727cf5;
                box-shadow: 0 0 0 3px #727cf5;
                left: -55.8px;
                background: #fff;
                border-radius: 50%;
                height: 9px;
                width: 9px;
                content: "";
                top: 5px;
            }

            @media (max-width: 767px) {
                .timeline .event:after {
                    left: -31.8px;
                }
            }

            .rtl .timeline {
                border-left: 0;
                text-align: right;
                border-bottom-right-radius: 0;
                border-top-right-radius: 0;
                border-bottom-left-radius: 4px;
                border-top-left-radius: 4px;
                border-right: 3px solid #727cf5;
            }

            .rtl .timeline .event::before {
                left: 0;
                right: -170px;
            }

            .rtl .timeline .event::after {
                left: 0;
                right: -55.8px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <c:if test="${not empty requestScope.NOTIFICATION_LIST}">
            <div class="container mt-2">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h6 class="card-title">Notifications</h6>
                                <div id="content">
                                    <ul class="timeline">
                                        <c:forEach var="notification" items="${requestScope.NOTIFICATION_LIST}">
                                            <li class="event" data-date="${notification.time}">
                                                <h3>Some Kind Of Social Network</h3>
                                                <p>
                                                    <a href="DispatchController?btAction=loadArticle&postID=${notification.triggerPostID}">
                                                        ${notification.triggerEmail} ${notification.eventName} on your post
                                                    </a>
                                                </p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <ul class="mt-2 justify-content-center pagination pagination-lg text-center">
                    <c:forEach begin="1" end="${requestScope.TOTAL_NOTIFY_PAGE}" varStatus="i">
                        <li class="page-item <c:if test="${i.count==requestScope.CURR_PAGE}">active</c:if>">
                            <a class="page-link" href="DispatchController?btAction=loadNotification&page=${i.count}">
                                ${i.count}
                            </a>
                        </li>

                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${empty requestScope.NOTIFICATION_LIST}">
                <h3 class="text-muted">You Currently don't have any notifications</h3>
            </c:if>
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script> 

    </body>
</html>
