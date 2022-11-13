<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.giuseppetripiciano.project.model.*"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<header>
		<% 
			boolean isLogged = false;
			
			Object accountBean = session.getAttribute("accountBean");
			
			if (session!=null)
				if (accountBean!=null)
					isLogged = true;	
		
			boolean isAdmin = false;
			boolean isTechnician = false;
			boolean isMachine = false;
			
			if (isLogged) { 
				if (accountBean instanceof User) {
					isAdmin = ((User) accountBean).getType() == 2;
					isTechnician = ((User) accountBean).getType() == 1;
				}
				
				isMachine = accountBean instanceof Machine;
			}
		 %>
		
		<nav class="navbar navbar-expand-lg navbar-light bg-lightgrey border border-darkorange flex-grow-1 justify-content-center my-0 py-0">
		    <a class="navbar-brand d-inline-flex flex-column align-self-center justify-content-center ms-4 me-5 my-2 px-3  border border-darkorange rounded-pill bg-gold text-lightgrey text-center" href="<%= response.encodeURL("index.jsp") %>">SVM Ent.</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleContent">
		    	<div class="d-flex align-items-center">
		    		<span class="navbar-toggler-icon"></span>
		    		<% 
		    			if (isLogged) { 
			    			if (!isMachine) { %>
								<span class="rounded fst-italic fs-6 px-2">Ciao<br>${accountBean.firstname}!</span>
						<% 	} 
			    			else { %>
								<span id="id" class="rounded fst-italic fs-6 px-2">ID #${accountBean.id}</span>
						<% 	} %>
					<% 	} 
					%>
		    	</div>
	 		</button>
		    <div class="collapse navbar-collapse justify-content-start" id="collapsibleContent">
		      <ul class="navbar-nav">
		      	<% if (!isMachine) { %>
			        <li class="nav-item ms-auto my-2 d-flex flex-column justify-content-center">
			          <a id="home" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("index.jsp") %>">Home <i class="fas fa-house fa-md fa-fw"></i></a>
			        </li>
		        <% } %>
		        <% if (isLogged && !isMachine) { %>
			        <li class="nav-item ms-auto my-2">
			          <a id="connect" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("Connect") %>">Connetti <i class="fas fa-link fa-md fa-fw"></i></a>
			        </li>
		        <% } %>
		        <% if (isLogged && (isTechnician || isAdmin)) { %>
			        <li class="nav-item ms-auto my-2">
			          <a id="refill" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("Refill") %>">Rifornisci <i class="fas fa-toolbox fa-md fa-fw"></i></a>
			        </li>
		        <% } %>
		        <% if (isLogged && isAdmin) { %>
		        	<li class="nav-item ms-auto me-4 my-2 dropdown">
						<a class="nav-link dropdown-toggle border border-darkorange rounded px-2 bg-lightorange" href="#" id="register" role="button" data-bs-toggle="dropdown">Registra <i class="fas fa-plus fa-md fa-fw"></i></a>
						<ul class="dropdown-menu bg-lightgrey" aria-labelledby="register">
							<li><a class="dropdown-item" href="<%= response.encodeURL("RegisterTechnician") %>">Tecnico</a></li>
							<li><a class="dropdown-item" href="<%= response.encodeURL("RegisterMachine") %>">Distributore</a></li>
						</ul>
					</li>
		        <% } %>
		      </ul>
		  	</div>
	  		<div class="collapse navbar-collapse justify-content-end" id="collapsibleContent">
		    	<ul class="navbar-nav">
					<% 
						if (!isLogged) { %>
				    		<li class="nav-item ms-auto my-2">
				    			<a id="login" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("Login") %>">Accedi <i class="fas fa-arrow-right-to-bracket fa-md fa-fw"></i></a>
				    		</li>
				    		<li class="nav-item ms-auto my-2">
				    			<a id="register" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("Register") %>">Registrati <i class="fas fa-user-plus fa-md fa-fw"></i></a>
				    		</li>
		    		<% } 
						else { 
							if (!isMachine) { %>
					    		<li class="nav-item ms-auto me-4 my-2 dropdown">
									<a class="nav-link dropdown-toggle border border-darkorange rounded px-2 bg-lightorange" href="#" id="account" role="button" data-bs-toggle="dropdown">Account <i class="fas fa-user fa-md fa-fw"></i></a>
									<ul class="dropdown-menu bg-lightgrey" aria-labelledby="account">
										<li class="text-center">
											<span class="px-3 opacity-75 fst-italic fw-bold">Credito</span>
											<span id="credit" class="px-3 opacity-75 fst-italic">${accountBean.credit}&euro;</span>
										</li>
										<li><hr class="dropdown-divider"></li>
										<li><a class="dropdown-item" href="<%= response.encodeURL("Profile") %>">Dati personali</a></li>
										<li><a class="dropdown-item" href="<%= response.encodeURL("Recharge") %>">Ricarica</a></li>
										<li><hr class="dropdown-divider"></li>
										<li><a id="logout" class="dropdown-item" href="<%= response.encodeURL("Logout") %>">Esci <i class="fas fa-arrow-right-to-bracket fa-md fa-fw"></i></a></li>
									</ul>
								</li>
						<% 	} 
							else { %>
							<li class="nav-item ms-auto my-2">
				    			<a id="logout" class="nav-link border border-darkorange me-4 px-2 rounded bg-lightorange" href="<%= response.encodeURL("Logout") %>">Logout <i class="fas fa-arrow-right-to-bracket fa-md fa-fw"></i></a>
				    		</li>
		    			<% 	} %>
	    			<% 	} 	
	    			%>
		    	</ul>
		    	<% 
			    	if (isLogged) { 
		    			if (!isMachine) { %>
							<div class="navbar-collapse collapse flex-grow-0 justify-content-end align-self-center"><span class="fst-italic ms-auto me-5 px-2">Ciao ${accountBean.firstname}!</span></div>
					<% 	} 
		    			else { %>
							<div class="navbar-collapse collapse flex-grow-0 justify-content-end align-self-center"><span id="id" class="fst-italic ms-auto me-4 px-2">ID #${accountBean.id}</span></div>
					<% 	} %>
				<% 	} %>
    		</div> 	
    	</nav>
	</header>
</body>
</html>