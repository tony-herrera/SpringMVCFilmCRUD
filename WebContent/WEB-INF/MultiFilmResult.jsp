<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Films from Search</title>
</head>
<body>
Film list printout: ${filmList}
	<div>
		<table>
			<c:forEach var="film" items="${filmList}">
				<tr>
					<td>${film.id}</td>
					<td>${film.title}</td>
					<td>${film.description}</td>
					<td><form action="redirToUpdate.do" method="POST">
							<button type="submit" name="filmId" value="${film.id}">Update</button>
						</form></td>
					<td><form action="deleteFilm.do" method="POST">
							<button type="submit" name="id" value="${film.id}">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>