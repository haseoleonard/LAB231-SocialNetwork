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
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <jsp:include page="WEB-INF/jspf/header.jsp"/>
    <body>
        <div class="text-center mb-3">
            <h3>Article List</h3>
            Search Article by Content<br/>
            <form class="form-inline justify-content-center mt-2" action="DispatchController" >
                <input class="form-control" type="text" name="txtSearchContent" value="${param.txtSearchContent}"/>
                <input class ="btn btn-outline-primary ml-2 mr-1" type="submit" name="btAction" value="Search Article"/>
                <input class = "btn btn-outline-success" type="submit" name="btAction" value="Create New Article"/>
            </form>
        </div>
        <c:if test="${not empty requestScope.RESULT_LIST}">
            <div class="container row m-auto">
                <c:forEach var="post" items="${requestScope.RESULT_LIST}">
                    <div class="card col-5" style="margin: 20px auto; width: 45%">
                        <c:if test="${not empty post.img}">
                            <img src="PostsImg/${post.img}" alt="" class="card-img-top">
                        </c:if>
                        <div class="card-body d-flex">
                            <div class="mt-auto">
                                <h5 class="card-title">${post.title}</h5>
                                <p class="card-text">${post.description}</p>
                                <a href="DispatchController?btAction=loadArticle&postID=${post.postID}" class="btn btn-outline-success btn-sm">Read More</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <ul class="mt-2 justify-content-center pagination pagination-lg text-center">
                <c:forEach begin="1" end="${requestScope.NUMBER_OF_PAGE}" varStatus="i"> 
                    <c:url var="paging" value="DispatchController">
                        <c:param name="hiddenPage" value="${i.count}"/>
                        <c:param name="btAction" value="Search Article"/>
                        <c:param name="txtSearchContent" value="${requestScope.txtSearchValue}"/>                        
                    </c:url>
                    <li class="page-item <c:if test="${i.count==requestScope.CURR_PAGE}">active</c:if>">                        
                        <a class="page-link" href="${paging}">
                            ${i.count}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${not empty param.txtSearchContent}">
            <c:if test="${empty requestScope.RESULT_LIST}">
                <div class="text-center text-muted mb-3">
                    <h5>No Article Matched!!</h5>
                </div>
            </c:if>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
    </body>
</html>
