package registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.UserDAO;
import registration.model.User;
import registration.model.UserError;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
		request.getRequestDispatcher("/login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role;
		HttpSession session = request.getSession();

		User user = new User();
		UserError userErrorMsgs = new UserError();
		
		session.removeAttribute("user");
		session.removeAttribute("errorMsgs");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		user.validateUser("login", user, userErrorMsgs);
		user = UserDAO.login(username, password);
		session.setAttribute("user", user);
		
		if (user.getRole() == null) {
			role = "";
			userErrorMsgs.setErrorMsg("User not Found");
		} else {
			role = user.getRole();
		}
		
		if (userErrorMsgs.getErrorMsg().equals("")) {
//			no error messages
			
			session.removeAttribute("errorMsgs");
			
			if (role.equals("admin")) {
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "admin");
			} else if(role.equals("student") || role.equals("faculty") || role.equals("staff")) {
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			} else if(role.equals("FacilityManager")) {
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			} else if (role.equals("repairer")) {
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "home");
			}
		} else {
//			error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/login.jsp").include(request, response);
		}
	}

}
