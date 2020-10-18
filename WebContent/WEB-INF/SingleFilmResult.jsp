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
				<div>
				<h2>
					Film ID: ${film.id} <br>Film Title: ${film.title} <br>
					Film Description: ${film.description}<br>
				</h2>
				<br>
				<h4>Category: ${film.category}</h4>
				</div>
				<c:if test="${film.actors.size() != 0}">
				<div>
				<h2>Starring:</h2>
				<h4>
					<c:forEach var="actor" items="${film.actors}">
						<ul>
							<li>${actor.firstName} ${actor.lastName}</li>
						</ul>
					</c:forEach>
				</h4>
				</div>
				</c:if>
				<hr>
				<h4>What would you like to do with this film?</h4>
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
	<br>
	<a href="index.do">Go to HomePage</a>
</body>
</html>

