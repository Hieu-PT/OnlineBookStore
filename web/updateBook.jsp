<%-- 
    Document   : updateBook
    Created on : Aug 26, 2020, 11:31:10 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Book Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%@include file="menuAdmin.jsp" %>
        <div class="main">
            <section class="col-main" style="min-height: 625px">
                <h2 style=" font-size: 30px; margin: 20px;">Update Book</h2>
                <font color="green">
                    ${requestScope.MESSAGE}
                </font>
                <form action="UpdateBookController" method="POST" enctype="multipart/form-data">
                    ID: <input type="text" name="txtID" readonly="" style="margin: 10px 20px 10px 75px;" value="${requestScope.DTO.id}"/>
                    <br/>
                    Title:<input type="text" name="txtTitle" style="margin: 10px 20px 10px 68px" value="${requestScope.DTO.title}"/>
                    <font color="red">
                    ${requestScope.INVALID.titleError}
                    </font>
                    <br/>
                    Author:<input type="text" name="txtAuthor" style="margin: 10px 20px 10px 53px" value="${requestScope.DTO.author}"/>
                    <font color="red">
                    ${requestScope.INVALID.authorError}
                    </font>
                    <br/>
                    Price:<input type="text" name="txtPrice" style="margin: 10px 20px 10px 63px" value="${requestScope.DTO.price}"/>
                    <font color="red">
                    ${requestScope.INVALID.priceError}
                    </font>
                    <br/>
                    Category:<select name="txtCategory" style="margin: 10px 20px 10px 38px">
                        <option value="Any" <c:if test="${requestScope.DTO.category == 'Any'}">selected=""</c:if>>----------- Select -----------</option>
                        <c:forEach items="${sessionScope.LIST_CATEGORY}" var="dto">
                            <option value="${dto.toString()}" <c:if test="${requestScope.DTO.category == dto.toString()}">selected=""</c:if>>${dto.toString()}</option>
                        </c:forEach>
                        <option value="Others" <c:if test="${requestScope.DTO.category == 'Others'}">selected=""</c:if>>Others</option>
                    </select>
                    <font color="red">
                    ${requestScope.INVALID.categoryError}
                    </font>
                    <br/>
                    Import Date:<input type="text" name="txtImportDate" readonly="" style="margin: 10px 20px 10px 22px" value="${requestScope.DTO.importDate}"/>
                    <br/>
                    Quantity:<input type="number" name="txtQuantity" style="margin: 10px 20px 10px 43px" value="${requestScope.DTO.quantity}"/>
                    <font color="red">
                    ${requestScope.INVALID.quantityError}
                    </font>
                    <br/>
                    Status:<select name="status" id="status" style="margin: 10px 20px 20px 57px">
                        <option value="Active" <c:if test="${requestScope.DTO.status == 'Active'}">selected=""</c:if>>Active</option>
                        <option value="InActive" <c:if test="${requestScope.DTO.status == 'InActive'}">selected=""</c:if>>InActive</option>
                    </select>
                    <br/>
                    Description:
                    <font color="red">
                    ${requestScope.INVALID.descriptionError}
                    </font>
                    <br/>
                    <textarea name="txtDescription" style="margin: 10px 20px 10px 25px; width: 250px; height: 70px">${requestScope.DTO.description}</textarea>
                    <br/>
                    Image: <input type="file" name="image" size="60" style="margin: 10px 20px 10px 45px"/>
                    <font color="red">
                    ${requestScope.INVALID.imageError}
                    </font>
                    <br/>
                    <img src="${requestScope.DTO.image}" style="width: 160px; height: 160px; margin: 8px 20px 8px 100px"/>
                    <br/>
                    <input type="hidden" name="txtImage" value="${requestScope.DTO.image}"/>
                    <input type="submit" value="Update" class="btn-search" style="margin:20px 20px 30px 90px"/>
                </form>
            </section>
            <%@include file="aside.jsp" %>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
