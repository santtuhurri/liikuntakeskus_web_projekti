package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;

// toteuttaa http://localhost:8080/poista-ryhmaliikunta?ryhmaliikuntaid=1
@WebServlet("/poista-ryhmaliikunta")
public class PoistaRyhmaliikuntaServlet extends HttpServlet {

	// selaimelle ei lähetetä lomaketta, vaan ryhmäliikunta poistetaan suoraan
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// id
			String strId = request.getParameter("ryhmaliikuntaid");
			int ryhmaliikuntaId = Integer.parseInt(strId);

			RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

			// poistetaan ryhmäliikunta tietokannasta
			boolean poistoOnnistui = ryhmaliikuntadao.removeRyhmaliikunta(ryhmaliikuntaId);

			if (poistoOnnistui)
				// uudelleenohjaus /listaa-ryhmäliikunnat endpointtiin .jps-käsittelyn sijaan
				response.sendRedirect("/listaa-ryhmaliikunnat");
			else {
				request.setAttribute("viesti", "Ryhmäliikunnan poistaminen tietokannasta ei onnistunut.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
