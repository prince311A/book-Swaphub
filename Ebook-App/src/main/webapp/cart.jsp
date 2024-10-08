<%@page import="java.util.List"%>
<%@page import="com.entiy.User"%>
<%@page import="com.entiy.Cart"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.dao.CartDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Your Cart</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<c:if test="${ empty userobj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>

	<c:if test="${not empty succMsg }">
		<div class="alert alert-success text-center" role="alert">${succMsg}</div>
		<c:remove var="succMsg" scope="session" />
	</c:if>


	<c:if test="${not empty failed }">
		<div class="alert alert-danger  text-center" role="alert">${failed}</div>
		<c:remove var="failed" scope="session" />
	</c:if>
	<c:if test="${not empty msg }">
		<div class="alert alert-danger text-center" role="alert">${msg}</div>
		<c:remove var="msg" scope="session" />
	</c:if>
	<c:if test="${not empty additem }">
		<div class="alert alert-danger text-center" role="alert">${additem}</div>
		<c:remove var="additem" scope="session" />
	</c:if>


	<%@include file="all_component/navbar.jsp"%>
	<div class="container">
		<div class="row p-2">
			<div class="col-md-6">

				<div class="card bg-white">

					<div class="card-body ">
						<h3 class="text-center text-success">Your Selected Item's</h3>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Book Name</th>
									<th scope="col">Author</th>
									<th scope="col">Price</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<%
								User u = (User) session.getAttribute("userobj");
								CartDAOImpl dao = new CartDAOImpl(DBConnect.getConn());
								List<Cart> cart = dao.getBookByUser(u.getId());
								Double totalPrice = 0.00;
								for (Cart c : cart) {
									totalPrice = c.getTotalprice();
								%>
								<tr>
									<th scope="row"><%=c.getBookname()%></th>
									<td><%=c.getAuthor()%></td>
									<td><%=c.getPrice()%></td>
									<td><a
										href="removebook?bid=<%=c.getBid()%>&&uid=<%=c.getUid()%>&&cid=<%=c.getCid()%>"
										class="btn btn-sm btn-danger">Remove</a></td>
								</tr>
								<%
								}
								%>
								<tr>
									<td>Total Price</td>
									<td></td>
									<td></td>
									<td><%=totalPrice%></td>

								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center text-success">Detail's For Order</h3>
						<form action="order" method="post">
							<input type="hidden" value="<%=u.getId()%>" name="userid">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Name</label> <input type="text"
										class="form-control" id="inputEmail4" value="<%=u.getName()%>"
										name="name" required="required">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Email</label> <input type="email"
										class="form-control" id="inputPassword4"
										value="<%=u.getEmail()%>" name="email" required="required"
										readonly="readonly">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Phone Number</label> <input
										type="number" class="form-control" id="inputEmail4"
										value="<%=u.getPhno()%>" name="phno" required="required">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Address</label> <input type="text"
										class="form-control" id="inputPassword4" value=""
										name="address" required="required">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">LandMark</label> <input type="text"
										class="form-control" id="inputEmail4" value="" name="landmark"
										required="required">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">City</label> <input type="text"
										class="form-control" id="inputPassword4" value="" name="city"
										required="required">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">State</label> <input type="text"
										class="form-control" id="inputEmail4" value="" name="state"
										required="required">
								</div>
								<div class="form-group col-md-6">
									<label for="inputPassword4">Pincode</label> <input type="text"
										class="form-control" id="inputPassword4" value=""
										name="pincode" required="required">
								</div>
							</div>
							<div class="form-group">
								<label>Payment Mode</label> <select class="form-control"
									name="paymenttype">
									<option value="noselect">--Select--</option>
									<option value="COD">Cash On Delivery</option>
								</select>
							</div>
							<div class="text-center">
								<button class="btn btn-warning text-black">
									<i class="fa-solid fa-money-bill"></i> Order Now
								</button>
								<a href="index.jsp" class="btn btn-success"><i
									class="fa-solid fa-bag-shopping"></i> Continue Shopping</a>
							</div>


						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div style="">
		<%@include file="all_component/footer.jsp"%></div>
</body>
</html>