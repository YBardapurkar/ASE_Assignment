package registration.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.data.UserDAO;
import registration.model.User;
import registration.model.UserError;

@WebServlet("/register")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");			// single User object
		session.removeAttribute("current_user");	// logged in user
		session.removeAttribute("errorMsgs");		// single User message object
		
		session.setAttribute("role_dropdown", getRoles());
		
		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
		request.getRequestDispatcher("/register.jsp").include(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("user");			// single User object
		session.removeAttribute("current_user");	// logged in user
		session.removeAttribute("errorMsgs");		// single User message object
		
		User newUser = new User();
		UserError userErrorMsgs = new UserError();
		
		newUser = getUserParam(request);
		newUser.validateUser(action, newUser, userErrorMsgs);
		
		if (!userErrorMsgs.getErrorMsg().equals("")) {
//			if error messages
			session.setAttribute("user", newUser);	
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/register.jsp").include(request, response);
		}
		else {
//			if no error messages
			UserDAO.insertUser(newUser);
			session.setAttribute("role_dropdown", getRoles());
			
			newUser.setMessage("Registration is successful");
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/register.jsp").include(request, response);
		}
	}
	
	private User getUserParam (HttpServletRequest request) {
		User user = new User();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstname(request.getParameter("firstname")); 
		user.setLastname(request.getParameter("lastname"));
		user.setRole(request.getParameter("role"));
		user.setUtaId(request.getParameter("utaid")); 
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setStreet(request.getParameter("street")); 
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state")); 
		user.setZipcode(request.getParameter("zipcode"));
		
		return user;
	}
	
	private ArrayList<String> getRoles() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("Student");
		roles.add("Faculty");
		roles.add("Repairer");
		if (UserDAO.getUsersByRole("Facility Manager").isEmpty()) {
			roles.add("Facility Manager");
		}
		if (UserDAO.getUsersByRole("Admin").isEmpty()) {
			roles.add("Admin");
		}
		return roles;
	}
}
