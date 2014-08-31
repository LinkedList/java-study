<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1>This is a list</h1>
		<ul>
			<c:forEach var="str" items="${list}">
				<li>${str}</li>
			</c:forEach>
		</ul>

		<h1>This is a string (obviously)</h1>
		<div><c:out value="${string}" /></div>

		<h1>This is a date</h1>
		<div><c:out value="${date}" /></div>
	</div>
</t:wrapper>