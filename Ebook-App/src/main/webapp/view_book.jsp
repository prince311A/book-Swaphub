<%@page import="com.entiy.User"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.BookDaoImpl"%>
<%@page import="com.entiy.BookDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: View Details</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover{
background-color: #fcf7f7;
}

}

</style>

</head>
<body style="background-color: #f0f1f2;">
<%
	User u = (User) session.getAttribute("userobj");
	%>
	
	
	<%@include file="all_component/navbar.jsp"%>
	<%
	int id = Integer.parseInt( request.getParameter("bid"));
	BookDaoImpl dao2 = new BookDaoImpl(DBConnect.getConn());
	BookDetails b = dao2.getBookById(id);
	
	%>
	
	<div class="container p-3">
		<div class="row">
			<div class="col-md-6 text-center p-5 border bg-white">
				<img src="book/<%=b.getPhotoName() %>" style="height: 150px; width: 150px;"><br>
				<h4 class="mt-3">Book Name: <span class="text-success"><%=b.getBookName() %></span></h4>
				<h4 >Author Name: <span class="text-success"><%=b.getAuthor() %></span></h4>
				<h4>Category: <span class="text-success"><%=b.getBookCategory() %></span></h4>
			</div>
			<div class="col-md-6 text-center p-5 border bg-white">
			
				<h3><%=b.getBookName() %></h3>
				<%if(b.getBookCategory().equals("Old")){%>
					<h5 class="text-primary">Contact To Seller</h5>
					<h5 class="text-primary"><i class="fa-regular fa-envelope"></i> EMail: <%=b.getEmail() %></h5>
					
					
				<%}
				%>
				
				<div class="row">
					<div class="col-md-4 text-center text-danger p-2">
						<i class="fa-solid fa-money-bill-wave fa-2x"></i>
						<p>Cash On Delivery</p>

					</div>
					<div class="col-md-4 text-center text-danger p-2">
						<i class="fa-solid fa-rotate-left fa-2x"></i>
						<p>Return Available</p>
					</div>
					<div class="col-md-4 text-center text-danger p-2">
						<i class="fa-solid fa-truck-moving fa-2x"></i>
						<p>Free Shipping</p>
					</div>


				</div>
				<%if(b.getBookCategory().equals("Old")){%>
					<div class="text-center p-3">
				<a href="index.jsp"class="btn btn-success"><i class="fa-solid fa-cart-shopping"></i>Continue Shopping</a>
				<a href=""class="btn btn-danger"><%=b.getPrice() %> <i class="fa-solid fa-indian-rupee-sign"></i></a>
				</div>
					
				<%}else{%>
					<div class="text-center p-3">
						<%
							if (u == null) {
							%>
							<a href="login.jsp"
						class="btn btn-primary"><i class="fa-solid fa-cart-shopping"></i>
						Add Cart</a>

							<%
							} else {
							%>
						<a href="cartServlet?bid=<%=b.getBookId()%>&&uid=<%=u.getId()%>"
						class="btn btn-primary"><i class="fa-solid fa-cart-shopping"></i>
						Add Cart</a>
							<%
							}
							%>
						 <a href=""class="btn btn-danger"><%=b.getPrice() %> <i class="fa-solid fa-indian-rupee-sign"></i></a>
				
				</div>
				
					
				<%}
				
				%>
				
							</div>
		</div>
	</div>
	<div  style="margin-top: 140px;">	<%@include file="all_component/footer.jsp" %></div>
</body>
</html>