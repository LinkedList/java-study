<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="jumbotron">
		<div class="container">
		</div>
	</div>
	<div class="container">
		<h1>Welcome to the Internet</h1>
		<ul class="nav nav-pills nav-stacked">
			<li><a href="<c:url value="/hello" />">Hello World</a></li>
			<li><a href="<c:url value="/hello/Martin" />">Hello Martin</a></li>
			<li><a href="<c:url value="/image/lol" />">Image route with
					existent image</a></li>
			<li><a href="<c:url value="/image/nonexistent" />">Image
					route with nonexistent image</a></li>
			<li><a href="<c:url value="/hello/hello/nonexistent" />">Nonexistent
					route - 404</a></li>
			<li><a href="<c:url value="/resources/img/failwhale.jpg" />">Static
					jpg</a></li>
			<li><a href="<c:url value="/viewModelTest" />">View Model Test</a></li>
		</ul>
	</div>
</t:wrapper>