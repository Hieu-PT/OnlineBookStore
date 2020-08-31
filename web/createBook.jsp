<%-- 
    Document   : createBook
    Created on : Aug 26, 2020, 9:55:16 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Book Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuAdmin.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style=" font-size: 30px; margin: 20px;">Create Book</h2>
                <font color="green">
                    ${requestScope.MESSAGE}
                </font>
                <form action="CreateBookController" method="POST" enctype="multipart/form-data">
                    ID: <input type="text" name="txtID" style="margin: 10px 20px 10px 75px;" value="${param.txtID}"/>
                    <font color="red">
                    ${requestScope.INVALID.idError}
                    </font>
                    <br/>
                    Title:<input type="text" name="txtTitle" style="margin: 10px 20px 10px 68px" value="${param.txtTitle}"/>
                    <font color="red">
                    ${requestScope.INVALID.titleError}
                    </font>
                    <br/>
                    Author:<input type="text" name="txtAuthor" style="margin: 10px 20px 10px 53px" value="${param.txtAuthor}"/>
                    <font color="red">
                    ${requestScope.INVALID.authorError}
                    </font>
                    <br/>
                    Price:<input type="text" name="txtPrice" style="margin: 10px 20px 10px 63px" value="${param.txtPrice}"/>
                    <font color="red">
                    ${requestScope.INVALID.priceError}
                    </font>
                    <br/>
                    Category:<select name="txtCategory" style="margin: 10px 20px 10px 38px">
                        <option value="Any" <c:if test="${requestScope.CATEGORY == 'Any'}">selected=""</c:if>>----------- Select -----------</option>
                        <c:forEach items="${sessionScope.LIST_CATEGORY}" var="dto">
                            <option value="${dto.toString()}" <c:if test="${requestScope.CATEGORY == dto.toString()}">selected=""</c:if>>${dto.toString()}</option>
                        </c:forEach>
                        <option value="Others" <c:if test="${requestScope.CATEGORY == 'Others'}">selected=""</c:if>>Others</option>
                    </select>
                    <font color="red">
                    ${requestScope.INVALID.categoryError}
                    </font>
                    <br/>
                    Quantity:<input type="number" name="txtQuantity" style="margin: 10px 20px 10px 43px" value="${param.txtQuantity}"/>
                    <font color="red">
                    ${requestScope.INVALID.quantityError}
                    </font>
                    <br/>
                    Description:
                    <font color="red">
                    ${requestScope.INVALID.descriptionError}
                    </font>
                    <br/>
                    <textarea name="txtDescription" style="margin: 10px 20px 10px 25px; width: 250px; height: 70px">${param.txtDescription}</textarea>
                    <br/>
                    Image: <input type="file" name="image" size="60" style="margin: 10px 0px 10px 45px"/>
                    <font color="red">
                    ${requestScope.INVALID.imageError}
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
