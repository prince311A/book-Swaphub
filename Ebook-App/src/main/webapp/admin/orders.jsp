<%@page import="java.util.List"%>
<%@page import="com.entiy.BookOrder"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.BookOrderDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin: All Orders</title>
<%@include file="allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<%@include file="navbar.jsp"%>
<c:if test="${empty userobj }">
	<c:redirect url="../login.jsp"/>
	</c:if>

<h3 class="text-center">Hello Admin</h3>
<table class="table table-striped ">
  <thead class="bg-primary text-white">
    <tr>
      <th scope="col">Order Id</th>
      <th scope="col">Name</th>
      <th scope="col">EMail</th>
      <th scope="col">Address</th>
       <th scope="col">Phone</th>
        <th scope="col">Book Name</th>
         <th scope="col">Author</th>
             <th scope="col">Price</th>
                 <th scope="col">Payment Type</th>
    </tr>
  </thead>
  <tbody>
  
  <%
  BookOrderDAOImpl dao = new BookOrderDAOImpl(DBConnect.getConn());
  List<BookOrder> list = dao.getAllOrderedBookAdmin();
  for(BookOrder b:list){%>
	   <tr>
      <th scope="row"><%=b.getOrderid() %></th>
      <td><%=b.getUsername() %></td>
      <td><%=b.getEmail() %></td>
      <td><%=b.getFullAddress() %></td>
       <td><%=b.getPhno() %></td>
      <td><%=b.getBookname() %></td>
       <td><%=b.getAuthor() %></td>
      <td><%=b.getPrice() %></td>
      <td><%=b.getPaymentType() %></td>
     
  
    </tr>
  <%}
  %>
   
  
  </tbody>
</table>
<div style="margin-top: 400px;">	<%@include file="footer.jsp" %></div>
</body>
</html>