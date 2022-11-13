<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../fragments/refs.jsp"/>
	<script src="assets/js/refill.js"></script>
	<title>SVM - Rifornisci</title>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
	<div id="content">
		<form id="refill_form"class="d-flex flex-column m-auto p-3 w-50 border border-darkorange bg-white justify-content-center" action="<%= response.encodeURL("Refill") %>">
			<span class="text-center fs-3 my-2">Rifornisci</span>
			<div class="row g-3 my-2 justify-content-center align-items-center text-center">
				<div class="col-auto px-0">
					<i class="fas fa-hashtag fa-lg fa-fw"></i>
				</div>
				<div class="col-auto w-25">
					<input type="text" placeholder="ID" class="form-control" name="id">
				</div>
			</div>
			<div class="row g-3 my-3 justify-content-center align-items-center text-center">
				<div class="col-auto">
					<button type="submit" class="btn btn-primary">Rifornisci</button>
				</div>	
			</div>
			<div id="message" class="d-flex justify-content-center"></div>
		</form>	
	</div>
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>