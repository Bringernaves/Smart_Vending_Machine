<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="WEB-INF/fragments/refs.jsp"/>
	<script src="assets/js/index.js"></script>
	<title>SVM - Home</title>
</head>
<body>
	<jsp:include page="WEB-INF/fragments/header.jsp"/>
	<div id="content">
		<div id="carousel" class="m-auto vh-80 w-75 carousel slide border-start border-end border-darkorange" data-bs-ride="carousel">
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#carousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" data-bs-target="#carousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" data-bs-target="#carousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="h-100 carousel-inner">
		    <div class="h-100 carousel-item active">
		      <img src="assets/images/coffee.jpg" class="d-block w-100 h-100" alt="...">
		    </div>
		    <div class="h-100 carousel-item">
		      <img src="assets/images/chips.jpg" class="d-block w-100 h-100" alt="...">
		    </div>
		    <div class="h-100 carousel-item">
		      <img src="assets/images/water.jpg" class="d-block w-100 h-100" alt="...">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
	</div>
	<jsp:include page="WEB-INF/fragments/footer.jsp"/>
</body>
</html>