<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1>There shall be all of the users listed here in all their glory</h1>
		<a class="btn btn-primary" href="<c:url value="/users/seeder" />">Seed me some users please</a>
		<a class="btn btn-primary" href="<c:url value="/users/user/create" />">Let me create a new one please</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Login</th>
					<th>Email</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.login}</td>
					<td>${user.email}</td>
					<td>
						<div class="btn-group btn-group-xs">
							<a class="btn btn-primary" href="<c:url value="/users/user/${user.id}" />">Detail</a>
							<a class="btn btn-default" href="<c:url value="/users/user/edit/${user.id}" />">Edit</a>
							<a class="btn btn-danger" href="<c:url value="/users/delete/${user.id}" />">Delete</a>
						</div></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>