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
		<form action="updateFilm.do”" method="POST" id="form1">
			<h2>
				Film ID: ${film.id} <br> Film Title: ${film.title} <br>
				Film Description: ${film.description} <br>
			</h2>
			<label for="title"> Title of the Film</label> 
			<input type="text" id="title" name="title"><br> 
			
			<label for="description">Description</label>
			 <input type="text" id="description" name="descripion"> 
			 <input type="submit" form="form1" value="submit">
		</form>
	</div> 
</body>
</html>

