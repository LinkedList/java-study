<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1>Books</h1>
		<a class="btn btn-primary" href="<c:url value="/books/seeder" />">Seed me some books please</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Description</th>
				</tr>
			</thead>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>${book.description}</td>
					<td>
						<div class="btn-group btn-group-xs">
						    <a class="btn btn-default" href="
						       <c:url value="/books/edit/${book.id}">
							       <c:param	name="returnToIndex" value="true" />
						       </c:url>">
							Edit
						    </a>
						    <a class="btn btn-danger" href="
						       <c:url value="/books/delete/${book.id}">
							       <c:param	name="returnToIndex" value="true" />
						       </c:url>">
							Delete
						    </a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>