package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// toteuttaa http://localhost:8080/hallintapaneeli
@WebServlet("/hallintapaneeli")
public class HallintapaneeliServlet extends HttpServlet {

	// lähettää selaimelle hallintapaneeli.jsp:n
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/hallintapaneeli.jsp").forward(request, response);

	}

}
