<%-- 
    Document   : createArticlePage
    Created on : Sep 18, 2020, 11:34:58 AM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Article</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <form action="DispatchController">
            Title* <input type="text" name="txtTitle"/><br/>
            Description* <input type="text" name="txtDescription"/><br/>
            Content* <input type="text" name="txtContent"/><br/>
            Image(Optional) <input type="image" name="uploadImg"/><br/>
            <input type="submit" name="btAction" value="Create Article"/>
        </form>
    </body>
</html>
