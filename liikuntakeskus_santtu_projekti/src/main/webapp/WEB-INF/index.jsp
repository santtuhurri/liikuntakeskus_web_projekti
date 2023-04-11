<%@ page language="java" contentType="text/html; ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8" />
<title>Santun liikuntakeskus</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />

</head>
<body>
	<h1>Santun liikuntakeskuksen etusivu</h1>
	<p> <a href="/listaa-ryhmaliikunnat">Ryhmäliikuntalista</a></p>
	<p>	<a href="/hallintapaneeli">Hallintapaneeli</a></p>
	<p>
	Harjoitustyön aiheena on kuvitteellinen Santun liikuntakeskus, jonka tarjontaan kuuluu useita erilaisia
	ryhmäliikuntatunteja.
	</p>
	<p>
	<b>Toiminnallisuudet:</b>	
	</p>
	<p>
	Hallintapaneeli - sisältää kaikki sovelluksen toiminnallisuudet.	
	</p>
	<p>
	Ryhmäliikuntalista - kaikki tarjolla olevat ryhmäliikunnat listattu tietoineen.
	<p>
	Ryhmäliikuntalistan muokkaa-painike - avaa valitun ryhmänliikunnan tiedot ja asettaa ne muokkauslomakkeeseen.
	</p>
	<p>
	Ryhmäliikuntalistan poista-painike - poistaa suoraan valitun ryhmäliikunnan.
	</p>
	<p>
	Lisää ryhmäliikunta id:llä - ryhmäliikunnan lisäys id:n avulla, jotta tietokantaan voidaan lisätä tietoa mihin kohtaan tahansa.
	</p>
	<p>
	Lisää ryhmäliikunta ilman id:tä - ryhmäliikunnan lisäys ilman id:tä, jolloin uusi tieto menee tietokannassa viimeiseksi.
	</p>
	<p>
	Muokkaa ryhmäliikuntaa - erillinen lomake, jonka avulla haluttua ryhmäliikuntaa voidaan muokata.
	</p>
	<p>
	Poista ryhmäliikunta - erillinen lomake, jonka avulla haluttu ryhmäliikunta voidaan poistaa id:n avulla.
	</p>
</body>
</html>