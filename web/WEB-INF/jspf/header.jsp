<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.LOGIN_USER.status==1}">
    <div class="pl-2 bg-primary">Your Account is not activated. <a class="text-white" href="AccountVerifyPage.jsp">Click Here to Verify</a></div>
</c:if> 
<jsp:include page="Banner.jsp"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='ArticleListPage.jsp'}">active</c:if>">
                <a class="pl-4 pr-4 nav-link" href="ArticleListPage.jsp">Article List <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='notificationsPage.jsp'}">active</c:if>">
                <a class="pl-4 pr-4 nav-link" href="notificationsPage.jsp">Notifications</a>
            </li>
        </ul>
            <span class="navbar-text">
                <font style="color: red">Welcome,${sessionScope.LOGIN_USER.name}</font> 
            </span>
        <span class="nav-item">
            <a class="nav-link" href="DispatchController?btAction=Logout">Logout</a>
        </span>
    </div>
</nav>

