<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Order Success</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<c:if test="${empty userobj }">
<c:redirect url="login.jsp"></c:redirect>
</c:if>
<%@include file="all_component/navbar.jsp"%>

<div class="container text-center mt-3">
<i class="fas fa-check-circle fa-5x text-success"></i>
<h1>Thank You</h1>
<h2 class="text-danger">Your Order Placed Successfully</h2>
<h5>Within 7 days Your Product Will be Delivered</h5>
<a href="index.jsp" class="btn btn-primary mt-3">Home</a>
<a href="order.jsp" class="btn btn-danger mt-3">View Order's</a>



</div>











<div  style="margin-top: 230px;">	<%@include file="all_component/footer.jsp" %></div>
</body>
</html>