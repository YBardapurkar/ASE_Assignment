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
		HttpSession session = request.getSession();
		session.removeAttribute("user");			// single User object
		session.removeAttribute("current_user");	// logged in user
		session.removeAttribute("errorMsgs");		// single User message object
		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
		request.getRequestDispatcher("/login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");			// single User object
		session.removeAttribute("current_user");	// logged in user
		session.removeAttribute("errorMsgs");		// single User message object

		User user = new User();
		UserError userErrorMsgs = new UserError();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		userErrorMsgs = user.validateUser("login");
//		user.setPassword("");
		session.setAttribute("user", user);
		if (userErrorMsgs.getErrorMsg().equals("")) {
//			no error messages
			
			User loginUser = UserDAO.login(user.getUsername(), user.getPassword());
						
//			User does not exist in the database
			if (loginUser.getUsername() == null && loginUser.getRole() == null) {
				String result="Username does not exist or Password mismatch";
				userErrorMsgs.setErrorMsg(result);
				session.setAttribute("errorMsgs", userErrorMsgs);
				request.getRequestDispatcher("/menu_login.jsp").include(request, response);
				request.getRequestDispatcher("/login.jsp").include(request, response);
			} 
//			User found in database
			else {
				session.setAttribute("current_user", loginUser);
				if (loginUser.getRole().equals("Admin")) {
					response.sendRedirect("admin");
				} else if(loginUser.getRole().equals("Facility Manager")) {
					response.sendRedirect("facility_manager");
				} else if (loginUser.getRole().equals("Repairer")) {
					response.sendRedirect("repairer");
				} else {
					response.sendRedirect("home");
				} 
			}
		} else {
//			error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/login.jsp").include(request, response);
		}
	}

}
