<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Edit Profile</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<c:if test="${ empty userobj }">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
<%@include file="all_component/navbar.jsp"%>

<div class="container">
<div class="row p-3">
<div class="col-md-4 offset-md-4">
<div class="card">
<div class="card-body">
<h4 class="text-center text-primary">Edit Profile</h4>
<p class="text-center text-danger" >Please Login Again To Check</p>
	<c:if test="${not empty failedMsg }">
		<h5 class="text-center text-danger">${failedMsg }</h5>
		<c:remove var="failedMsg" scope="session" />
	</c:if>
	<c:if test="${not empty succMsg }">
		<h5 class="text-center text-success">${succMsg }</h5>
		<c:remove var="succMsg" scope="session" />
	</c:if>
	<form action="updateprofile" method="post">
	<input type="hidden" value="${userobj.id}" name="id">
							<div class="form-group">
								<label for="exampleInputEmail1">Enter Full Name*</label> <input
									type="text" class="form-control" id="name"
									aria-describedby="emailHelp" placeholder="Enter Name"
									required="required" name="fname" value="${userobj.name}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address*</label> <input
									type="email" class="form-control" id="email"
									aria-describedby="emailHelp" placeholder="Enter email"
									required="required" name="email" value="${userobj.email}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Phone*</label> <input
									type="number" class="form-control" id="phone"
									aria-describedby="emailHelp" placeholder="Enter Phone Number"
									required="required" name="phno"value="${userobj.phno}">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="password"
									placeholder="Enter Password" required="required"
									name="password">
							</div>
							<div class="text-center p-2">
								<button type="submit" class="btn btn-primary btn-block btn-sm p-2">Update</button>
							</div>
						</form>

</div>

</div>
</div>

</div>
</div>
<div  style="margin-top: 3px;">	<%@include file="all_component/footer.jsp" %></div>
</body>
</html>