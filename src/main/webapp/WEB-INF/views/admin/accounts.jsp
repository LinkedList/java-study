<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false"%>
<t:wrapper>
	<div class="container">
		<h1>Accounts</h1>
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
			<c:forEach items="${accounts}" var="account">
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
						    <a class="btn btn-danger" href="">
							Delete
						    </a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</t:wrapper>