<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/recharge.js"></script>
	<title>SVM - Ricarica</title>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
	<div id="content">
		<form id="recharge_form"class="d-flex flex-column m-auto p-3 w-50 border border-darkorange bg-white justify-content-center" action="<%= response.encodeURL("Recharge") %>">
			<span class="text-center fs-3 my-2">Ricarica</span>
			<div class="row g-3 my-2 justify-content-center align-items-center text-center">
				<div class="col-auto w-25">
					<input type="text" placeholder="Importo" class="form-control" name="recharge">
					<div class="d-flex"></div>
				</div>
				<div class="col-auto px-0">
					<i class="fas fa-euro-sign fa-lg fa-fw"></i>
				</div>
			</div>
			<div class="row g-3 my-3 justify-content-center align-items-center text-center">
				<div class="col-auto">
					<button type="submit" class="btn btn-primary">Ricarica</button>
				</div>	
			</div>
			<div id="message" class="d-flex justify-content-center"></div>
		</form>	
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>