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
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .fa.fa-thumbs-up,.fa.fa-thumbs-down{
                color: black;
            }

            .fa.fa-thumbs-up.active{
                color: blue;
            }
            .fa.fa-thumbs-down.active{
                color: red;
            }
            ul.timeline {
                list-style-type: none;
                position: relative;
                padding-left: 1.5rem;
            }

            /* Timeline vertical line */
            ul.timeline:before {
                content: ' ';
                background: #0275d8;
                display: inline-block;
                position: absolute;
                left: 16px;
                width: 4px;
                height: 100%;
                z-index: 400;
                border-radius: 1rem;
            }

            li.timeline-item {
                margin: 20px 0;
            }

            /* Timeline item arrow */
            .timeline-arrow {
                border-top: 0.5rem solid transparent;
                border-right: 0.5rem solid #fff;
                border-bottom: 0.5rem solid transparent;
                display: block;
                position: absolute;
                left: 2rem;
            }

            /* Timeline item circle marker */
            li.timeline-item::before {
                content: ' ';
                background: #ddd;
                display: inline-block;
                position: absolute;
                border-radius: 50%;
                border: 3px solid #0275d8;
                left: 11px;
                width: 14px;
                height: 14px;
                z-index: 400;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            }
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <c:if test="${not empty requestScope.LOADED_ARTICLE}">
            <div class="w-50 m-auto">
                <div class="card" style="width: 45rem;">
                    <div class="card-body">
                        <div class="pl-3 pr-3 row justify-content-between">
                            <h3 class="card-title">${requestScope.LOADED_ARTICLE.title}</h3>
                            <p class="text-muted">${requestScope.LOADED_ARTICLE.ownerEmail}</p>
                        </div>
                        <h6 class="pl-3 card-subtitle mb-2 text-muted">${requestScope.LOADED_ARTICLE.submitTime}</h6>
                        <p class="mt-5 card-text">${requestScope.LOADED_ARTICLE.content}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <c:if test="${not empty requestScope.LOADED_ARTICLE.img}">
                            <li class="list-group-item" style="border-bottom: none!important"><img src="PostsImg/${requestScope.LOADED_ARTICLE.img}" class="card-img-top" alt="..."></li>
                            </c:if>
                        <p class=" pr-2 text-muted text-right" >
                            ${requestScope.NUMBER_OF_LIKE} Likes, ${requestScope.NUMBER_OF_DISLIKE} Dislikes, ${NUMBER_OF_COMMENT} Comments
                        </p>     
                    </ul>
                    <div class="card-body row justify-content-between pl-4 pr-4 pt-2 pb-2">
                        <form id="React" action="DispatchController" method="POST">
                            <input type="hidden" name="postID" value="${param.postID}"/>
                            <button form="React" class="btn btn-outline-primary" type="submit" name="btAction" value="Like"> 
                                <i class="pl-2 pr-2 fa fa-thumbs-up <c:if test="${requestScope.LAST_REACTION=='Like'}">active</c:if>" style="font-size:24px">Like</i>
                                </button> 
                                <button form="React" class="btn btn-outline-danger" type="submit" name="btAction" value="Dislike"> 
                                    <i class="fa fa-thumbs-down <c:if test="${requestScope.LAST_REACTION=='Dislike'}">active</c:if>" style="font-size:24px">Dislike</i>
                                </button>
                            </form>
                        <c:if test="${requestScope.LOADED_ARTICLE.ownerEmail eq sessionScope.LOGIN_USER.email}">
                            <a class="btn btn-danger" href="DispatchController?btAction=Delete+Article&postID=${requestScope.LOADED_ARTICLE.postID}&ownerEmail=${requestScope.LOADED_ARTICLE.ownerEmail}" onclick="return confirm('Are you sure to delete this Article ?')">
                                Delete Post
                            </a>
                        </c:if>
                    </div>
                    <form action="DispatchController" method="POST">
                        <input type="hidden" name="postID" value="${param.postID}"/>
                        <textarea class="form-control" name="txtComment" rows="3" cols="50" style="resize: none"></textarea>
                        <div class="text-right pr-2">
                            <input type="submit" class="btn btn-outline-secondary" name="btAction" value="Comment"/>
                        </div>
                    </form>
                    <c:if test="${not empty requestScope.LOADED_COMMENT}">
                        <div class="row">
                            <div class="col-lg-10 mx-auto">
                                <ul class="timeline">
                                    <c:forEach var="comment" items="${requestScope.LOADED_COMMENT}">
                                        <li class="timeline-item bg-white rounded ml-3 p-4 shadow">
                                            <div class="timeline-arrow"></div>
                                            <div class="pl-3 pr-3 row justify-content-between">
                                            <h2 class="h5 mb-0">${comment.commenterName}(${comment.commenterEmail})</h2>
                                            <c:if test="${comment.commenterEmail eq sessionScope.LOGIN_USER.email}">
                                                <form action="DispatchController" method="POST">
                                                    <input type="hidden" name="postID" value="${requestScope.LOADED_ARTICLE.postID}"/>
                                                    <input type="hidden" name="commentID" value="${comment.commentID}"/>
                                                    <input type="hidden" name="commenterEmail"value="${comment.commenterEmail}"/>
                                                    <input type="submit" class="btn btn-light" name="btAction" value="Delete Comment" onclick="return confirm('Are you sure to delete this Comment ?')"/>
                                                </form>
                                            </c:if>
                                            </div>
                                            <span class="small text-gray"><i class="fa fa-clock-o mr-1"></i>${comment.time}</span>
                                            <p class="text-small mt-2 font-weight-light">${comment.commentContent}</p>
                                            
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>                                
            </div>
        </c:if>
        <c:if test="${empty requestScope.LOADED_ARTICLE}">
            <div class="container text-center" style="padding-top: 5%">
                <h3 class="text-center">The Article You Request cannot be found</h3>
                <a href="ArticleListPage.jsp">Click here to return to Article List</a>
            </div>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
    </body>
</html>
