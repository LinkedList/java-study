<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1>Create a new awesome book</h1>

		<c:url var="postUrl" value="/books/book/create/${userId}" />
		<form:form method="POST" commandName="book" role="form" action="${postUrl}">
			<div class="form-group">
				<label for="title">Title:</label>
				<form:input path="title" class="form-control" id="title" />
				<form:errors path="title" />
			</div>
			<div class="form-group">
				<label for="description">Description:</label>
				<form:input path="description" class="form-control" id="description" />
				<form:errors path="description" />
			</div>
				<form:button type="submit" value="Save" class="btn btn-primary">Save</form:button>
		</form:form>
	</div>
</t:wrapper>