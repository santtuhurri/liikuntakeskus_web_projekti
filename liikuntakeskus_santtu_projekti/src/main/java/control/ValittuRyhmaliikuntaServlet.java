package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;
import model.Ryhmaliikunta;

// toteuttaa http://localhost:8080/valittu-ryhmaliikunta?ryhmaliikuntaid=1
@WebServlet("/valittu-ryhmaliikunta")
public class ValittuRyhmaliikuntaServlet extends HttpServlet {

	// lähettää selaimelle näkyviin halutun ryhmäliikunnan tiedot
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// id
		String strId = request.getParameter("ryhmaliikuntaid");
		int ryhmaliikuntaId = Integer.parseInt(strId);

		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();
		List<Ryhmaliikunta> ryhmaliikunnat = ryhmaliikuntadao.findOne(ryhmaliikuntaId);

		// viedään ryhmaliikunta-lista .jsp:n näkyville
		request.setAttribute("ryhmaliikunnat", ryhmaliikunnat);

		// lähetetään pyyntö eteenpäin ryhmaliikuntayksi.jsp:lle
		request.getRequestDispatcher("/WEB-INF/valitunryhmaliikunnanmuokkaus.jsp").forward(request, response);

	}

	// lähettää selaimelle ryhmäliikuntalomakkeen, johon on valmiiksi täytetty
	// valitun ryhmäliikunnan tiedot
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// id
			String strId = request.getParameter("id");
			int id = Integer.parseInt(strId);
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
			Ryhmaliikunta ryhmaliikunta = new Ryhmaliikunta(strId, nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka);

			RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

			// viedään muutos tietokantaan
			boolean muokkausOnnistui = ryhmaliikuntadao.editRyhmaliikunta(id, ryhmaliikunta);
			if (muokkausOnnistui)
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
