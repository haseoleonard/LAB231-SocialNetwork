<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <header>
            <h1>Some kind of Social Network</h1>
        </header>
        <nav>
            <font style="color: red">Welcome,${sessionScope.LOGIN_USER.name}</font> 
            <a href="DispatchController?btAction=Logout">Log Out</a><br/>
            <a href="ArticleListPage.jsp">Article List</a> || 
            <a href="notificationsPage.jsp">Notifications</a>
            <br/>
        </nav>
