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
		User currentUser = (User) session.getAttribute("user");
		
//		redirect to login if user not found
		if (currentUser == null) {
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "login");
			return;
		}
		String role = currentUser.getRole();
		
//		homepage for admin
		if (role.equals("admin")) {
			request.getRequestDispatcher("menu_admin.jsp").include(request, response);
			request.getRequestDispatcher("/home_admin.jsp").include(request, response);	
		} 
//		homepage for student/faculty/staff
		else if(role.equals("student") || role.equals("faculty") || role.equals("staff")) {
			request.getRequestDispatcher("menu_student.jsp").include(request, response);
			request.getRequestDispatcher("/home_student.jsp").include(request, response);	
		} 
//		homepate for facility manager
		else if(role.equals("FacilityManager")) {
			request.getRequestDispatcher("menu_fm.jsp").include(request, response);
			request.getRequestDispatcher("/home_student.jsp").include(request, response);
		} 
//		homepage for repairer
		else if (role.equals("repairer")) {
			request.getRequestDispatcher("menu_repairer.jsp").include(request, response);
			request.getRequestDispatcher("/home_student.jsp").include(request, response);
		}
	}
}
