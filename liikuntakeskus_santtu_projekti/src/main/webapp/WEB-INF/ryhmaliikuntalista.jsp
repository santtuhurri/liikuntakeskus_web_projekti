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
</style>
<head>
<meta charset="utf-8" />
<title>Ryhmäliikunnat</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>

<body>
	<h1>Ryhmäliikunnat</h1>
	<p><a href="/">Palaa etusivulle</a>
	<p><a href="/lisaa-ryhmaliikuntaid-lomake">Lisää ryhmäliikunta id:llä</a></p>
	<p><a href="/lisaa-ryhmaliikunta-lomake">Lisää ryhmäliikunta ilman id:tä</a></p>
	<table class="table table_striped" >
	
<!--Taulukko ryhmäliikunnan arvojen otsikoille-->
	<tr>
		<th>ID</th>
		<th>Nimi</th>
		<th class="left">Kuvaus</th>
		<th>Tyyppi</th>
		<th>Kesto</th>
		<th>Hinta (&euro;)</th>
		<th>Ohjaaja</th>
		<th>Paikka</th>
		<th>Hallinnointi</th>
	</tr>
	
<!--Haetaan kaikkien ryhmäliikuntojen tiedot tietokannasta ja listataan ne taulukkoon-->
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
		<td><a href="/valittu-ryhmaliikunta?ryhmaliikuntaid=${ryhmaliikunta.id}">Muokkaa</a>
		<a href="/poista-ryhmaliikunta?ryhmaliikuntaid=${ryhmaliikunta.id}">Poista</a></td>
	</tr>
	</c:forEach>
	
	</table>
</body>
</html>
