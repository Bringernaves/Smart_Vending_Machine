<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/sell.js"></script>
	<title>SVM - Distributore</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>
	<div id="content">
		<form id="sell_form" class="d-none d-flex flex-column flex-grow-1 mx-auto px-3 w-75 border-start border-end border-darkorange bg-white justify-content-center" action="<%= response.encodeURL("Sell") %>">
			<table class="table table-striped text-center">
			  <thead>
			    <tr>
			      <th scope="col">Prodotto</th>
			      <th scope="col">Prezzo</th>
			      <th scope="col">Disponibilità</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
			<div class="row g-3 justify-content-center">
				<div class="col-auto">
					<button type="submit" class="btn btn-primary">Acquista</button>
				</div>
			</div>
			<div id="message" class="d-flex justify-content-center"></div>
		</form>
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>