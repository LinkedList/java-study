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
		<h1>Would you create me an account, please</h1>

		<c:url var="postUrl" value="/account/create/${userId}" />
		<form:form method="POST" commandName="account" role="form" action="${postUrl}">
			<div class="form-group">
				<label for="name">Name:</label>
				<form:input path="name" class="form-control" id="name" />
				<form:errors path="name" />
			</div>
			<div class="form-group">
				<label for="prefix">Prefix:</label>
				<form:input path="prefix" class="form-control" id="prefix" />
				<form:errors path="prefix" />
			</div>
			<div class="form-group">
				<label for="number">Account Number:</label>
				<form:input path="number" class="form-control" id="number" />
				<form:errors path="number" />
			</div>
			<div class="form-group">
				<label for="code">Bank code:</label>
				<form:input path="code" class="form-control" id="code" />
				<form:errors path="code" />
			</div>
				<form:button type="submit" value="Save" class="btn btn-primary">Save</form:button>
		</form:form>
	</div>
</t:wrapper>