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
		<a class="btn btn-primary" href="<c:url value="/books/seeder" />">Seed me some books please</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>${book.description}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>