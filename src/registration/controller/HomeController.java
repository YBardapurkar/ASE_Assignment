package registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.model.User;

@WebServlet("/home")
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("login");
		if (currentUser != null) {
			System.out.println(currentUser.getRole());
		} else {
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "login");
		}
		getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
