<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.giuseppetripiciano.project.model.User"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/profile.js"></script>
	<title>SVM - Dati personali</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>
	
	<%	
			User accountBean = (User) session.getAttribute("accountBean");
	
			String firstname = accountBean.getFirstname();
			String lastname = accountBean.getLastname();
			String email = accountBean.getEmail();
	%>
	
	<div id="content">
		<div class="d-flex flex-column flex-grow-1 mx-auto w-50 bg-white border-start border-end border-darkorange justify-content-center">
			<div class="text-center fs-3 mt-3 mb-auto">Dati personali</div>
			<div class="text-center mb-2"><i class="fas fa-user fa-lg fa-fw"></i></div>
			<div class="row g-3 mb-2 justify-content-center">
			  	<div class="col-auto w-50">
	        		<input type="text" placeholder="<%= firstname %>" class="form-control text-center" name="firstname" readonly disabled/>
	        	</div>
	        </div>
	        <div class="row g-3 mb-2 justify-content-center">
	        	<div class="col-auto w-50">
	        		<input type="text" placeholder="<%= lastname %>" class="form-control text-center" name="lastname" readonly disabled/>
	        	</div>
	        </div>
	        <div class="text-center mb-2"><i class="fas fa-envelope fa-lg fa-fw"></i></div>
			<div class="row g-3 mb-2 justify-content-center">
		    	<div class="col-auto w-50">
		    		<input type="email" placeholder="<%= email %>" class="form-control text-center" name="email" readonly disabled>
		    	</div>
			</div>
			<div class="row g-3 m-auto justify-content-center">
				<form id="changeEmail_form" class="col-auto d-flex flex-column flex-grow-1 m-auto px-5 w-50 justify-content-center" action="<%= response.encodeURL("Profile") %>">
					<div class="text-center fs-5 mt-3 mb-3">Modifica email</div>
					<div class="row g-3 mb-2 justify-content-center">
					  	<div class="col-auto w-100">
			        		<input type="password" placeholder="Password" class="form-control" name="psw"/>
			        		<div class="d-flex"></div>
			        	</div>
		        	</div>
		       		 <div class="row g-3 mb-2 justify-content-center">
			        	<div class="col-auto w-100">
			        		<input type="email" placeholder="Nuova email" class="form-control" name="new_email"/>
			        	</div>
		       	 	</div>
		       	 	<div class="row g-3 mt-auto mb-3 justify-content-center">
						<div class="col-auto">
							<button type="submit" class="btn btn-primary">Modifica email</button>
						</div>
					</div>
					<div id="message1" class="d-flex justify-content-center"></div>
				</form>
				<form id="changePassword_form" class="col-auto d-flex flex-column flex-grow-1 m-auto px-5 w-50 justify-content-center" action="<%= response.encodeURL("Profile") %>">
					<div class="text-center fs-5 mt-3 mb-3">Modifica password</div>
					<div class="row g-3 mb-2 justify-content-center">
					  	<div class="col-auto w-100">
			        		<input type="password" placeholder="Vecchia password" class="form-control" name="old_psw"/>
			        		<div class="d-flex"></div>
			        	</div>
		        	</div>
		       		 <div class="row g-3 mb-2 justify-content-center">
			        	<div class="col-auto w-100">
			        		<input type="password" placeholder="Nuova password" class="form-control" name="new_psw"/>
			        		<div class="d-flex"></div>
			        	</div>
		       	 	</div>
		       	 	<div class="row g-3 mb-2 justify-content-center">
			        	<div class="col-auto w-100">
			        		<input type="password" placeholder="Conferma nuova password" class="form-control" name="renew_psw"/>
			        	</div>
		       	 	</div>
		       	 	<div class="row g-3 mt-auto mb-3 justify-content-center">
						<div class="col-auto">
							<button type="submit" class="btn btn-primary">Modifica password</button>
						</div>
					</div>
					<div id="message2" class="d-flex justify-content-center"></div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>