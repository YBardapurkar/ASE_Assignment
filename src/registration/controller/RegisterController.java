package registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private void getuserParam (HttpServletRequest request, User r1) {
		r1.setuser(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"), request.getParameter("lastname"),  request.getParameter("role"), request.getParameter("utaid"), request.getParameter("phone"), request.getParameter("email"), request.getParameter("street"), request.getParameter("city"),  request.getParameter("state"), request.getParameter("zipcode"));	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User newUser = new User();
		UserError CerrorMsgs = new UserError();
		session.removeAttribute("errorMsgs");
		
		if (action.equalsIgnoreCase("saveuser")) {
			getuserParam(request, newUser);
			newUser.validateuser(action, newUser, CerrorMsgs);
			session.setAttribute("r1", newUser);	
			
			if (!CerrorMsgs.getErrorMsg().equals("")) {
//				if error messages
				getuserParam(request, newUser);
				session.setAttribute("errorMsgs", CerrorMsgs);
				request.getRequestDispatcher("/menu_login.jsp").include(request, response);
				request.getRequestDispatcher("/register.jsp").include(request, response);
			}
			else {
//				if no error messages
				registration.data.UserDAO.insertuser(newUser);			
				newUser.setMessage("data is inserted");
				request.getRequestDispatcher("/menu_login.jsp").include(request, response);
				request.getRequestDispatcher("/register.jsp").include(request, response);
			}
		}
	}
}
