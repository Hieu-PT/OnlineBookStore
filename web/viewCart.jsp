<%-- 
    Document   : viewCart
    Created on : Aug 27, 2020, 11:36:13 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuUser.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 25px">${sessionScope.NAME}'s Shopping Cart</h2>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <form action="Request" method="POST">
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                                <div class="cart">
                                    <img src="${dto.value.image}" class="cartimage" style="float: left"/>
                                    <p class="carttitle">${dto.value.title}</p>
                                    <p class="cartprice">Price: ${dto.value.price}$</p>
                                    Quantity:<select name="txtQuantity" style="margin: 0px 50px 0px 20px; width: 52px">
                                        <c:forEach items="${dto.value.listQuantity}" var="integer">
                                            <option value="${integer.intValue()}" <c:if test="${dto.value.cartQuantity == integer.intValue()}">selected=""</c:if>>${integer.intValue()}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="cartitemtotal">${dto.value.total}$</span>
                                    <input type="hidden" name="txtID" value="${dto.value.id}"/>
                                    <c:url var="deleteLink" value="Request">
                                        <c:param name="action" value="Remove"/>
                                        <c:param name="txtID" value="${dto.value.id}"/>
                                    </c:url>
                                    <a href="${deleteLink}" class="cartbtn" onclick="return confirm('Are you sure to delete?')"><br/><br/>x</a>
                                </div>
                                <c:if test="${requestScope.LIST_FAIL.contains(dto.value.id)}">
                                    <font color="red">
                                    Number of books you have selected is not available
                                    </font>
                                </c:if>
                            </c:forEach>
                            <br/>
                            <span class="discount">Discount Code: </span>
                            <input type="text" name="txtDiscount" value="${sessionScope.cart.discountCode}">
                            <input type="submit" value="Check" name="action" />
                            <font color="red">
                            ${requestScope.MESSAGE}
                            </font>
                            <br/>
                            <p class="carttotal">Total: ${requestScope.TOTAL}$</p>
                            <input type="submit" value="Save" name="action" style="margin: 0px 20px 20px 170px; width: 100px; height: 50px"/>
                            <input type="submit" value="Confirm" name="action" id="Confirm" style="margin: 0px 20px 20px 20px; width: 100px; height: 50px"/>
                        </form>
                    </c:if>
                    <c:if test="${!checkList}">
                        Your Shopping Cart is null
                    </c:if>
                </c:if>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
