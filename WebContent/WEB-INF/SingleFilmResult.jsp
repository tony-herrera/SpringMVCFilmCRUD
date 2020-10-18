<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Result</title>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${! empty film}">
				<h2>
					Film ID: ${film.id} <br>Film Title: ${film.title}
				</h2>
				<h4>Would you like to delete this Film from the Database?</h4>
				<form action="redirToUpdate.do" method="POST">
					<button type="submit" name="filmId" value="${film.id}">Update</button>
					</form>
				<br>
				<form action="deleteFilm.do" method="POST">
					<button type="submit" name="id" value="${film.id}">Delete</button>
				</form>
			</c:when>
			<c:otherwise>
				<div>That Film Id number does not exist.</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>

