<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring4 MVC - Index</title>
</head>
<body>
	<h1>Index page</h1>
	<ul>
		<li><a href="<c:url value="/hello" />">Hello World</a></li>
		<li><a href="<c:url value="/hello/Martin" />">Hello Martin</a></li>
		<li><a href="<c:url value="/image/lol" />">Image route with
				existent image</a></li>
		<li><a href="<c:url value="/image/nonexistent" />">Image
				route with nonexistent image</a></li>
		<li><a href="<c:url value="/hello/hello/nonexistent" />">Nonexistent
				route - 404</a></li>
		<li><a href="<c:url value="/resources/img/failwhale.jpg" />">Static
				jpg</a></li>
	</ul>
</body>
</html>