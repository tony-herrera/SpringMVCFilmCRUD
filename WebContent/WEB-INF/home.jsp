<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<H1>Welcome to the Film Query</H1>
</head>
<body>
<div>
	<form action="searchByFilmId.do" method="GET">
		<p>Please enter the Film ID to begin your search</p>
		<input type="text" name="filmId" class="search"
			placeholder="Place your selection here..."> <input
			type="submit" name="submit" class="submit" value="Search">
	</form>
</div>
<div>
	<form action="searchByKeyword.do" method="GET">
		<p>Please enter the keyword to begin your search</p>
		<input type="text" name="keyWord" class="search"
			placeholder="Place your selection here..."> <input
			type="submit" name="submit" class="submit" value="Search">
	</form>
</div>
<div>
<a href="NewFilm.html">Add a New Film </a><br/>

</div>	
</body>
</html>