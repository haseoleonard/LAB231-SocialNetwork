<%-- 
    Document   : ResourceNotFound.jsp
    Created on : Sep 22, 2020, 5:07:20 PM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resource Not Found</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp"/>
        <div class="text-center pt-4">
            <h3 style="color: red" >The Resources you requested cannot be found!!!</h3>
            <a href="DispatchController">Click here to return to Home Page</a>
        </div>
        <iframe src="https://discordapp.com/widget?id=702422955099160656&theme=dark" 
                width="300" height="400" allowtransparency="true" frameborder="0" class="text-center"
                sandbox="allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts">
        </iframe>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"crossorigin="anonymous"></script>
    </body>
</html>
