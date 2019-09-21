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
		session.removeAttribute("user");
		session.removeAttribute("errorMsgs");
		
		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
		request.getRequestDispatcher("/login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("user");
		session.removeAttribute("errorMsgs");

		String role;
		User user = new User();
		UserError userErrorMsgs = new UserError();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");

		user.validateUser("login", user, userErrorMsgs);

		user = UserDAO.login(user.getUsername(), user.getPassword());
		if (user.getUsername() == null && user.getPassword() == null) {
			String result="username does not exist in database";
			
			userErrorMsgs.setErrorMsg(result);
			System.out.println(userErrorMsgs.getErrorMsg());
		} 
		
		
		session.setAttribute("current_user", user);
		
		
		if (user.getRole() == null) {
			role = "";
			//userErrorMsgs.setErrorMsg("User not Found");
		} else {
			role = user.getRole();
		}
		
		if (userErrorMsgs.getErrorMsg().equals("")) {
//			no error messages
			
			session.removeAttribute("errorMsgs");
			
			if (role.equals("Admin")) {
				response.sendRedirect("admin");
			} else if(role.equals("Facility Manager")) {
				response.sendRedirect("facility_manager");
			} else if (role.equals("Repairer")) {
				response.sendRedirect("repairer");
			} else {
				response.sendRedirect("home");
			} 
		} else {
//			error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/login.jsp").include(request, response);
			session.removeAttribute("errorMsgs");
		}
	}

}
