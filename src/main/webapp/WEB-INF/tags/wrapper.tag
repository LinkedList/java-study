<%@tag description="Layout wrapper" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
	<title>Spring study application</title>
	<link rel="stylesheet" type="text/css"
	      href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" type="text/css"
	      href="<c:url value="/resources/css/main.css" />">
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
			<li><a href="<c:url value="/admin/users/" />">Users</a></li>
			<li><a href="<c:url value="/admin/books/" />">Books</a></li>
			<li><a href="<c:url value="/admin/accounts/" />">Accounts</a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
			<li class="nav navbar-text"><fmt:formatDate type="date" value="${date}" /></li>
			<li class="nav navbar-text">Users in database: <c:out value="${usersSize}" /></li>
			<li class="nav navbar-text">Books in database: <c:out value="${bookCount}" /></li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li class="nav navbar-text"><c:out value="${pageContext.request.userPrincipal.name}"/></li>  
				<li><a href="javascript:formSubmit()" > Logout</a></li>  
			</c:if>
		    </ul>
		</nav>
	    </div>
	</header>
	<jsp:doBody />
	<script>
		function formSubmit() {
		    document.getElementById("logoutForm").submit();
		}
	</script>
	<!-- csrt for log out-->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
	    <input type="hidden" 
		   name="${_csrf.parameterName}"
		   value="${_csrf.token}" />
	</form>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    </body>
</html>