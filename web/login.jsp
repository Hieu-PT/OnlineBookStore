
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuGuest.jsp" %>
        <body>
            <div class="main">
                <section class="col-main" style="min-height: 625px">
                    <h1 style="font-size: 35px; margin: 40px;">Login Page</h1>
                    <form action="Login" method="POST">
                        Username:<input type="text" name="txtUsername" style="margin: 20px 20px 20px 20px;"/>
                        <font color="red">
                        ${requestScope.INVALID.usernameError}
                        </font>
                        </br>
                        Password:<input type="password" name="txtPassword" style="margin: 22px;"/>
                        <font color="red">
                        ${requestScope.INVALID.passwordError}
                        </font>
                        </br>
                        <font color="red">${requestScope.ERROR}</font></br>
                        <input type="submit" value="Login" class="btn-search" style="margin: 20px 0px 0px 80px;"/></br>
                    </form>
                </section>
                
            </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
