<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film</title>
</head>
<body>
	<div>
			<h2>
				Film ID: ${film.id} <br> Film Title: ${film.title} <br>
				Film Description: ${film.description} <br>
			</h2>
		<form action="updateFilm.do" method="POST">
			<input type="hidden" name="filmId" value="${film.id}">
			<label for="title"> Title of the Film</label> 
			<input type="text" name="title" value="${film.title}"> <br> 
			<label for="description">Description</label>
			 <input type="text" name="description" value="${film.description}"> 
			 <input type="submit" name="name">
		</form>
	</div> 
	<a href="index.do">Go to HomePage</a><br/>
</body>
</html>

