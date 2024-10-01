<%@page import="com.entiy.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Your Address</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<c:if test="${empty userobj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%
	User us = (User) session.getAttribute("userobj");
	%>
	<%@include file="all_component/navbar.jsp"%>
	<div class="container">
		<div class="row p-3">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body">
						<h3 class="text-center text-primary">Add Address</h3>
						<p class="text-center text-danger">Please Login Again To
							Check</p>
						<c:if test="${not empty succMsg }">
							<h5 class="text-center text-success">${succMsg }</h5>
							<c:remove var="succMsg" scope="session" />
						</c:if>
						<c:if test="${not empty failedMsg }">
							<h5 class="text-center text-danger">${failedMsg }</h5>
							<c:remove var="failedMsg" scope="session" />
						</c:if>

						<form action="updateaddress" method="post">
							<input type="hidden" value="<%=us.getId()%>" name="id">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputPassword4">Address</label> <input type="text"
										class="form-control" id="inputPassword4"
										value="<%=us.getAddress()%>" name="address"
										required="required">
								</div>
								<div class="form-group col-md-6">
									<label for="inputEmail4">LandMark</label> <input type="text"
										class="form-control" id="inputEmail4"
										value="<%=us.getLandmark()%>" name="landmark"
										required="required">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label for="inputPassword4">City</label> <input type="text"
										class="form-control" id="inputPassword4"
										value="<%=us.getCity()%>" name="city" required="required">
								</div>
								<div class="form-group col-md-4">
									<label for="inputEmail4">State</label> <input type="text"
										class="form-control" id="inputEmail4"
										value="<%=us.getState()%>" name="state" required="required">
								</div>
								<div class="form-group col-md-4">
									<label for="inputPassword4">Pincode</label> <input type="text"
										class="form-control" id="inputPassword4"
										value="<%=us.getPincode()%>" name="pincode"
										required="required">
								</div>

							</div>
							<div class="text-center">
								<button type="submit"
									class="btn btn-warning btn-block btn-sm p-2">Add
									Address</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin-top: 180px;">
		<%@include file="all_component/footer.jsp"%></div>

</body>
</html>