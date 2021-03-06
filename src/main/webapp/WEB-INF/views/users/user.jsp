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
		    <a class="btn btn-default" href="<c:url value="/users/createOrEdit">
			       <c:param name="id" value="${user.id}" />
		       </c:url>">Edit</a>
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
		<a class="btn btn-primary" href="<c:url value="/books/createOrEdit">
			   <c:param name="userId" value="${user.id}" />
		   </c:url>">Create new book</a>
		<a class="btn btn-primary" href="<c:url value="/users/editFavourites/${user.id}" />">Pick favourite</a>
		<table class="table">
			<thead>
				<tr>
					<th>Title</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.books}" var="book">
					<c:choose>
						<c:when test="${book.id == user.favouriteBook.id}">
							<tr class="success">
						</c:when>
						<c:otherwise>
							<tr>
						</c:otherwise>
					</c:choose>
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.description}" /></td>
						<td>
							<div class="btn-group btn-group-xs">
							    <a class="btn btn-default" href="<c:url value="/books/createOrEdit">
								       <c:param name="id" value="${book.id}" />
							       </c:url>">
								Edit
							    </a>
							    <a class="btn btn-danger" href="<c:url value="/books/delete/${book.id}" />">
								Delete
							    </a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h1>Accounts</h1>
		<a class="btn btn-primary" href="<c:url value="/account/createOrEdit">
			   <c:param name="userId" value="${user.id}" />
		   </c:url>">Create an account</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Prefix</th>
					<th>Number</th>
					<th>Bank Code</th>
				</tr>
			</thead>
			<c:forEach items="${user.accounts}" var="account">
				<tr>
					<td>${account.id}</td>
					<td>${account.name}</td>
					<td>${account.prefix}</td>
					<td>${account.number}</td>
					<td>${account.code}</td>
					<td>
						<div class="btn-group btn-group-xs">
						    <a class="btn btn-default" href="
						       <c:url value="/account/createOrEdit">
							       <c:param name="id" value="${account.id}" />
						       </c:url>">
							Edit
						    </a>
						    <a class="btn btn-danger" href="<c:url value="/account/delete/${account.id}" />">
							Delete
						    </a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>