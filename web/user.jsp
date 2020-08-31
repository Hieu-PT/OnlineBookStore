<%-- 
    Document   : user
    Created on : Aug 25, 2020, 5:45:24 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuUser.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style="font-size: 25px">Search Book</h2>
                <form action="SearchBook" method="POST">
                    Title:<input type="text" name="txtSearch" style="margin: 10px 20px 10px 45px" value="${param.txtSearch}"/><br/>
                    Price:<select name="txtPrice" style="margin: 10px 20px 20px 40px">
                        <option value="all">All</option>
                        <option value="lessThan10">Less Than 10$</option>
                        <option value="10-40">10$ - 40$</option>
                        <option value="40-100">40$ - 100$</option>
                        <option value="over100">Over 100$</option>
                    </select><br/>
                    Category:<select name="txtCategory" style="margin-left: 15px">
                        <option value="Any">------------ Select ------------</option>
                        <c:forEach items="${sessionScope.LIST_CATEGORY}" var="dto">
                            <option value="${dto.toString()}">${dto.toString()}</option>
                        </c:forEach>
                        <option value="Others">Others</option>
                    </select>
                    <br/>
                    <input type="submit" value="Search" class="btn-search" style="margin: 20px 20px 20px 75px"/>
                </form>
                    <font color="red">
                    ${requestScope.ERROR}
                    </font>
                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}" var="checkList">
                        <c:forEach items="${requestScope.INFO}" var="dto" varStatus="counter">
                            <form action="AddToCart" method="POST">
                                <div class="sp">			
                                    <img src="${dto.image}" >
                                    <p class="name" style="font-size: 20px;">${dto.title}</p>
                                    <p class="name" style="font-size: 19px; color: gray">${dto.author}</p>
                                    <p class="price" style="font-size: 21px">${dto.price}$</p>
                                    <input type="hidden" value="${dto.id}" name="txtID"/>
                                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                        <input type="hidden" name="txtPrice" value="${param.txtPrice}"/>
                                        <input type="hidden" name="txtCategory" value="${param.txtCategory}"/>
                                        <input type="submit" value="ADD TO CART" class="btn" style="background-color: #00B700; width: 100%"/>
                                </div>
                            </form>
                        </c:forEach>
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
