<%-- 
    Document   : ServerError
    Created on : Sep 30, 2020, 12:11:51 AM
    Author     : haseo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something Wrong</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/Banner.jsp"/>
        <div class="mt-50">
            <div class="alert alert-danger" role="alert">
                Oops!!Something Wrong is happening on our server. Please Contact System Administrator for further assistance.
            </div>
            <iframe src="https://discordapp.com/widget?id=702422955099160656&theme=dark" 
                    width="300" height="400" allowtransparency="true" frameborder="0" class="text-center"
                    sandbox="allow-popups allow-popups-to-escape-sandbox allow-same-origin allow-scripts">
            </iframe>
        </div>
    </body>
</html>
