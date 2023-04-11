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

// toteuttaa http://localhost:8080/listaa-ryhmaliikunnat
@WebServlet("/listaa-ryhmaliikunnat")
public class ListaaRyhmaliikunnatServlet extends HttpServlet {

	// lähettää selaimelle kaikki tietokannan ryhmäliikunnat taulukossa
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// haetaan ryhmäliikuntatunnit tietokannasta
		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();
		List<Ryhmaliikunta> ryhmaliikunnat = ryhmaliikuntadao.findAll();

		// viedään ryhmäliikuntojen lista .jsp:n näkyville
		request.setAttribute("ryhmaliikunnat", ryhmaliikunnat);

		// lähetetään pyyntö eteenpäin ryhmaliikuntalista.jsp:lle
		request.getRequestDispatcher("/WEB-INF/ryhmaliikuntalista.jsp").forward(request, response);

	}
}
