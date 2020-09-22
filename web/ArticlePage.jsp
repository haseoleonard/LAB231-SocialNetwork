<%-- 
    Document   : ArticlePage
    Created on : Sep 16, 2020, 10:19:12 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.LOADED_ARTICLE.title}</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <c:if test="${not empty requestScope.LOADED_ARTICLE}">
            <div>
                <h4>${requestScope.LOADED_ARTICLE.title}</h4>

                <p>
                    ${requestScope.LOADED_ARTICLE.content}<br/>
                    <c:if test="${not empty requestScope.LOADED_ARTICLE.img}">
                        <img src="PostsImg/${requestScope.LOADED_ARTICLE.img}"/>
                    </c:if>
                </p>
                <font></font>
                ${requestScope.NUMBER_OF_LIKE} Likes, ${requestScope.NUMBER_OF_DISLIKE} Dislikes, ${NUMBER_OF_COMMENT} Comments
                <form action="DispatchController" method="POST">
                    <input type="hidden" name="postID" value="${param.postID}"/>
                    <input type="submit" name="btAction" value="Like"/>
                    <input type="submit" name="btAction" value="Dislike"/>
                    <c:if test="${requestScope.LOADED_ARTICLE.ownerEmail eq sessionScope.LOGIN_USER.email}">
                        <a href="DispatchController?btAction=Delete+Article&postID=${requestScope.LOADED_ARTICLE.postID}&ownerEmail=${requestScope.LOADED_ARTICLE.ownerEmail}">
                            Delete Post
                        </a>
                    </c:if>
                </form>
            </div>
            <div>
                <form action="DispatchController" method="POST">
                    <input type="hidden" name="postID" value="${param.postID}"/>
                    <textarea name="txtComment" rows="3" cols="50" style="resize: none"></textarea>
                    <input type="submit" name="btAction" value="Comment"/>
                </form>
                <c:if test="${not empty requestScope.LOADED_COMMENT}">
                    <c:forEach var="comment" items="${requestScope.LOADED_COMMENT}">
                        <div>
                            ${comment.commenterName}(${comment.commenterEmail}):<br/>
                            ${comment.commentContent}
                            <c:if test="${comment.commenterEmail eq sessionScope.LOGIN_USER.email}">
                                <form action="DispatchController" method="POST">
                                    <input type="hidden" name="postID" value="${requestScope.LOADED_ARTICLE.postID}"/>
                                    <input type="hidden" name="commentID" value="${comment.commentID}"/>
                                    <input type="hidden" name="commenterEmail"value="${comment.commenterEmail}"/>
                                    <input type="submit" name="btAction" value="Delete Comment"/>
                                </form>
                            </c:if>
                            <br/>
                        </div>
                    </c:forEach>
                </c:if>    
            </div>
        </c:if>
        <c:if test="${empty requestScope.LOADED_ARTICLE}">
            <h3>The Article You Request cannot be found</h3>
            <a href="ArticleListPage.jsp">Click here to return to Article List</a>
        </c:if>
    </body>
</html>
