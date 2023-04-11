package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;

// toteuttaa http://localhost:8080/poista-ryhmaliikunta-lomake
@WebServlet("/poista-ryhmaliikunta-lomake")
public class PoistaRyhmaliikuntaLomakeServlet extends HttpServlet {

	// lähettää selaimelle tyhjän ryhmäliikuntatietojen poistolomakkeen
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/ryhmaliikuntapoistolomake.jsp").forward(request, response);

	}

	/*
	 * vastaanottaa tietoa selaimelta: Otetaan lomakkeella syötetyn ryhmäliikunnan id,
	 * request (pyyntö)-olion parametritiedoista ja poistetaan sen avulla
	 * tietokannasta haluttu Ryhmäliikunta-luokan olio
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// id 
			String strId = request.getParameter("ryhmaliikuntaid");
			int ryhmaliikuntaId = Integer.parseInt(strId);

			RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

			// poistetaan ryhmäliikunta tietokannasta
			boolean poistoOnnistui = ryhmaliikuntadao.removeRyhmaliikunta(ryhmaliikuntaId);

			if (poistoOnnistui)
				// uudelleenohjaus /listaa-ryhmaliikunnat endpointtiin .jps-käsittelyn sijaan
				response.sendRedirect("/listaa-ryhmaliikunnat");
			else {
				request.setAttribute("viesti", "Ryhmäliikunnan poistaminen tietokannasta ei onnistunut.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("viesti", "Ryhmäliikunnan poistamisessa tietokannasta tapahtui virhe.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}

	}

}
