<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<c:choose>
		<c:when test="${! empty film}">
			<h2>Film ID: ${film.id}
			<br>Film Title: ${film.title}</h2>
		</c:when>
		<c:otherwise>
			<div>That Film Id number does not exist.</div>
		</c:otherwise>
	</c:choose>
	</div>
	<div>
	<h4>Would you like to delete this Film from the Database?</h4>
	<form action="index.do" method="GET">
	<button type="submit"> No</button>
	</form>
	</div>
	<form action="deleteFilm.do" method="POST">
	<button type ="submit" name="id" value="${film.id}">Yes</button>
	</form>
</body>
</html>