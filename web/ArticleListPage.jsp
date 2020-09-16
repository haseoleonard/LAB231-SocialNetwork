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
        <form action="">
            Search Article by Content<br/>
            <input type="text" name="txtContent" value="${param.txtContent}"/>
            <input type="submit" name="btAction" value="Search Article"/>
        </form>
        <c:set var="" value=""/>
        <c:if test="">
            
        </c:if>
    </body>
</html>
