<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/login.js"></script>
	<title>SVM - Accedi</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>
	<div id="content">
		<form id="login_form" class="d-flex flex-column m-auto p-3 w-50 border border-darkorange bg-white justify-content-center" action="<%= response.encodeURL("Login") %>">
			<span class="text-center fs-3 my-2">Accedi</span>
			<div class="row g-3 mb-3 justify-content-center text-center">
				<div class="col-auto w-50">
					<div class="form-check form-check-inline">
				  		<input class="form-check-input" type="radio" name="loginType" value="user" id="user" checked>
				  		<label class="form-check-label" for="user">Utente</label>
					</div>
					<div class="form-check form-check-inline">
				  		<input class="form-check-input" type="radio" name="loginType" value="machine" id="machine">
				  		<label class="form-check-label" for="machine">Distributore</label>
					</div>
				</div>
			</div>
			<div class="row g-3 mb-3 justify-content-center align-items-center">
				<div class="col-auto">
					<i class="fas fa-envelope fa-lg fa-fw"></i>
				</div>
		    	<div class="col-auto w-50">
		    		<input type="email" placeholder="Email" class="form-control" name="emailId">
		    	</div>
			</div>
			<div class="row g-3 mb-2 justify-content-center align-items-center">
				<div class="col-auto">
					<i class="fas fa-lock fa-lg fa-fw"></i>
				</div>
	    		<div class="col-auto w-50">
			    	<input type="password" placeholder="Password" class="form-control" name="psw">
			    	<div class="d-flex"></div>
			    </div>
			</div>
			<div class="row g-3 my-2 justify-content-center">
				<div class="col-auto">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
			<div id="message" class="d-flex justify-content-center"></div>
		</form>	
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>