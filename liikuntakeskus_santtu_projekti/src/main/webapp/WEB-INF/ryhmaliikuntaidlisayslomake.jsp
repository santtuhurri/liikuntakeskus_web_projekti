<!DOCTYPE html>
<%@ page language="java" contentType="text/html; ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="utf-8" />
<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Lis‰‰ ryhm‰liikunta</title>
<style type="text/css">
label {
	display: block;
	width: 10em;
	float: left;
}
</style>
</head>

<body>
<!--Lomake ryhm‰liikunnan lis‰‰miseen id-arvon avulla-->
	<h1>Lis‰‰ ryhm‰liikunta</h1>
	<form action="/lisaa-ryhmaliikuntaid-lomake" method="post">
		<p>
			<label>Id:</label> <input type="text" value="" name="id" size="20" />
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