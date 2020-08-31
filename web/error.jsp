<%-- 
    Document   : error
    Created on : Mar 8, 2020, 11:37:50 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 665px">
                <h1 style="font-size: 35px;">ERROR PAGE</h1>
                <h2>
                    <font color="red" style="font-size: 35px;">
                    ${requestScope.ERROR}
                    </font>
                </h2>
                <a href="Logout">Back to Login Page</a>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
