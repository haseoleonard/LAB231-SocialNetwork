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
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <h3 class="text-center">Create New Article Page</h3>
        <c:if test="${not empty requestScope.SUCCESS}">
            <div class="alert alert-success" role="alert">
                Article created Successfully.<a href="ArticleListPage.jsp" class="alert-link">Click here to return to article List</a>.
            </div>
        </c:if>
        <form style="width: 900px; margin: 0 auto" action="DispatchController" method="POST" enctype="multipart/form-data">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Title*</span>
                </div>
                <input type="text" name="txtTitle" value="${param.txtTitle}" maxlength="200" class="form-control" aria-label="Title*"/>
            </div>
            <c:if test="${not empty requestScope.ERROR.titleLengthErr}">
                <font style="color: red">${requestScope.ERROR.titleLengthErr}</font><br/>
            </c:if>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Description*</span>
                </div>
                <input type="text" name="txtDescription" maxlength="400" value="${param.txtDescription}" class="form-control" aria-label="Description*"/>
            </div>
            <c:if test="${not empty requestScope.ERROR.descriptionLengthErr}">
                <font style="color: red">${requestScope.ERROR.descriptionLengthErr}</font><br/>
            </c:if>
            <div class="">
                <div class="input-group-prepend">
                    <span class="input-group-text">Content*</span>
                </div>
                <textarea name="txtContent" class="form-control" rows="8" cols="100" style="resize: none"><c:if test="${not empty param.txtContent}">${param.txtContent}</c:if></textarea>
                <!--<input type="text" name="txtDescription" maxlength="400" value="${param.txtDescription}" class="form-control" aria-label="Description*"/>-->
            </div>

            <c:if test="${not empty requestScope.ERROR.contentLengthErr}">
                <font style="color: red">${requestScope.ERROR.contentLengthErr}</font><br/>
            </c:if>


            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupFileAddon01">Image(Optional)</span>
                </div>
                <div class="custom-file">
                    <input type="file" name="uploadImg" accept="image/jpeg,image/png" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                </div>
            </div>
            <c:if test="${not empty requestScope.ERROR.fileTypeIncorrectErr}">
                <font style="color: red">${requestScope.ERROR.fileTypeIncorrectErr}</font><br/>
            </c:if>
            <c:if test="${not empty requestScope.FAILED}">
                <font style="color: red">Article Create Failed</font><br/>
            </c:if>
                <input class="btn btn-outline-success" type="submit" name="btAction" value="Create Article"/>
        </form>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
        <script>
            $('#inputGroupFile01').on('change', function () {
                //get the file name
                var fileName = $(this).val();
                fileName = fileName.replace('C:\\fakepath\\', "");
                //replace the "Choose a file" label
                $(this).next('.custom-file-label').html(fileName);
            })
        </script>
    </body>
</html>
