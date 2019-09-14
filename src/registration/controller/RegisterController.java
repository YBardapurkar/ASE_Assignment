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

@WebServlet("/register")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/menu_login.jsp").include(request, response);
		request.getRequestDispatcher("/register.jsp").include(request, response);
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User newUser = new User();
		UserError userErrorMsgs = new UserError();
		session.removeAttribute("user");
		session.removeAttribute("errorMsgs");
		
		newUser = getUserParam(request);
		newUser.validateUser(action, newUser, userErrorMsgs);
		session.setAttribute("user", newUser);	
		
		if (!userErrorMsgs.getErrorMsg().equals("")) {
//			if error messages
			session.setAttribute("errorMsgs", userErrorMsgs);
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/register.jsp").include(request, response);
		}
		else {
//			if no error messages
			UserDAO.insertuser(newUser);			
			newUser.setMessage("data is inserted");
			request.getRequestDispatcher("/menu_login.jsp").include(request, response);
			request.getRequestDispatcher("/register.jsp").include(request, response);
		}
	}
}
