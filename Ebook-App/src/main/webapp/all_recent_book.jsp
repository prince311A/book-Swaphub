<%@page import="com.entiy.User"%>
<%@page import="com.entiy.BookDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.BookDaoImpl"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.DB.DBConnect"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Recent Book</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover{
background-color: #fcf7f7;
}

}

</style>
</head>
<body>
	<%
	User u = (User)session.getAttribute("userobj");
	%>
	<%@include file="all_component/navbar.jsp"%>
	<div class="contianer-fluid">
		<div class="row p-3">
			 <%
			BookDaoImpl dao2 = new BookDaoImpl(DBConnect.getConn());
		List<BookDetails> list1 =	dao2.getAllRecentBook();
		for(BookDetails b: list1){
			%>
			<div class="col-md-3">
				<div class="card crd-ho mt-2">
					<div class="card-body text-center">
						<img alt="" src="book/<%=b.getPhotoName() %>"
							style="width: 100px; height: 150px;" class="img-thumblin">
						<p><%=b.getBookName() %></p>
						<p><%=b.getAuthor() %></p>
						<p>
						<%
						if(b.getBookCategory().equals("Old")){
						%>
						Categories:<%=b.getBookCategory() %></p>
						<div class="row">
							 <a href="view_book.jsp?bid=<%=b.getBookId()%>"
								class="btn btn-success btn-sm ml-5">View Details</a> <a href=""
								class="btn btn-danger btn-sm ml-1"><%=b.getPrice() %> <i class="fa-solid fa-indian-rupee-sign"></i></a>

						</div>	
							
						<%}
						else{%>
							Categories:<%=b.getBookCategory() %></p>
							<div class="row">
							<%
							if (u == null) {
							%>
							<a href="login.jsp" class="btn btn-danger btn-sm ml-5">AddCart</a>

							<%
							} else {
							%>
							<a
								href="cartServlet?bid=<%=b.getBookId()%>&&uid=<%=u.getId()%>"
								class="btn btn-danger btn-sm ml-5">AddCart</a>
							<%
							}
							%>
								
								<a href="view_book.jsp?bid=<%=b.getBookId()%>"
									class="btn btn-success btn-sm ml-1">View Details</a>
									 <a href=""
									class="btn btn-danger btn-sm ml-1 "><%=b.getPrice() %> <i class="fa-solid fa-indian-rupee-sign"></i></a>
								 

							</div>
						<%}
						%>
						
						
					</div>
				</div>
			</div>
       <%
		}
		%>
			 
			 
			 
			
			
			</div>
	</div>
	<div  style="margin-top: 300px;">	<%@include file="all_component/footer.jsp" %></div>
</body>
</html>