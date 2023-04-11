<%@ page language="java" contentType="text/html; ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
.left {
text-align: left
}
tr, th, td {
text-align: center
}
label {
	display: block;
	width: 10em;
	float: left;
}
</style>
<head>
<meta charset="utf-8" />
<title>Valittu ryhm�liikunta</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>

<body>
	<h1>Valittu ryhm�liikunta</h1>
	<p><a href="/">Palaa etusivulle</a></p>
	<table class="table table_striped" >
	
<!--Taulukko ryhm�liikunnan arvojen otsikoille-->
	<tr>
		<th>ID</th>
		<th>Nimi</th>
		<th class="left">Kuvaus</th>
		<th>Tyyppi</th>
		<th>Kesto</th>
		<th>Hinta (&euro;)</th>
		<th>Ohjaaja</th>
		<th>Paikka</th>
		<th>Poista</th>
	</tr>
	
<!--Haetaan valitun ryhm�liikunnan tiedot tietokannasta ja listataan ne taulukkoon-->
	<c:forEach items="${ryhmaliikunnat}" var="ryhmaliikunta"> 
	<tr>
		<td><c:out value="${ryhmaliikunta.id}" /></td> 
		<td><c:out value="${ryhmaliikunta.nimi}" /></td>
		<td class="left"><c:out value="${ryhmaliikunta.kuvaus}" /></td>
		<td><c:out value="${ryhmaliikunta.tyyppi}" /></td>
		<td><c:out value="${ryhmaliikunta.kesto} min"/></td>
		<td><c:out value="${ryhmaliikunta.hinta}" /></td>
		<td><c:out value="${ryhmaliikunta.ohjaaja}"/></td>
		<td><c:out value="${ryhmaliikunta.paikka}" /></td>
		<td><a href="/poista-ryhmaliikunta?ryhmaliikuntaid=${ryhmaliikunta.id}">Poista</a>
	</tr>
	</c:forEach>
	
	</table>
	
<!--Lomake ryhm�liikunnan muokkaamista varten. Arvot t�ytet��n valitun ryhm�liikunnan mukaisesti.-->
	<h1>Muokkaa ryhm�liikuntaa</h1>
	<form action="/muokkaa-ryhmaliikunta-lomake" method="post">
	<c:forEach items="${ryhmaliikunnat}" var="ryhmaliikunta">
		<p>
			<label>Ryhm�liikunnan id:</label> <input type="text" value="${ryhmaliikunta.id}"  name="id" size="20" />
		</p>
		<p>
			<label>Nimi:</label> <input type="text" value="${ryhmaliikunta.nimi}" name="nimi" size="20" />
		</p>
		<p>
			<label>Kuvaus:</label> <input type="text" value="${ryhmaliikunta.kuvaus}" name="kuvaus" size="20" />
		</p>
		<p>
			<label>Tyyppi:</label> <input type="text" value="${ryhmaliikunta.tyyppi}" name="tyyppi" size="20" />
		</p>
		<p>
			<label>Kesto minuutteina:</label> <input type="text" value="${ryhmaliikunta.kesto}" name="kesto" size="20" />
		</p>
		<p>
			<label>Hinta:</label> <input type="text" value="${ryhmaliikunta.hinta}" name="hinta" size="20" />
		</p>
		<p>
			<label>Ohjaaja:</label> <input type="text" value="${ryhmaliikunta.ohjaaja}" name="ohjaaja" size="20" />
		</p>
		<p>
			<label>Paikka:</label> <input type="text" value="${ryhmaliikunta.paikka}" name="paikka" size="20" />
		</p>
		</c:forEach>
			<p>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		<span class="button"><a href="/listaa-ryhmaliikunnat" >Peruuta</a></span>
		</p>
	</form>
</body>
</html>
