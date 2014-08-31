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
		<h1>Edit the chosen one!</h1>
		<form:form method="POST" commandName="user" role="form">
			<div class="form-group">
				<label for="login">Login:</label>
				<form:input path="login" class="form-control" id="login" />
			</div>
			<div class="form-group">
				<label for="email">Email:</label>
				<form:input path="email" class="form-control" id="email" />
			</div>
				<form:button type="submit" value="Save" class="btn btn-primary">Save</form:button>
		</form:form>
	</div>
</t:wrapper>