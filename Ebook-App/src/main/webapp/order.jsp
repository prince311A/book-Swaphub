<%@page import="com.entiy.BookOrder"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.BookOrderDAOImpl"%>
<%@page import="com.entiy.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: My Order</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<c:if test="${empty userobj }">
<c:redirect url="login.jsp"></c:redirect>
</c:if>
<%@include file="all_component/navbar.jsp"%>
<div class="container p-1">
<h3 class="text-center text-primary">Your Order's</h3>
<table class="table table-striped mt-3">
  <thead class="bg-primary text-white">
    <tr>
      <th scope="col">Order ID</th>
      <th scope="col">Name</th>
      <th scope="col">Book Name</th>
      <th scope="col">Author</th>
       <th scope="col">Price</th>
        <th scope="col">Payment Type</th>
    </tr>
  </thead>
  <tbody>
  
  <%
 User us =(User) session.getAttribute("userobj");
  BookOrderDAOImpl dao = new BookOrderDAOImpl(DBConnect.getConn());
  List<BookOrder> list = dao.getOrderedBook(us.getEmail());
  for(BookOrder b:list){%>
	   <tr>
      <th scope="row"><%=b.getOrderid() %></th>
      <td><%=b.getUsername() %></td>
      <td><%=b.getBookname() %></td>
      <td><%=b.getAuthor() %></td>
       <td><%=b.getPrice() %></td>
        <td><%=b.getPaymentType() %></td>
    </tr>
  <% }
  %>
  
   
  
   
  </tbody>
</table>
</div>
<div  style="margin-top: 370px;">	<%@include file="all_component/footer.jsp" %></div>
</body>
</html>