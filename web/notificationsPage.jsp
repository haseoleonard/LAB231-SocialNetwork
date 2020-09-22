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
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <c:if test="${not empty requestScope.NOTIFICATION_LIST}">
            <c:forEach var="notification" items="${requestScope.NOTIFICATION_LIST}">
                <a href="DispatchController?btAction=loadArticle&postID=${notification.triggerPostID}">
                    <p>
                    ${notification.triggerEmail} ${notification.eventName} your post
                    </p>
                </a>
            </c:forEach>
            <c:forEach begin="1" end="${requestScope.TOTAL_NOTIFY_PAGE}" varStatus="i">
                <a href="DispatchController?btAction=loadNotification&page=${i.count}">
                    ${i.count}
                </a>
            </c:forEach>
        </c:if>
        
    </body>
</html>
