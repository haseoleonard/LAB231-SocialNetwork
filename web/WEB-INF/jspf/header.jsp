<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <jsp:include page="Banner.jsp"/>
        <nav>
            <font style="color: red">Welcome,${sessionScope.LOGIN_USER.name}</font> 
            <a href="DispatchController?btAction=Logout">Log Out</a><br/>
            <a href="ArticleListPage.jsp">Article List</a> || 
            <a href="DispatchController?btAction=loadNotification">Notifications</a>
            <br/>
        </nav>
