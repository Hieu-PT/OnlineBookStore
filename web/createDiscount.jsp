<%-- 
    Document   : createDiscount
    Created on : Aug 26, 2020, 10:59:49 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Discount Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuAdmin.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style=" font-size: 30px; margin: 20px;">Create Discount</h2>
                <font color="green">
                    ${requestScope.MESSAGE}
                </font>
                <form action="CreateDiscount" method="POST">
                    ID: <input type="text" name="txtID" style="margin: 10px 20px 10px 100px;" value="${param.txtID}"/>
                    <font color="red">
                    ${requestScope.INVALID.idError}
                    </font>
                    <br/>
                    Discount Percent:<input type="number" name="txtDiscountPercent" style="margin: 10px 20px 10px 11px" value="${param.txtDiscountPercent}"/>
                    <font color="red">
                    ${requestScope.INVALID.discountPercentError}
                    </font>
                    <br/>
                    <input type="submit" value="Create" class="btn-search" style="margin:20px 20px 30px 90px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
