<%-- 
    Document   : createArticlePage
    Created on : Sep 18, 2020, 11:34:58 AM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Article</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <h3>Create New Article Page</h3>
        <form action="DispatchController" method="POST" enctype="multipart/form-data">
            Title* <br/>
            <c:if test="${not empty requestScope.ERROR.titleLengthErr}">
                <font style="color: red">${requestScope.ERROR.titleLengthErr}</font><br/>
            </c:if>
            <input type="text" name="txtTitle" value="${param.txtTitle}" maxlength="200"/>
           
            <br/>Description*<br/>
            <c:if test="${not empty requestScope.ERROR.descriptionLengthErr}">
                <font style="color: red">${requestScope.ERROR.descriptionLengthErr}</font><br/>
            </c:if> 
            <textarea name="txtDescription" rows="8" cols="40" maxlength="200" style="resize: none"><c:if test="${not empty param.txtDescription}">${param.txtDescription}</c:if></textarea><br/>
            <!--<input type="text" name="txtDescription" value="${param.txtDescription}"/><br/>-->
                
            Content* <br/>
            <c:if test="${not empty requestScope.ERROR.contentLengthErr}">
                <font style="color: red">${requestScope.ERROR.contentLengthErr}</font><br/>
            </c:if>
            <textarea name="txtContent" rows="8" cols="100" style="resize: none"><c:if test="${not empty param.txtContent}">${param.txtContent}</c:if></textarea><br/>
                
            Image(Optional) <input type="file" name="uploadImg" accept="image/jpeg,image/png"/><br/>
            <c:if test="${not empty requestScope.ERROR.fileTypeIncorrectErr}">
                <font style="color: red">${requestScope.ERROR.fileTypeIncorrectErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.SUCCESS}">
                <font style="color: greenyellow">Article Create Successfully</font>
                <a href="ArticleListPage.jsp">Click here to return to article List</a><br/>
            </c:if>
            <c:if test="${not empty requestScope.FAILED}">
                <font style="color: red">Article Create Failed</font><br/>
            </c:if>
            <input type="submit" name="btAction" value="Create Article"/>
        </form>
    </body>
</html>
