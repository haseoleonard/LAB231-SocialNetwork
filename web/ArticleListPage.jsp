<%-- 
    Document   : ArticleListPage
    Created on : Sep 16, 2020, 11:41:45 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article List</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <h3>Article List</h3>
        <form action="DispatchController">
            Search Article by Content<br/>
            <input type="text" name="txtContent" value="${param.txtContent}"/>
            <input type="submit" name="btAction" value="Search Article"/>
            <input type="submit" name="btAction" value="Create New Article"/>
        </form>
        <c:if test="${not empty requestScope.RESULT_LIST}">
            <c:forEach var="post" items="${requestScope.RESULT_LIST}">
                <div style="border: yellow solid">
                    <a href="DispatchController?btAction=loadArticle&postID=${post.postID}">${post.title}</a>
                    <p>${post.description}</p>
                    <c:if test="${not empty post.img}">
                        <img src="PostsImg/${post.img}"/>                       
                    </c:if>
                </div>
            </c:forEach>
            <c:forEach begin="1" end="${requestScope.NUMBER_OF_PAGE}" varStatus="i">
                <a href="DispatchController?btAction=Search+Article&txtContent=${param.txtContent}&hiddenPage=${i.count}">
                    ${i.count}
                </a>
            </c:forEach>
        </c:if>
    </body>
</html>
