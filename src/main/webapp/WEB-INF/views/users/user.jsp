<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1 class="pull-left">This is the chosen one!</h1>
		<div class="btn-group pull-right">
			<a class="btn btn-default" href="<c:url value="/users/user/edit/${user.id}" />">Edit</a>
			<a class="btn btn-danger" href="<c:url value="/users/delete/${user.id}" />">Delete</a>
		</div>
		<table class="table">
			<tr>
				<td>Login</td>
				<td><c:out value="${user.login}" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><c:out value="${user.email}" /></td>
			</tr>
		</table>
		<h2>(S)He has some books as well</h2>
		<a class="btn btn-default" href="<c:url value="/books/create/${user.id}" />">Create new book for this magnificent bastard</a>
		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.books}" var="book">
					<tr>
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.description}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</t:wrapper>