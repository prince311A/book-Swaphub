<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EBook: Sell Book</title>
<%@include file="all_component/allCss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<c:if test="${empty userobj }">
<c:redirect url="login.jsp"></c:redirect>
</c:if>

	<%@include file="all_component/navbar.jsp"%>
	<div class="container">
		<div class="row p-3">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="text-center text-primary p-1">Sell Old Book</h5>
							<c:if test="${not empty succMsg }">
					<h6 class="text-center text-success">${succMsg }</h6>
					<c:remove var="succMsg" scope="session"/>
					</c:if>
					
					<c:if test="${not empty failedMsg }">
					<h6 class="text-center text-danger">${failedMsg }</h6>
					<c:remove var="failedMsg" scope="session"/>
					</c:if>
						
						<form action="addoldbook" method="post"
							enctype="multipart/form-data">

							<input type="hidden" value="${userobj.email}" name="email">

							<div class="form-group">
								<label for="exampleInputEmail1">Book Name*</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" name="bname" required="required">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Author Name*</label> <input
									type="text" class="form-control" id="exampleInputPassword1"
									name="author" required="required">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Price*</label> <input
									type="number" class="form-control" id="exampleInputPassword1"
									name="price" required="required">
							</div>


							<div class="form-group">
								<label for="exampleFormControlFile1">Upload Photo</label> <input
									type="file" class="form-control-file"
									id="exampleFormControlFile1" name="bimg" required="required">
							</div>

							<div class="text-center">
								<button type="submit"
									class="btn btn-primary btn-block btn-sm p-1">Sell</button>

							</div>

						</form>

					</div>
				</div>

			</div>
		</div>
	</div>
	<div style="margin-top: 60px;">
		<%@include file="all_component/footer.jsp"%></div>
</body>
</html>