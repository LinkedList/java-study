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
		<c:choose>
			<c:when test="${command.user.id == null}">
				<h1>Create a new magnificent user</h1>
			</c:when>
			<c:otherwise>
				<h1>Edit this magnificent user</h1>
			</c:otherwise>
		</c:choose>

		<c:url var="postUrl" value="/users/createOrEdit" />
		<form:form method="POST" role="form" commandName="command" action="${postUrl}">
			<div class="form-group">
				<label for="login">Login:</label>
				<form:input path="user.login" class="form-control" id="login" />
				<form:errors path="user.login" />
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<form:input path="user.email" class="form-control" id="email" />
				<form:errors path="user.email" />
			</div>
				<form:button type="submit" value="Save" class="btn btn-primary">Save</form:button>
		</form:form>
	</div>
</t:wrapper>