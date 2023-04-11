<!DOCTYPE html>
<%@ page language="java" contentType="text/html; ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="utf-8" />

<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Poista ryhmäliikunta</title>
<style type="text/css">
label {
	display: block;
	width: 10em;
	float: left;
}
</style>
</head>

<body>
<!--Lomake ryhmäliikunnan poistamista varten id:n avulla-->
	<h1>Poista ryhmäliikunta</h1>
	<form action="/poista-ryhmaliikunta-lomake" method="post">
		<p>
			<label>Ryhmäliikunnan id:</label> <input type="text" value="" name="ryhmaliikuntaid" size="5" />
		</p>
		<p>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		<span class="button"><a href="/hallintapaneeli" >Peruuta</a></span>
		</p>
	</form>
</body>
</html>