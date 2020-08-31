<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<title>User Management System</title>
	<meta charset="utf-8">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
    <header class="header">
        <div class="top" style="font-weight: bold">
            <c:if test="${(sessionScope.ROLE != 'Admin')}">
                <marquee direction="left" scrollamount="5">
                    <p>Use code NEW20 to get 20% off on total bill. Thank you for choosing us. Have a nice day!</p>
                </marquee>	
            </c:if>
        </div>
        <div class="head">
            <a href="#" class="logo">
            </a>
            <p style="position: absolute; font-size: 35px; margin-left: 450px; color: black">Online Book Store</p>
        </div>