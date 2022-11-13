<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/register.js"></script>
	<title>SVM - Registrati</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>
	<%
		boolean isLogged = false;
		
		if (session!=null)
			if (session.getAttribute("accountBean")!=null)
				isLogged = true;
		
		String actionURL;
		
		if (!isLogged)
			actionURL = response.encodeURL("Register");
		else
			actionURL = response.encodeURL("RegisterTechnician");
	%>
	<div id="content">
		<form id="register_form" class="d-flex flex-column flex-grow-1 mx-auto px-5 w-50 border-start border-end border-darkorange bg-white justify-content-center" action="<%= actionURL %>">
			<div class="text-center fs-3 mt-3 mb-auto">
				<% 	
					if (!isLogged) { %>
						Registrati
				<% 	} 
					else { %>
						Registra tecnico
				<% 	} 
				%>
			</div>
			<div class="text-center mb-2"><i class="fas fa-user-pen fa-lg fa-fw"></i></div>
			<div class="row g-3 mb-2 justify-content-center">
			  	<div class="col-auto w-50">
	        		<input type="text" placeholder="Nome" class="form-control" name="firstname"/>
	        	</div>
	        </div>
	        <div class="row g-3 mb-2 justify-content-center">
	        	<div class="col-auto w-50">
	        		<input type="text" placeholder="Cognome" class="form-control" name="lastname"/>
	        	</div>
	        </div>
	        <div class="text-center mb-2"><i class="fas fa-envelope fa-lg fa-fw"></i></div>
			<div class="row g-3 mb-2 justify-content-center">
		    	<div class="col-auto w-50">
		    		<input type="email" placeholder="Email" class="form-control" name="email">
		    	</div>
			</div>
			<div class="text-center mb-2"><i class="fas fa-lock fa-lg fa-fw"></i></div>
			<div class="row g-3 mb-2 justify-content-center">
	    		<div class="col-auto w-50">
			    	<input type="password" placeholder="Password" class="form-control" name="psw">
			    	<div class="d-flex"></div>
			    </div>
			</div>
			<div class="row g-3 mb-2 justify-content-center">
			    <div class="col-auto w-50">
			    	<input type="password" placeholder="Conferma password" class="form-control" name="repsw">
			    </div>   
			</div>
			<div class="row g-3 mt-auto mb-3 justify-content-center">
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