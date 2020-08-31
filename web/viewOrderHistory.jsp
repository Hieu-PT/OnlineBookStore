<%-- 
    Document   : viewOrderHistory
    Created on : Aug 29, 2020, 3:15:26 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuUser.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 25px">Order History</h2>
                <form action="SearchOrderHistory" method="POST">
                    Search:<input type="text" name="txtSearch" style="margin: 10px 20px 10px 35px"/><br/>
                    From:<input type="date" name="txtFrom" style="margin: 10px 20px 10px 46px"><br/>
                    To: <input type="date" name="txtTo" style="margin: 10px 20px 10px 59px"><br/>
                    <input type="submit" value="Search" class="btn-search" name="action" style="margin: 10px 20px 10px 60px;"/>
                </form>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <br/>
                        <table border="5" style="margin: 30px; border: solid black;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Order ID</th>
                                    <th>Customer</th>
                                    <th>Total</th>
                                    <th>Order Date</th>
                                    <th>Payment</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.orderID}</td>
                                        <td>${dto.customerID}</td>
                                        <td>${dto.total}</td>
                                        <td>${dto.orderDate}</td>
                                        <td>
                                            <c:if test="${dto.status == 'Unpaid'}" var="checkPayment">
                                                <form action="PayOnline" method="POST">
                                                    <input type="hidden" name="id" value="${dto.orderID}"/>
                                                    <input type="submit" value="Pay Online" name="action" />
                                                </form>
                                            </c:if>
                                            <c:if test="${!checkPayment}">
                                                ${dto.status}
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${!checkList}">
                        No record found
                    </c:if>
                </c:if>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
