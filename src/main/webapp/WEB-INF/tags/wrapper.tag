<%@tag description="Layout wrapper" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring study application</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/docs.min.css" />">
</head>
<body>
	<header class="navbar navbar-static-top bs-docs-nav" id="top"
		role="banner">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target=".bs-navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="<c:url value="/" />" class="navbar-brand">Study application</a>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">
				<ul class="nav navbar-nav">
					<li><a href="#">Something</a></li>
					<li><a href="#">will</a></li>
					<li><a href="#">be</a></li>
					<li><a href="#">here</a></li>
					<li><a href="#">in</a></li>
					<li><a href="#">the</a></li>
					<li><a href="#">future</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="container">
		<jsp:doBody />
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>