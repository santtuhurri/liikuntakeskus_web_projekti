<%@ page language="java" contentType="text/html; ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
label {
	display: block;
	width: 10em;
	float: left;
}
</style>
<head>
<meta charset="utf-8" />
<title>Muokkaa ryhmäliikuntaa</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>

<body>
<!--Lomake ryhmäliikunnan muokkaamista varten-->
	<h1>Muokkaa ryhmäliikuntaa</h1>
	<form action="/muokkaa-ryhmaliikunta-lomake" method="post">
		<p>
			<label>Ryhmäliikunnan id:</label> <input type="text" value="" name="id" size="20" />
		</p>
		<p>
			<label>Nimi:</label> <input type="text" value="" name="nimi" size="20" />
		</p>
		<p>
			<label>Kuvaus:</label> <input type="text" value="" name="kuvaus" size="20" />
		</p>
		<p>
			<label>Tyyppi:</label> <input type="text" value="" name="tyyppi" size="20" />
		</p>
		<p>
			<label>Kesto minuutteina:</label> <input type="text" value="" name="kesto" size="20" />
		</p>
		<p>
			<label>Hinta:</label> <input type="text" value="" name="hinta" size="20" />
		</p>
		<p>
			<label>Ohjaaja:</label> <input type="text" value="" name="ohjaaja" size="20" />
		</p>
		<p>
			<label>Paikka:</label> <input type="text" value="" name="paikka" size="20" />
		</p>
		<p>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		<span class="button"><a href="/hallintapaneeli" >Peruuta</a></span>
		</p>
	</form>
</body>
</html>
