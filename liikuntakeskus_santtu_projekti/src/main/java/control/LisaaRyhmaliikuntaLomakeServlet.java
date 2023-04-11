package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;
import model.Ryhmaliikunta;

// toteuttaa http://localhost:8080/lisaa-ryhmaliikunta-lomake
@WebServlet("/lisaa-ryhmaliikunta-lomake")
public class LisaaRyhmaliikuntaLomakeServlet extends HttpServlet {

	// lähettää selaimelle tyhjän ryhmäliikuntatietojen lisäyslomakkeen ilman id-arvoa
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/ryhmaliikuntalisayslomake.jsp").forward(request, response);

	}

	/*
	 * vastaanottaa tietoa selaimelta: Otetaan lomakkeella syötetyn ryhmäliikunnan
	 * tiedot request (pyyntö)-olion parametritiedoista ja luodaan saaduista
	 * tiedoista Ryhmäliikunta-luokan olio
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// nimi
			String nimi = request.getParameter("nimi");
			// kuvaus
			String kuvaus = request.getParameter("kuvaus");
			// tyyppi
			String tyyppi = request.getParameter("tyyppi");
			// kesto
			String strKesto = request.getParameter("kesto");
			int kesto = Integer.parseInt(strKesto);
			// hinta
			String strHinta = request.getParameter("hinta");
			double hinta = Double.parseDouble(strHinta);
			// ohjaaja
			String ohjaaja = request.getParameter("ohjaaja");
			// paikka
			String paikka = request.getParameter("paikka");

			// luodaan uusi Ryhmäliikunta-luokan olio edellisillä parametreillä
			Ryhmaliikunta ryhmaliikunta = new Ryhmaliikunta(nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka);

			RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

			// talletetaan ryhmäliikunnan tiedot tietokantaan
			boolean lisaysOnnistui = ryhmaliikuntadao.addRyhmaliikunta(ryhmaliikunta);
			if (lisaysOnnistui)
				// uudelleenohjaus /listaa-ryhmaliikunnat endpointtiin .jps-käsittelyn sijaan
				response.sendRedirect("/listaa-ryhmaliikunnat");
			else {
				request.setAttribute("viesti", "Ryhmäliikunnan lisäyksessä tietokantaan tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {

			// tulostetaan Consoleen virhetilanteessa metodikutsupinoa, josta näkee
			// rivinumeron, jossa Exception tapahtuu
			e.printStackTrace();

			request.setAttribute("viesti", "Annetut tiedot eivät olleet kelvolliset, ole hyvä ja yritä uudestaan.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}

	}

}
