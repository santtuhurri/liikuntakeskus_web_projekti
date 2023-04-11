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

//HUOM! TÄMÄ ON VAIN TESTIKÄYTÖSSÄ!!

@WebServlet("/listaa-yksi")
public class ListaaYksiRyhmaliikuntaServlet extends HttpServlet {

	// lähettää selaimelle yhden ryhmäliikunnan tiedot, halutun id:n avulla
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// id
		String strId = request.getParameter("ryhmaliikuntaid");
		int ryhmaliikuntaId = Integer.parseInt(strId);

		// haetaan ryhmäliikunta tietokannasta
		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();
		List<Ryhmaliikunta> ryhmaliikunnat = ryhmaliikuntadao.findOne(ryhmaliikuntaId);

		// viedään ryhmaliikunta-lista .jsp:n näkyville
		request.setAttribute("ryhmaliikunnat", ryhmaliikunnat);

		// lähetetään pyyntö eteenpäin ryhmaliikuntalista.jsp:lle
		request.getRequestDispatcher("/WEB-INF/ryhmaliikuntalista.jsp").forward(request, response);

	}

}
