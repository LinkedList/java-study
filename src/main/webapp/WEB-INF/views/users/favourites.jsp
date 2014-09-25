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
	    <h1>Edit favourites</h1>

	    <c:url var="postUrl" value="/users/editFavourites" />
	    <form:form method="POST" role="form" commandName="command" action="${postUrl}">
		    <div class="form-group">
			<label for="book">Favourite book:</label>
			<form:select path="book" class="form-control">
				<form:option value="-" label="-- Please choose favourite --" />
				<form:options items="${map}"/>
			</form:select>
		    </div>
		    <form:button type="submit" value="Save" class="btn btn-primary">Save</form:button>
	    </form:form>
	</div>
</t:wrapper>